package com.example.demo.service;
/**
 * service 层
 */

import com.example.demo.Exception.GirlException;
import com.example.demo.enums.ResultEnum;
import com.example.demo.repository.GirlRepository;
import com.example.demo.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {
    @Autowired
    private GirlRepository girlRepository;


//    通过事务提交同事存储两个信息
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setAge(10);
        girlA.setCupsize("a");
        Girl girlB = new Girl();
        girlB.setAge(20);
        girlB.setCupsize("b");
        girlRepository.save(girlA);
        girlRepository.save(girlB);
    }
//
    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回你“你可能再上小学” code=100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);

        }else if (age > 10 && age<16){
            //返回“你可能在上初中”  code=101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);

        }
    }
    //新建测试方法 通过id查询一个
    /**
     * @param id
     * @return Girl
     */
    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }
}
