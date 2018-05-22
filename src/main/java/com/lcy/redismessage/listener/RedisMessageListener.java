package com.lcy.redismessage.listener;

import com.lcy.redismessage.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息监听器
 *
 * @author player six
 * @email jslcylcy@163.com
 * @create 2017-10-16 15:01
 **/
@Component
public class RedisMessageListener implements MessageListener {

    @Autowired
    private MessageService messageService;

    private static Logger logger = LoggerFactory.getLogger(RedisMessageListener.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 处理订阅消息
     *
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();// 请使用valueSerializer
        byte[] channel = message.getChannel();
        String msgContent = (String) redisTemplate.getValueSerializer().deserialize(body);
        String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);
        logger.info("redis--topic:" + topic + "  body:" + msgContent);
    }


    /**
     * 处理消息队列消息
     */
    @PostConstruct
    public void messageListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    messageService.dealMsg();
                }
            }
        }, "消息监听任务线程").start();
    }
}
