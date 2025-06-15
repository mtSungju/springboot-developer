package me.shinsungyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller

public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("최성주"); 
        examplePerson.setAge(30);
        examplePerson.setHobbies(List.of("클라이밍", "음악감상"));
        
        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());
        
        return "example"; // example.html 뷰 조회
    }

    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
