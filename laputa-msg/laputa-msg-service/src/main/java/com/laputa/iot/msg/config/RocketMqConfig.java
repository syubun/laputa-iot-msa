package com.laputa.iot.msg.config;

//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author sommer.jiang
 */
//@EnableBinding({MySink.class, RocketMqConfig.MySource.class})
public class RocketMqConfig {
    public interface MySink {
//        @Input(Sink.INPUT)
        SubscribableChannel input();

//        @Input("input2")
        SubscribableChannel input2();

//        @Input("input3")
        SubscribableChannel input3();

    }

    public interface MySource {
//        @Output(Source.OUTPUT)
        MessageChannel output();
    }
}
