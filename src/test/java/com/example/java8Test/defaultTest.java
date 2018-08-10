package com.example.java8Test;

import org.junit.Test;

/**
 * @Description：java8特性默认方法
 * @Author:yanghao
 * @Date：2018/7/16 9:43
 */
public class defaultTest {

    /**
     * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
     * 我们只需在方法名前面加个default关键字即可实现默认方法。
     */
    @Test
    public void test1() {
//        Vehicle vehicle = new Car();
//        vehicle.print();
        Vehicle vehicle1= new Car1();
        vehicle1.print();
    }
    interface Vehicle {
        default void print(){
            System.out.println("我是一辆车!");
        }
//        Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法。例如：
        static void blowHorn(){
            System.out.println("按喇叭!!!");
        }
    }

    interface FourWheeler {
        default void print(){
            System.out.println("我是一辆四轮车!");
        }
    }

    class Car implements Vehicle, FourWheeler {
        public void print(){
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }
    class Car1 implements Vehicle {
        public void print(){
            Vehicle.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }
}


