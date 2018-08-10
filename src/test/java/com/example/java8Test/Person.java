package com.example.java8Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/7/10 10:03
 */
@Data
@AllArgsConstructor
public class Person {
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private int age;
}
