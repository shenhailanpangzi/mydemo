package com.example.demo.controller;

import com.example.demo.properties.GrilProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
//RestController = controller+resquestMapping
@RestController()
public class HelloController {

    @Resource
    private GrilProperties grilProperties;

//    @RequestMapping(value = "/hello",method = RequestMethod.GET)//不写请求方式默认两种都可以
    @GetMapping(value = {"/hi","/hello"})//两个地址路径
    public String say(@RequestParam(value = "id",required = false,defaultValue = "0") Integer Myid){
    System.out.println("!!!!!!!!!!成功!!!!!!!!!!!!!!!");
//        return "hello:"+grilProperties.getCupSize();
       return "id:"+Myid;
    }

}
