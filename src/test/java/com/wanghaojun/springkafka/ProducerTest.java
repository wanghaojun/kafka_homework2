package com.wanghaojun.springkafka;

import com.wanghaojun.springkafka.component.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {

    @Autowired
    private Producer producer;

    @Test
    void sendMsgSecTest(){
        producer.sendMsgSec(1,3600,"producerhour");
    }

    @Test
    void sendMsgTest(){
        producer.sendMsgLine(10,"produce_test");
    }
}
