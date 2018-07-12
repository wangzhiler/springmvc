package com.qaqa.springmvc.crud.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by thinkpad on 2018/7/11.
 */

@ControllerAdvice
public class HandleException {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception e) {
        System.out.println("----》出异常了: " + e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception3", e);
        return mv;
    }
}
