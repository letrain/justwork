package cn.hwj.gnw.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public String sendMsg() {
        String msg = "just test: " + new Date().getTime();
        this.amqpTemplate.convertAndSend("testQueue", msg);
        this.amqpTemplate.receiveAndConvert("testQueue", 5000);
        return "test";
    }

}
