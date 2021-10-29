package com.laputa.iot.msg.controller;


import com.laputa.iot.msg.entity.Order;
//import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sommer.jiang
 */
@RestController
public class OrderController {

//    @Autowired
//    RocketMqConfig.MySource source;

//    @Autowired
//    public OrderController(Source source) {
//        this.source = source;
//    }

    /**
     * 正常情况
     */
    @GetMapping("/success")
    public String success() {
        Order order = new Order();


//        Message message = MessageBuilder
//                .withPayload(order)
//                .setHeader("orderId", order.getOrderId())
//                .build();
        //发送半消息
//        source.output().send(message);
        return "下单成功";
    }

    /**
     * 发送消息失败
     */
    @GetMapping("/produceError")
    public String produceError() {
        Order order = new Order();


//        Message message = MessageBuilder
//                .withPayload(order)
//                .setHeader("orderId", order.getOrderId())
//                .setHeader("produceError", "1")
//                .build();
        //发送半消息
//        source.output().send(message);
        return "发送消息失败";
    }

    /**
     * 消费消息失败
     */
    @GetMapping("/consumeError")
    public String consumeError() {
        Order order = new Order();


//        Message message = MessageBuilder
//                .withPayload(order)
//                .setHeader("orderId", order.getOrderId())
//                .setHeader("consumeError", "1")
//                .build();
        //发送半消息
//        source.output().send(message);
        return "消费消息失败";
    }
}
