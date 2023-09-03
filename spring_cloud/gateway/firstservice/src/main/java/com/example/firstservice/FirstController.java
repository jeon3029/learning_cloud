package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstController {
    Environment env;

    @Autowired
    public FirstController(Environment env) {
        this.env = env;
    }

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
    public String check(HttpServletRequest req){
        log.info("Server port : {}",req.getServerPort());
        return String.format("this is first service CHECK port : %s",env.getProperty("local.server.port"));
    }
}
