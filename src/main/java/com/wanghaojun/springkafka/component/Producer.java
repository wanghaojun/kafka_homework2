package com.wanghaojun.springkafka.component;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Producer {

    @Autowired
    private DataSet dataSet;

    private final String topic = "MyTopic";

    @Autowired
    private KafkaTemplate<String, String> template;

    /**
     * 小时级生产者 一小时的数据发送一次
     * @param num 发送次数 ，-1为发送数据集中的所有内容
     * @param time 消息跨越时间，比如小时级生产者每次发送一个小时的数据
     * @param name 生产者名称
     */
    public void sendMsgSec(int num,int time,String name){
        ArrayList<String> text = dataSet.readFromFile(num*500);
        // 发送记录数
        int i=0;
        // 发送次数
        int c=0;
        while ((i < num) && (c < text.size()) ){
            Long temp =dataSet.dateToTimeStamp(text.get(c).split("\001")[3]) + time;
            StringBuilder sendMsg = new StringBuilder();
            while ( (c<text.size()) &&  (temp >= dataSet.dateToTimeStamp(text.get(c).split("\001")[3])) ){
                sendMsg.append(text.get(c)).append("\n");
                c++;
            }
            i++;
//            System.out.println(sendMsg.toString()+name);
            template.send(topic,sendMsg.toString()+name);
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 秒级生产者 一条记录发送一次
     * @param num 发送次数
     * @param name 生产者名称
     */
    public void sendMsgLine(int num,String name)  {
        ArrayList<String> text = dataSet.readFromFile(num);
        int i=0;
        while (i < text.size()){
            template.send(topic, text.get(i) + "\n" + name);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }
    }
}
