package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/second-service")
public class SecondController {
    @GetMapping("/welcome")
    public String hello(){
        return "this is second service";
    }

    @GetMapping("/message")
    public String message(
            @RequestHeader("req")String req){
        log.info(req);
        return "this is second service MESSAGE";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest req){
      log.info("Server port : {}",req.getServerPort());
        return "this is second service CHECK";
    }

}
