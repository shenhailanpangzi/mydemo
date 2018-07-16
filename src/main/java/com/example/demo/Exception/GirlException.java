package com.example.demo.Exception;

import com.example.demo.enums.ResultEnum; /**
 * @Description：
 * @Author:yanghao
 * @Date：2018/1/2 14:55
 */
//如果继承RuntimeException会进行事务回滚，继承Exception不会进行回滚
public class GirlException extends RuntimeException{
    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
