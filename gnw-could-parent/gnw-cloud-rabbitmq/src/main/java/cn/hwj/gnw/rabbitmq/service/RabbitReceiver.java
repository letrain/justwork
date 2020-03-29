package cn.hwj.gnw.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "testQueue")
public class RabbitReceiver {
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("receiver=" + msg);
    }
}
