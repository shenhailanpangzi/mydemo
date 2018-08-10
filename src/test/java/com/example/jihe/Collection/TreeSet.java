package com.example.jihe.Collection;

import java.util.Comparator;
import java.util.Set;

/**
 * @Description：
 *
 * @Author:yanghao
 * @Date：2018/7/30 16:22
 */
public class TreeSet {

    public static void main(String[] args) {
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        Person p3 = new Person(3);

        Set<Person> set = new java.util.TreeSet<>(new Person());
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println(set);  //结果为[1, 2, 3]
    }

}

class Person implements Comparator<Person> {
    public int age;
    public Person(){}
    public Person(int age){
        this.age = age;
    }
    @Override
    /***
     * 根据年龄大小进行排序  设置排序规则
     */
    public int compare(Person o1, Person o2) {
        // TODO Auto-generated method stub
        if(o1.age > o2.age){
            return 1;
        }else if(o1.age < o2.age){
            return -1;
        }else{
            return 0;
        }
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ""+this.age;
    }
}