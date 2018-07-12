package com.qaqa.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thinkpad on 2018/7/12.
 */

@Controller
public class HelloWorld {

    @Autowired
    private UserService userService;

    public HelloWorld() {
        System.out.println("hello world constructor...");
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("success");
        return "success";
    }
}
