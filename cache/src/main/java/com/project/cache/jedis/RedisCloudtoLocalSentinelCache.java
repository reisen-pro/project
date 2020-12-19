package com.project.cache.jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.util.Pool;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;


public class RedisCloudtoLocalSentinelCache extends Pool<Jedis> {

    protected GenericObjectPoolConfig poolConfig;

    protected int timeout = Protocol.DEFAULT_TIMEOUT;

    protected String password;

    protected int database = Protocol.DEFAULT_DATABASE;

    protected String cloudToLocalAddress;

    protected Map<HostAndPort,HostAndPort> IPAndPortMap = new HashMap<HostAndPort, HostAndPort>();

    protected Set<MasterListener> masterListeners = new HashSet<MasterListener>();

    protected final Logger log = Logger.getLogger(RedisCloudtoLocalSentinelCache.class);

    // private volatile JedisFactory factory;

    private volatile HostAndPort currentHostMaster;

    public RedisCloudtoLocalSentinelCache(String masterName,
                                          Set<String> sentinels, final GenericObjectPoolConfig poolConfig,
                                          int timeout, final String password, final int database,
                                          final String cloudToLocalAddress) {

        this.poolConfig = poolConfig;
        this.timeout = timeout;
        this.password = password;
        this.database = database;
        this.cloudToLocalAddress = cloudToLocalAddress;

        // 初始化转换ip和port
        convertIPAndPort(cloudToLocalAddress);
        // 初始化哨兵
        HostAndPort master = initSentinels(sentinels, masterName);
        // 初始化连接池
        initPool(master);
    }

    private void convertIPAndPort(String cloudToLocalAddress){
        log.info("当前转换的地址为:"+cloudToLocalAddress);
        String[] address = cloudToLocalAddress.split(";");
        for (String str : address) {
            // 分割地址信息 10.32.65.53, 26379#10.139.168.132, 26379
            String[] IPAndPorts = str.split("#"); //10.32.65.53, 26379   10.139.168.132, 26379
            HostAndPort local = toHostAndPort(Arrays.asList(IPAndPorts[0].split(":")));
            HostAndPort cloud = toHostAndPort(Arrays.asList(IPAndPorts[1].split(":")));
            IPAndPortMap.put(local, cloud);
        }
        log.info("当前的地址关系为:"+IPAndPortMap);
    }

    public void destroy() {
        for (MasterListener m : masterListeners) {
            m.shutdown();
        }

        super.destroy();
    }

    public HostAndPort getCurrentHostMaster() {
        return currentHostMaster;
    }

    /**
     * MasterListener会在发现master地址改变以后，去调用initPool方法。
     * 如果是第一次调用initPool方法(构造函数中调用)，那么会初始化Jedis实例创建工厂，
     * 如果不是第一次调用(MasterListener中调用)，那么只对已经初始化的工厂进行重新设置。
     * 从以上也可以看出为什么currentHostMaster和factory这两个变量为什么要声明为volatile，
     * 它们会在多线程环境下被访问和修改，因此必须保证可见性。
     *
     * @param master
     */

    private void initPool(HostAndPort master) {
        if (!master.equals(currentHostMaster)) {
            currentHostMaster = master;
/*            if (factory == null) {
                factory = new JedisFactory(master.getHost(), master.getPort(),
                        timeout, timeout, password, database,null); // TODO 使用临时包 升级到2.8.2，此处构建方法新增入参
                initPool(poolConfig, factory);
            } else {
                factory.setHostAndPort(currentHostMaster);
                // although we clear the pool, we still have to check the
                // returned object
                // in getResource, this call only clears idle instances, not
                // borrowed instances
                internalPool.clear();
            }*/

            log.info("Created JedisPool to master at " + master);
        }
    }

    private HostAndPort initSentinels(Set<String> sentinels,
                                      final String masterName) {

        HostAndPort master = null;
        boolean sentinelAvailable = false;

        log.info("Trying to find master from available Sentinels...");

        // 依据每一个单独的哨兵去连接,看是否能够连的上
        for (String sentinel : sentinels) {
            // 将配置的哨兵的地址和端口转换为对应的对象
            final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
                    .split(":")));

            log.info("Connecting to Sentinel " + hap);

            Jedis jedis = null;
            try {
                jedis = new Jedis(hap.getHost(), hap.getPort());
                // 连接当前的哨兵,根据主机名获取当前的主机IP与其端口号
                List<String> masterAddr = jedis
                        .sentinelGetMasterAddrByName(masterName);

                // connected to sentinel...
                sentinelAvailable = true;

                if (masterAddr == null || masterAddr.size() != 2) {
                    log.warn("Can not get master addr, master name: "
                            + masterName + ". Sentinel: " + hap + ".");
                    continue;
                }
                // 获取主机的对象,有一个获取到就跳出循环
                master = IPAndPortMap.get(toHostAndPort(masterAddr));
                log.error("Found Redis master at " + master);
                break;
            } catch (JedisConnectionException e) {
                log.info("Cannot connect to sentinel running @ " + hap
                        + ". Trying next one.");
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }

