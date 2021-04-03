package com.project.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author Reisen
 * @title: CurrentHashMapController
 * @projectName project
 * @description: CurrentHashMap的使用
 * @date 2021/3/31 21:38
 */
@Slf4j
@RestController
public class CurrentHashMapController {
    // 线程个数
    private final static int THREAD_COUNT = 10;
    // 总元素数量
    private final static int ITEM_COUNT = 1000;
    // 循环次数
    private final static int LOOP_COUNT = 10000000;

    // 返回一个ConcurrentHashMap key是uuid，value是longsteam
    private ConcurrentHashMap<String, Long> getData(int count) {
        // range，需要传入开始节点和结束节点两个参数，返回的是一个有序的LongStream。
        // 差别就是rangeClosed包含最后的结束节点，range不包含。
        return LongStream.rangeClosed(1, count)
                // boxed()返回一个由该流的元素组成的Stream，每个元素都装箱成Long
                .boxed()
                // 转换成集合
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(),
                        // identity()就是Function接口的一个静态方法。
                        //  Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式
                        Function.identity(),
                        (o1, o2) -> o1,
                        // 指定转换后的集合为ConcurrentHashMap
                        ConcurrentHashMap::new));
    }

    @GetMapping("odd")
    public String wrong() throws InterruptedException {
        // 初始化900个元素
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size:{}", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> {
            IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
                // 这里对并发操作的对象concurrentHashMap进行加锁，可以保证最终的数据正确
                synchronized (concurrentHashMap) {
                    int gap = ITEM_COUNT - concurrentHashMap.size();
                    log.info("gap size:{}", gap);
                    // 补充元素
                    concurrentHashMap.putAll(getData(gap));
                }
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //
        log.info("finish size:{}", concurrentHashMap.size());
        return "OK";
    }

    @GetMapping("normaluse")
    private Map<String, Long> normaluse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freps = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
                    // 获得一个随机的key
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    synchronized (freps) {
                        if (freps.containsKey(key)) {
                            freps.put(key, freps.get(key) + 1);
                        } else {
                            // key不存在则初始化1
                            freps.put(key, 1L);
                        }
                    }
                }
        ));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freps;
    }

    /**
     * 使用ConcurrentHashMap的原子性方法 computeIfAbsent 来做复合逻辑操作，判断key是否存在value
     * 如果不存在则把lambda表达式运行后的结果放入map作为value，也就是创建一个LongAdder对象，最后返回Value
     * @return map
     * @throws InterruptedException 中断异常
     */
    @GetMapping("gooduse")
    private Map<String, Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> freps = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
                    // 获得一个随机的key
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    freps.computeIfAbsent(key, k -> new LongAdder()).increment();
                }
        ));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        // 因为我们的value不是LongAdder而不是Long,所以需要做一次转换才能返回
        return freps.entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey(),
                e -> e.getValue().longValue())
        );
    }
}
