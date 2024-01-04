package com.digitalinovationone.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController  {

    @GetMapping("/teste")
    public String helloMessage(){
        return "Hello, Digital Innovation One!";
    }
}
