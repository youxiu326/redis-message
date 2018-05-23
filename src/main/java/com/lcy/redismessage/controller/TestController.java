package com.lcy.redismessage.controller;

import com.lcy.redismessage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author player six
 * @email jslcylcy@163.com
 * @create 2018-05-22 17:17
 **/
@RestController
public class TestController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add-topic")
    public String addTopic(String message) {
        messageService.sendTopicMessage(message);
        return "success";
    }

    @PostMapping("/add-queue")
    public String addQueue(String message) {
        messageService.sendQueueMessage(message);
        return "success";
    }
}
