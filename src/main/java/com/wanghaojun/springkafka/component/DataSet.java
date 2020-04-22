package com.wanghaojun.springkafka.component;

import com.wanghaojun.springkafka.dto.RecordDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 数据集组件类
 */
@Component
public class DataSet {

    @Value("${file.path}")
    public String filePath;

    @Value("${file.sort-path}")
    public String sortPath;


    /**
     * 文件读取方法
     * @param num 读取行数
     * @return ArrayList<String>
     */
    public ArrayList<String> readFromFile(int num)  {
        File file = new File(filePath);
        ArrayList<String> text = new ArrayList<>();
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            if (num==-1){
                while ((line = in.readLine())!= null){
                    text.add(line);
                }
            }else {
                while ((line = in.readLine())!= null && num!=0){
                    text.add(line);
                    num--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


    /**
     * 按照时间顺序排序
     */
    public void sort(){
        ArrayList<String> text = readFromFile(-1);
        text.sort((s, t1) -> {
            String s1 = s.split("\\001")[3];
            String s2 = t1.split("\\001")[3];
            return s1.compareTo(s2);
        });
        File file = new File(sortPath);
        Writer writer = null;

        try {
            writer = new FileWriter(file);
            for (String line: text){
                writer.write(line+"\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("sort and write to file finish!!!");
    }

    /**
     * 将spring转为RecordDto类的对象
     * @param arrayList 字符数组
     * @return ArrayList<RecordDto>
     */
    public ArrayList<RecordDto> toRecordDto(ArrayList<String> arrayList) {
        ArrayList<RecordDto> recordDtos = new ArrayList<>();
        for (String a:arrayList){
            String[] line = a.split("\\001");
            RecordDto recordDto = new RecordDto();
            recordDto.setUserId(line[0]);
            recordDto.setType(line[1]);
            recordDto.setArticalId(line[2]);
            recordDto.setTime(line[3]);
            recordDtos.add(recordDto);
        }
        return recordDtos;
    }

    /**
     * 将日期转为时间戳
     * @param date 日期
     * @return 时间戳
     */
    public Long dateToTimeStamp(String date)  {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return (simpleDateFormat.parse(date).getTime()/1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Long.valueOf("0");
    }




}
