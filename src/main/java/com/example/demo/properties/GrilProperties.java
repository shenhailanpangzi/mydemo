package com.example.demo.properties;
/**
 * 配置
 */
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component  //注入配置
@ConfigurationProperties(prefix = "girl")
public class GrilProperties {

    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
