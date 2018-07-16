package com.example.demo.aspect;
/**
 * 拦截器
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


//引入aop，把文件引入spring容器
@Aspect
@Component
public class HttpAspect {
    //在请求之前执行(拦截的绝对路径的类的方法，*表示所有方法，两个点表示无论是任何参数都会被拦截)
//    @Before("execution(public * com.example.demo.controller.girlController.*(..))")
//    @Before("execution(public * com.example.demo.controller.girlController.girlList(..))")
//    public void log(){
//        System.out.println("before拦截器开始执行");
//    }
//
//    @After("execution(public * com.example.demo.controller.girlController.girlList(..))")
//    public void doAfter(){
//        System.out.println("after拦截器开始执行");
//    }
    private final static Logger logger=  LoggerFactory.getLogger(HttpAspect.class);
    //声明切面位置
    @Pointcut("execution(public * com.example.demo.controller.GirlController.*(..))")
    public void log(){}
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
//        System.out.println("before拦截器开始执行");
        logger.info("before拦截器开始执行");
        //获取httpServletRequest请求
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        //url 获取
        logger.info("url={}", request.getRequestURI());
        //method 获取
        logger.info("method={}", request.getMethod());
        //ip 获取
        logger.info("ip={}", request.getRemoteAddr());
        //类方法 获取类名+类方法
        logger.info("class_method{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());
        System.out.println("httpReq展示结束");
    }
    @After("log()")
    public void doAfter(){
//        System.out.println("after拦截器开始执行");
        logger.info("after拦截器开始执行");
    }
//       在返回参数时的拦截  returning中写入参(object)
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("afterReturning拦截器开始执行");
//        logger.info("response={}", object.toString());
    }

}
