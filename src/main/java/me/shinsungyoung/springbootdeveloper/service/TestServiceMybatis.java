package me.shinsungyoung.springbootdeveloper.service;

import lombok.extern.slf4j.Slf4j;
import me.shinsungyoung.springbootdeveloper.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TestServiceMybatis {

    @Autowired
    private TestMapper testMapper;

    public List<Map<String, Object>> test(){

        log.info("======================Service======================");

        log.info("===================================================");
        return  testMapper.selectTestList();
    }

}
