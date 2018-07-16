package com.example.demo.domain;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/2/26 12:22
 */
public class PostReqDto {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PostReqDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
