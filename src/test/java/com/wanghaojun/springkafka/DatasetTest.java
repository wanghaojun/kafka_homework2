package com.wanghaojun.springkafka;

import com.wanghaojun.springkafka.component.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@SpringBootTest
class DatasetTest {

    @Autowired
    private DataSet dataSet;


    @Test
    void readFileTest() throws FileNotFoundException {

        ArrayList<String> res = dataSet.readFromFile(-1);
        int a = 0,b=0,c= 0;
        for (String re : res) {
            String type = re.split("\\001")[1];
            switch (type) {
                case "0":
                    a ++;
                    break;
                case "1":
                    b++;
                    break;
                case "2":
                    c++;
                    break;
            }
        }
        System.out.println(res.size());
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        System.out.println(res.size() - a - b - c);

    }


    @Test
    void sortTest(){
        dataSet.sort();

    }
}
