package com.example.userservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    private Environment env;
    private Greeting greeting;

    public UserController(Environment env, Greeting greeting) {
        this.env = env;
        this.greeting = greeting;
    }

    @GetMapping("/health_check")
    public String status(){
        return "OK";
    }
    @GetMapping("/welcome")
    public String welcome(){
        return env.getProperty("greeting.message");
    }

    @GetMapping("/welcome2")
    public String welcome2(){
        return greeting.getMessage();
    }
}
