package com.example.demo.utils;

import com.example.demo.domain.Result;

/**
 * @Description： 错误信息工具类
 * @Author:yanghao
 * @Date：2017/12/27 15:27
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static Result success(){
        return success(null);
    }

}
