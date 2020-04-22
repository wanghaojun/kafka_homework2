package com.wanghaojun.springkafka;

import com.wanghaojun.springkafka.component.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@SpringBootTest
class SpringKafkaApplicationTests {

	@Autowired
	private DataSet dataSet;

	@Test
	void contextLoads() {
	}



}
