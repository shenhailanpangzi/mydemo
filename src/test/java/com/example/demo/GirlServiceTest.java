package com.example.demo;

import com.example.demo.domain.Girl;
import com.example.demo.repository.GirlRepository;
import com.example.demo.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description：单元测试类
 * @Author:yanghao
 * @Date：2018/1/2 17:43
 */
//RunWith表示要在测试环境中运行,底层使用Junit4测试工具
@RunWith(SpringRunner.class)
//SpringBootTest表示将启动整个Springboot工程
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest(){
        Girl girl = girlService.findOne(4);
        //断言
        Assert.assertEquals( new Integer(14),girl.getAge());
    }
}
