package com.cloud.springboot.controller;

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
        Person person = new Person();
        person.setId(1L);
        person.setName("클라우드");
        person.setAge(31);
        person.setHobbies(List.of("운동", "코딩"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }
}

@Getter
@Setter
class Person{
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}
