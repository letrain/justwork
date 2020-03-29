package cn.hwj.gnw.rabbitmq.controller;

import cn.hwj.gnw.rabbitmq.service.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {
    @Autowired
    private RabbitSender sender;

    @RequestMapping("/rabbitmq")
    public String test() {
        return sender.sendMsg();
    }
}
