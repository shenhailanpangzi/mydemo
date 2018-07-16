package com.example.demo.domain;
/**
 * 实体对象
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Girl {
    @Id
    @GeneratedValue//自增
    private Integer id;
    private String cupsize;
    @Min(value = 18, message = "未成年少女禁止入内")
    private Integer age;
    @NotNull(message = "金额必传")
    private Double money;

    /*@NotNull  和 @NotEmpty  和@NotBlank 区别
    @NotEmpty 用在集合类上面
    @NotBlank 用在String上面
    @NotNull  用在基本类型上*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupsize() {
        return cupsize;
    }

    public void setCupsize(String cupsize) {
        this.cupsize = cupsize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupsize='" + cupsize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
