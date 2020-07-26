package com.project.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

/**
 * RedisReceiver
 */

@Component
@Slf4j
public class RedisReceiver implements MessageListener {

    //@Autowired
    //private IGbcJobTraceService service;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void timer() {
        redisTemplate.opsForValue().get("heartbeat");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void jobDetail() {
        log.info("开始查询");
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("namespace" + "xxx");//空命名+具体方法
        if (entries != null && entries.size() > 0) {
            Set<Object> objects = entries.keySet();
            for (Object object : objects) {
                //BatchStatusDTO batchStatusDTO = JSON.parseObject(((String) entries.get(object)),BatchStatusDTO.class);
                //service.stopBatchTrace(batchStatusDTO);
                redisTemplate.opsForHash().delete("namespace", object);
            }
        }
        //保持通道通畅
        redisTemplate.convertAndSend("GBCS", "");
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String[] body = null;
        String channel = null;
        try {
            //过滤无效消息
            if (message.getBody() == null || message.getBody().length <= 2) {
                return;
            }
            body = new String(message.getBody(), "UTF-8").split("#");
            channel = new String(message.getChannel(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        "100133#10.56.85.60#191122135100#191122135101#falg#message"
        log.info("接收到消息: channel{}  body:{}", channel, body);
//        BatchStatusDTO batchStatusDTO = new BatchStatusDTO();
//        batchStatusDTO.setBatchID(body[0]);
//        batchStatusDTO.setServerIp(body[1]);
//        batchStatusDTO.setStartTime(body[2]);
//        //这两个参数可能不传
//        batchStatusDTO.setEndTime(body.length>=4?body[3]:null);
//        batchStatusDTO.setStatus(body.length>=5?body[4]:null);
//        batchStatusDTO.setErrMessage(body.length>=6?body[5]:null);
//
//        if (StrUtil.equals(ConstantField.GBCS,channel)){
//            service.startBatchTrace(batchStatusDTO);
//        }else{
//            service.stopBatchTrace(batchStatusDTO);
//        }
    }
}