package com.example.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/7/10 8:46
 */
public class lambdaTest {
    /**
     *单参数lambda
     */
    @Test
    public void test1() {
        String a = "hell！！！";
//      lambda只有一个参数时()只有一个语句时{}可以省略
//        MyLambdaInterf aBLockOfCode = (s)-> {System.out.println(s);};
        MyLambdaInterf aBLockOfCode = s-> System.out.println(s);
        aBLockOfCode.aaa(a);
    }
//该注释会强制 javac 检查一个接口是否符合函数接口的标准。 如果该注释添加给一个枚举
//类型、 类或另一个注释， 或者接口包含不止一个抽象方法， javac 就会报错。 重构代码时，
//使用它能很容易发现问题
    @FunctionalInterface
    interface MyLambdaInterf {
        void aaa(String s);
    }
    /**
     * 多参数lambda
     */
    @Test
    public void test11(){
        IMathListener mPlusListener = (x, y) -> x + y;
        int sum = mPlusListener.doMathOperator(10, 5);
        System.out.println(sum);
    }
    public interface IMathListener {
        int doMathOperator(int start, int plusValue);
    }
    /**
     * 包含多个参数且主体为代码段
     */
    @Test
    public void test12(){
        //多参数,不指定类型
        IMathListener iMathListener = (x,y)->{
            if (x>y){
                return x;
            }else {
                return y;
            }
        };
        System.out.println("返回较大："+iMathListener.doMathOperator(1,2));
        //包含多个参数，指定参数类型
        IMathListener iMathListener1 = (int x,int y)->{
            if (x<y){
                return x;
            }else {
                return y;
            }
        };
        System.out.println("返回较小："+iMathListener1.doMathOperator(1,2));
    }
    /**
     * Lambda表达式引用的是值，而不是变量 Lambda表达式也被称为闭包(在方法外部定义的都为Final)
     */
    @Test
    public void  test13(){
        int afinal=1;
        final int bfinal=2;//这两种写法对于内部的lambda没区别都是静态的
        IMathListener iMathListener = new IMathListener(){
            @Override
            public int doMathOperator(int start, int plusValue) {
                return afinal+bfinal;
            }
        };
        System.out.println(iMathListener.doMathOperator(1,2));
    }

    /**
     * 类型推断
     * Lambda表达式中的类型推断，javac根据Lambda 表达式上下文信息就能推断出参数的正确类型。
     * 程序依然要经过类型检查来保证运行的安全性， 但不用再显式声明类型罢了。这就是所谓的类型推断
     */
    public void test14(){
        //在下面表达式中，javac会自行将x和y推断为int类型
        IMathListener mSubListener = (x, y) -> x * y;
//        Funtion接口，该接口接收一个F类型的参数并返回一个T类型的值。
//        javac可以推断出接收的数据类型为String，返回类型为Integer
        Function<String, Integer> string2Integer = Integer::valueOf;
        Function<String, Integer> string2Integer2 = a ->Integer.valueOf(a);
    }

    /**
     * 遍历集合
     */
    @Test
    public void test2() {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);
    }

    /**
     * 下面是使用lambdas 来实现 Runnable接口
     */
    @Test
    public void test3() {
        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

        // 1.2使用 lambda expression
        Thread thread = new Thread(() -> System.out.println("Hello world !"));
        thread.start();

        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

        // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();
    }

    /**
     *使用lanbda表达式从小到大排序
     */
    @Test
    public void test4() {
        List<Person> studentList = new ArrayList<Person>() {
            {
                add(new Person("stu1", "a", 1));
                add(new Person("stu2", "b", 3));
                add(new Person("stu3", "c", 2));
                add(new Person("stu4", "d", 4));
            }
        };
        //原生写法
        Collections.sort(studentList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Double.compare(o1.getAge(), o2.getAge());
            }
        });
        //
        Collections.sort(studentList,(s1,s2)->Double.compare(s1.getAge(),s2.getAge()));
        System.out.println(studentList);
    }
    @Test
    public void test5() {




    }

}