        if (master == null) {
            if (sentinelAvailable) {
                // can connect to sentinel, but master name seems to not
                // monitored
                throw new JedisException("Can connect to sentinel, but "
                        + masterName + " seems to be not monitored...");
            } else {
                throw new JedisConnectionException(
                        "All sentinels down, cannot determine where is "
                                + masterName + " master is running...");
            }
        }

        log.info("Redis master running at " + master
                + ", starting Sentinel listeners...");

        // 将哨兵加入主机监听 ?? 启动对每个sentinels的监听
        for (String sentinel : sentinels) {
            final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
                    .split(":")));
            MasterListener masterListener = new MasterListener(masterName,
                    hap.getHost(), hap.getPort());
            masterListeners.add(masterListener);
            masterListener.start();
        }

        return master;
    }

    private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
        String host = getMasterAddrByNameResult.get(0);
        int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
        return new HostAndPort(host, port);
    }

    @Override
    public Jedis getResource() {
        while (true) {
            Jedis jedis = super.getResource();
            //jedis.setDataSource(this);

            // get a reference because it can change concurrently
            final HostAndPort master = currentHostMaster;
            final HostAndPort connection = new HostAndPort(jedis.getClient()
                    .getHost(), jedis.getClient().getPort());

            if (master.equals(connection)) {
                // connected to the correct master
                return jedis;
            } else {
                jedis.close();
            }
        }
    }

    /**
     * @deprecated starting from Jedis 3.0 this method won't exist. Resouce
     *             cleanup should be done using @see
     *             {@link redis.clients.jedis.Jedis#close()}
     */
    public void returnBrokenResource(final Jedis resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    /**
     * @deprecated starting from Jedis 3.0 this method won't exist. Resouce
     *             cleanup should be done using @see
     *             {@link redis.clients.jedis.Jedis#close()}
     */
    public void returnResource(final Jedis resource) {
        if (resource != null) {
            resource.resetState();
            returnResourceObject(resource);
        }
    }

    /**
     * 然委托了Jedis去与sentinel打交道，订阅了关于master地址变换的消息，
     * 当master地址变换时，就会再调用一次initPool方法，重新设置对象池相关的设置
     *
     * @author zhouwy_ebiz
     *
     */
    protected class MasterListener extends Thread {

        protected String masterName;
        protected String host;
        protected int port;
        protected long subscribeRetryWaitTimeMillis = 5000; // 订阅重试等待时间
        protected Jedis j;
        protected AtomicBoolean running = new AtomicBoolean(false);

        protected MasterListener() {
        }

        public MasterListener(String masterName, String host, int port) {
            this.masterName = masterName;
            this.host = host;
            this.port = port;
        }

        public MasterListener(String masterName, String host, int port,
                              long subscribeRetryWaitTimeMillis) {
            this(masterName, host, port);
            this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
        }

        public void run() {

            running.set(true);

            while (running.get()) {

                j = new Jedis(host, port);

                try {
                    j.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            log.info("Sentinel " + host + ":" + port
                                    + " published: " + message + ".");

                            String[] switchMasterMsg = message.split(" ");

                            if (switchMasterMsg.length > 3) {

                                if (masterName.equals(switchMasterMsg[0])) {
                                    initPool(IPAndPortMap.get(toHostAndPort(Arrays.asList(
                                            switchMasterMsg[3],
                                            switchMasterMsg[4]))));
                                } else {
                                    log.info("Ignoring message on +switch-master for master name "
                                            + switchMasterMsg[0]
                                            + ", our master name is "
                                            + masterName);
                                }

                            } else {
                                log.info("Invalid message received on Sentinel "
                                        + host
                                        + ":"
                                        + port
                                        + " on channel +switch-master: "
                                        + message);
                            }
                        }
                    }, "+switch-master");
                    log.info("当前连接监听成功: host:"+host+"port:"+port);

                } catch (JedisConnectionException e) {

                    if (running.get()) {

                        log.info("Lost connection to Sentinel at " + host
                                + ":" + port
                                + ". Sleeping 5000ms and retrying.");
                        try {
                            Thread.sleep(subscribeRetryWaitTimeMillis);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        log.info("Unsubscribing from Sentinel at " + host + ":"
                                + port);
                    }
                }
            }
        }

        public void shutdown() {
            try {
                log.info("Shutting down listener on " + host + ":" + port);
                running.set(false);
                // This isn't good, the Jedis object is not thread safe
                j.disconnect();
            } catch (Exception e) {
                log.fatal( "Caught exception while shutting down: ",e);
            }
        }
    }
}
