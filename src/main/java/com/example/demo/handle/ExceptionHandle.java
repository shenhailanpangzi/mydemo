package com.example.demo.handle;

import com.example.demo.Exception.GirlException;
import com.example.demo.domain.Result;
import com.example.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description：异常捕获类
 * @Author:yanghao
 * @Date：2018/1/2 14:40
 */
//异常捕获类注解
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    //声明要捕获的异常类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("[系统异常] {}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
