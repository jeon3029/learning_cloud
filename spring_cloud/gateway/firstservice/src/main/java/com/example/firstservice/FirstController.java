package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstController {
    @GetMapping("/welcome")
    public String hello(){
        return "this is first service";
    }

    @GetMapping("/message")
    public String message(
            @RequestHeader(value = "req") String req){
        log.info(req);
        return "this is second service";
    }

    @GetMapping("/check")
    public String check(){
        return "this is first service CHECK";
    }
}
