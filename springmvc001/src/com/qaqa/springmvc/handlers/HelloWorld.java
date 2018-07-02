package com.qaqa.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thinkpad on 2018/6/29.
 */

@Controller
public class HelloWorld {

    //1. 使用@RequestMapping注解来映射请求URL
    //2. 返回值会通过视图解析器解析为实际的物理视图,对于InternalResourceViewResolver视图解析器,会做如下的解析:
    // 通过 prefix + returnVal + 后缀 这样的方式得到实际的物理视图,然后做转发操作
    // /WEB-INF/views/success.jsp
    @RequestMapping("/helloworld")
    public String hello() {
        System.out.println("hello world");
        return "success";
    }
}
