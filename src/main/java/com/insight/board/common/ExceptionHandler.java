package com.insight.board.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/*
 @ControllerAdvice 해당 클래스가 예외처리 클래스임을 알려줌.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest  request, Exception exception){

        ModelAndView mv = new ModelAndView("/error/error_default");
        mv.addObject("exception", exception);

        log.error("Exception", exception);

        return mv;
    }

}
