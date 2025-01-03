package com.example.stusystem.config;

import com.example.stusystem.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Result exceptionAdvice(Exception e) {
        return Result.error(e.getMessage());
    }
}
