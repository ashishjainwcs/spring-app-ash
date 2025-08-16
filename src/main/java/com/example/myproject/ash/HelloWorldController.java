package com.example.myproject.ash;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {
    
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
    
    @GetMapping("/{id}")
    public String helloWorldPathParam(@PathVariable String id) {
        return "Hello World "+id;
    }
}
