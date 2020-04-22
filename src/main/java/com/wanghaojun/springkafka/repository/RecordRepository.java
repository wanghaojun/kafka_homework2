package com.wanghaojun.springkafka.repository;


import com.wanghaojun.springkafka.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RecordRepository extends CrudRepository<Record,Integer> {


}
