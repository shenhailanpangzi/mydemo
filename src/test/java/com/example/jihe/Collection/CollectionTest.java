package com.example.jihe.Collection;

import org.junit.Test;

import java.util.*;
import java.util.TreeSet;

/**
 * @Description：集合接口
 * @Author:yanghao
 * @Date：2018/7/30 15:18
 */
public class CollectionTest {

    /**
     * ArrayList
     * 　数组就像身上编了号站成一排的人，要找第10个人很容易，根据人身上的编号很快就能找到。
     * 但插入、删除慢，要望某个位置插入或删除一个人时，后面的人身上的编号都要变。当然，加入或删除的人始终末尾的也快。
     */
    @Test
    public void test1(){
        List<String> list=new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        list.add("HAHAHAHA");
        //第一种遍历方法使用foreach遍历List
        for (String str : list) {            //也可以改写for(int i=0;i<list.size();i++)这种形式
            System.out.println(str);
        }

        //第二种遍历，把链表变为数组相关的内容进行遍历
        String[] strArray=new String[list.size()];
        list.toArray(strArray);
        for(int i=0;i<strArray.length;i++) //这里也可以改写为foreach(String str:strArray)这种形式
        {
            System.out.println(strArray[i]);
        }

        //第三种遍历 使用迭代器进行相关遍历

        Iterator<String> ite=list.iterator();
        while(ite.hasNext())
        {
            System.out.println(ite.next());
        }
        //线程安全的ArrayList
        Vector vector = new Vector();
    }
    /**
     * LinkedList
     * 链表就像手牵着手站成一圈的人，要找第10个人不容易，必须从第一个人一个个数过去。
     * 但插入、删除快。插入时只要解开两个人的手，并重新牵上新加进来的人的手就可以。删除一样的道理。
     */
    @Test
    public void test2(){
        List<String> list=new LinkedList<String>();
        list.add("Hello");
        list.add("World");
        list.add("龙不吟，虎不啸");
        //LinkedList遍历的第一种方式使用数组的方式
        String[] strArray=new String[list.size()];
        list.toArray(strArray);
        for(String str:strArray)
        {
            System.out.println(str);
        }
        //LinkedList遍历的第二种方式
        for(String str:list)
        {
            System.out.println(str);
        }
    }

    /**
     * set
     * 　共同点：1、都不允许元素重复
     * 　　　　　2、都不是线程安全的类，解决办法：Set set = Collections.synchronizedSet(set 对象)
     */
    @Test
    public void test3(){
        Set hashSet = new HashSet();
//        不可以重复，有序因为底层采用 链表 和 哈希表的算法。链表保证元素的添加顺序，哈希表保证元素的唯一性
        Set linkedHashSet = new LinkedHashSet();
//        使用TreeSet:有序；不可重复，底层 红黑树算法，擅长于范围查询。
//        *  如果使用 TreeSet() 无参数的构造器创建一个 TreeSet 对象, 则要求放入其中的元素的类必须实现 Comparable 接口所以, 在其中不能放入 null 元素
//        *  必须放入同样类的对象.(默认会进行排序) 否则可能会发生类型转换异常.我们可以使用泛型来进行限制
        Set set = Collections.synchronizedSet(hashSet);
        //无序
        Set<String> set1 = new HashSet<>();
        set1.add("S1");
        set1.add("S4");
        set1.add("S5");
        set1.add("S6");
        set1.add("S2");
        set1.add("S3");
        set1.forEach(o -> System.out.println("HashSet"+o+""));
        System.out.println();
        //保证添加顺序
        Set<String> set2 = new LinkedHashSet<>();
        set2.add("S1");
        set2.add("S4");
        set2.add("S5");
        set2.add("S6");
        set2.add("S2");
        set2.add("S3");
        set2.forEach(o -> System.out.println("LinkedHashSet"+o+""));
        System.out.println();
        //保证自然顺序
        Set<String> set3 = new TreeSet<>();
        set3.add("S1");
        set3.add("S4");
        set3.add("S5");
        set3.add("S6");
        set3.add("S2");
        set3.add("S3");
        set3.forEach(o -> System.out.println("TreeSet:"+o+""));
        System.out.println();
    }
    /**
     * 循环删除list元素
     */
    @Test
    public void test4(){
        List<String> list = new ArrayList<>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
        /**
         * 报错java.util.ConcurrentModificationException
         */
        for (String str : list) {
            if (str.contains("b")) {
                list.remove(str);
            }
        }

        /**
         * 报错：下标越界 java.lang.IndexOutOfBoundsException
         */
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            if (str.contains("b")) {
                list.remove(i);
            }
        }
        /**
         * 正常删除，每次调用size方法，损耗性能，不推荐
         */
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.contains("b")) {
                list.remove(i);
            }
        }
        /**
         * 正常删除，推荐使用
         */
        for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
            String str = ite.next();
            if (str.contains("b")) {
                ite.remove();
            }
        }
        /**
         * 报错 java.util.ConcurrentModificationException
         */
        for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
            String str = ite.next();
            if (str.contains("b")) {
                list.remove(str);
            }
        }


    }
}
