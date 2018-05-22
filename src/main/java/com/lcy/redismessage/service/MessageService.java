package com.lcy.redismessage.service;

import com.lcy.redismessage.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 消息处理service
 *
 * @author player six
 * @email jslcylcy@163.com
 * @create 2018-05-22 17:08
 **/
@Service
public class MessageService {

    private static Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${channelname}")
    private String channelName;

    @Value("${queuename}")
    private String queueName;

    /**
     * redis发布消息
     *
     * @param message
     */
    public void sendTopicMessage(String message) {
        redisTemplate.convertAndSend(channelName, message);
    }

    /**
     * 处理redis消息队列
     */
    public void dealMsg() {
        String message = redisUtil.rightPop(queueName, 1, TimeUnit.MINUTES);
        if (message != null) {
            logger.info("redis--queue:" + queueName + "  body:" + message);
        }
    }

    /**
     * @param message
     */
    public void sendQueueMessage(String message) {
        redisUtil.leftPush(queueName, message);
    }
}
