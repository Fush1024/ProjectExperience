package com.mmall.concurrency.example.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/hystrix1")
public class HystrixController1 {

    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        throw new RuntimeException();
    }

    private String fail1() {
        log.warn("fail1");
        return "fail1";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2() {
        throw new RuntimeException();
    }

//    @HystrixCommand(fallbackMethod = "fail3")
    private String fail2() {
        log.warn("fail2");
        throw new RuntimeException();
    }

//    @HystrixCommand
    private String fail3() {
        log.warn("fail3");
        throw new RuntimeException();
    }

    private String defaultFail() {
        log.warn("default fail");
        return "default fail";
    }
}
