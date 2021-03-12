package com.example.employee;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @RequestMapping(method= RequestMethod.GET, path = "/helloworld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path="hello-world-bean/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean("Hello World , "+ name);
    }
}
