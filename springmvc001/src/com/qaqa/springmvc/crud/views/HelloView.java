package com.qaqa.springmvc.crud.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by thinkpad on 2018/7/5.
 */

@Component
public class HelloView implements View{

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.getWriter().print("hello view, time: " + new Date());
    }
}
