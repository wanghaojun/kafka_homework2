package com.wanghaojun.springkafka.component;

import com.wanghaojun.springkafka.dto.RecordDto;
import com.wanghaojun.springkafka.model.Record;
import com.wanghaojun.springkafka.repository.RecordRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component
public class Customer {


    @Autowired
    private RecordRepository recordRepository;

    /**
     * 监听Mytopic主题
     * @param cr 收到的消息
     */
    @KafkaListener(topics = "MyTopic")
    public void listen(ConsumerRecord<?, ?> cr) {
//       System.out.println("receive a message");
        this.custome(cr);
    }

    /**
     * 消费数据 把数据存入服务器端的mysql数据库
     * @param cr  待消费 的数据
     */
    private void custome(ConsumerRecord<?, ?> cr){
        ArrayList<Record> records = new ArrayList<Record>();
        String  value  = (String) cr.value();
        String[] lines = value.split("\n");
        String name = lines[lines.length-1];
        int l = lines.length -1 ;
//        System.out.println(lines.length);
        for (int i = 0 ;i<l ; i++){
            Record record = new Record();
            record.setUserId(lines[i].split("\\001")[0]);
            record.setType(lines[i].split("\\001")[1]);
            record.setArticalId(lines[i].split("\\001")[2]);
            record.setTime(lines[i].split("\\001")[3]);
            record.setProducerName(name);
            records.add(record);
        }
        recordRepository.saveAll(records);
    }

}
