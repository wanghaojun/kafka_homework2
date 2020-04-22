package com.wanghaojun.springkafka.controller;


import com.wanghaojun.springkafka.component.Customer;
import com.wanghaojun.springkafka.component.Producer;
import com.wanghaojun.springkafka.model.Record;
import com.wanghaojun.springkafka.repository.RecordRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private Producer producer;


    @Autowired
    private Customer customer;

    /**
     * 查看当前数据库中所有数据
     * @return Iterable<Record> Json
     */
    @GetMapping("/")
    public Iterable<Record> getRecords(){
        return recordRepository.findAll();
    }

    /**
     * 查看当前数据库中数据数目
     * @return 数据数目
     */
    @GetMapping("/count")
    public Object count(){
        return recordRepository.count();
    }

    /**
     * 秒级生产者1
     * @param num 产生数据，-1为全部
     * @return Object
     */
    @GetMapping("/produce1")
    public Object produce1(int num){
        producer.sendMsgLine(num,"producer1");

        return new String("this is produce1 \n produce "+num+" message");
    }

    /**
     * 小时级生产者，每次生产1小时数据
     * @param num 发送次数
     * @return Object
     */
    @GetMapping("/producehour")
    public Object produceHour(int num){
        producer.sendMsgSec(num,3600,"producehour");
        return new String("this is producehour \n produce "+num+" message");
    }

    /**
     * 秒级生产者2
     * @param num 产生数据，-1为全部
     * @return Object
     */
    @GetMapping("/produce2")
    public Object produce2(int num){
        producer.sendMsgLine(num,"producer2");
        return new String("this is produce2 \n produce "+num+" message");
    }

}
