package me.shinsungyoung.springbootdeveloper.controller;

import me.shinsungyoung.springbootdeveloper.service.TestServiceMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestControllerMybatis {

    @Autowired
    private TestServiceMybatis testService;

    @GetMapping("/testMybatis")
    public List<Map<String, Object>> test(){

        List<Map<String, Object>> list = testService.test();

        return list;
    }

}
