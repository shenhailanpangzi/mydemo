package com.example.mytest;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Girl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;

/**
 * @Description：json和类转换
 * @Author:yanghao
 * @Date：2018/3/21 16:15
 */
public class MyTest1 {

    @Test
    public void test1(){
        System.out.println("hello Test!!");
    }
    @Test
    //json和对象相互转换
    public void test2() {
        Girl girl = new Girl();
        girl.setAge(12);
        girl.setCupsize("C");
        girl.setId(1);
        girl.setMoney((double) 15);
        //fastjson对象转json
        String girlJson = JSON.toJSONString(girl);
        System.out.println(girlJson);
        //fastjson的json转对象
        Girl girl1 = JSON.parseObject(girlJson, Girl.class);
        System.out.println(girl1);
        ObjectMapper mapper = new ObjectMapper();
        try {
            //json转对象
            Girl Girl = (Girl) mapper.readValue(girlJson, Girl.class);
            System.out.println(Girl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //对象转json
            mapper.writeValueAsString(girl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Test
    //获得文件大小
    public void Test2(){
        File file = new File("F:/upload/接口数据导出1.xls");
        System.out.println("文件大小:"+file.length());
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(file);
            System.out.println("流的文件大小："+inputStream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    //文件编码
    public void Test3() throws UnsupportedEncodingException {
        String message = "无权限";
        String responname = URLEncoder.encode(message, "UTF-8");
        System.out.println(responname);
    }
}
