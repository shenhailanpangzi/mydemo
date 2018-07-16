package com.example.demo.controller;
/**
 * 控制层
 */
import com.example.demo.domain.PostReqDto;
import com.example.demo.domain.Result;
import com.example.demo.repository.GirlRepository;
import com.example.demo.domain.Girl;
import com.example.demo.service.GirlService;
import com.example.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService girlService;

    public final static Logger logger = LoggerFactory.getLogger(GirlController.class);
    //查询所有
    @GetMapping(value = "/girl")
    public List<Girl> girlList() {
        logger.info("查询所有controller");
        return girlRepository.findAll();
    }
    //新增
    @PostMapping(value = "/girladd")
    public Result<Girl> girladd(@Valid Girl girl, BindingResult bindingResult) {//验证对象+验证结果
        Result result = new Result();
         if (bindingResult.hasErrors()) {//表单验证
            System.out.println("表单验证结果："+bindingResult.getFieldError().getDefaultMessage());
//            return null;
//             result.setCode(1);
//             result.setMsg(bindingResult.getFieldError().getDefaultMessage());
//             result.setData(null);
             return null;
//            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
//        Girl save = girlRepository.save(girl);
//        result.setCode(0);
//        result.setMsg("成功");
//        result.setData(girlRepository.save(girl));
        return ResultUtil.success(girlRepository.save(girl));
    }
//    通过id查询
    @GetMapping(value = "/girl/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }
//    更新
    @PutMapping (value = "/girl/{id}")
    public Girl girlUpadte(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupsize(cupSize);
        Girl save = girlRepository.save(girl);
        System.out.println("更新成功！！");
    return save;
    }
//    根据id删除
    @DeleteMapping(value = "/girl/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }
//    通过年龄查询
    @GetMapping(value = "/girl/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        System.out.println("成功！！");
        return girlRepository.findByAge(age);
    }
//    通过事物控制插入两条信息
    @GetMapping(value = "/two")
    public void saveTwoGirl(){
        System.out.println("开始！！");
        girlService.insertTwo();
        System.out.println("结束！！");
    }
// 获取某女生的年龄并且判断，小于10报错“应该在上小学”，大于10小于16，返回“可能在上初中”
    @GetMapping(value = "/girl/getage/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        System.out.println("获取年龄controller开始执行");
        girlService.getAge(id);
    }

    @PostMapping(value = "/girl/posttest")
    public void getPost(@RequestParam("id") Integer id,
                        @RequestParam("name") String name) throws Exception {
        System.out.println("controller1:"+id);
        System.out.println("controller:"+name);
    }
    @PostMapping(value = "/girl/posttest1")
    public void getPost(@RequestBody PostReqDto postReqDto) throws Exception {

        System.out.println("controller:"+postReqDto.getId());
        System.out.println("controller:"+postReqDto.getName());
    }
}