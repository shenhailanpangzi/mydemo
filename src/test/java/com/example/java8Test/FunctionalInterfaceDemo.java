package com.example.java8Test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/7/13 10:35
 */
public class FunctionalInterfaceDemo {
/*
函数式接口是什么呢？函数式接口(Functional Interface)是Java 8对一类特殊类型的接口的称呼。
这类接口只定义了唯一的抽象方法的接口（除了隐含的Object对象的公共方法），用作Lambda表达式的类型。
    在java8中，满足下面任意一个条件的接口都是函数式接口：
            1、被@FunctionalInterface注释的接口，满足@FunctionalInterface注释的约束。
            2、没有被@FunctionalInterface注释的接口，但是满足@FunctionalInterface注释的约束
    @FunctionalInterface注释的约束：
            1、接口有且只能有个一个抽象方法，只有方法定义，没有方法体
            2、在接口中覆写Object类中的public方法，不算是函数式接口的方法。
*/
//    下面三个接口都是函数式接口：
    //接口一
    @FunctionalInterface
    public interface FunctionInterfaceTest1 {
        String getInfo(String input);
        @Override
        String toString();  //Object中的方法
        @Override
        boolean equals(Object obj); //Object中的方法
    }
    //接口二
    @FunctionalInterface
    public interface FunctionInterfaceTest2 {
        String getInfo(String input);
    }
    //接口三
    public interface FunctionInterfaceTest3 {
        String getInfo(String input);
    }
    /**
     * 函数接口与lambda
     */
    @FunctionalInterface
    public interface IMathListener {
        int doMathOperator(int start, int plusValue);
    }
    @Test
    public void test1(){
//        Lambda表达式都是静态类型的，也就是说其在编译时就已经被编译，
// 所以刚才被引用的方法必须是静态的，否则编译器会报错。
//        也就是说doIntPlus与IMathListener接口中的doMathOperator方法的签名一样。
// 既然签名一样，我们可以函数调用，直接生成了一个IMathListener对象
//        对于函数接口而言，接口中唯一方法的命名并不重要了，只要方法签名和Lambda表达式的类别相匹配即可
        IMathListener mPlusListener = Math::doIntPlus;//等价于mPlusListener2
        IMathListener mPlusListener2 = (start, plusValue) -> start+plusValue;
        System.out.println(mPlusListener.doMathOperator(1,3));
        System.out.println(mPlusListener2.doMathOperator(1,3));
    }
    /**
     *  java8中常用的函数式接口：
     *   接口                 说明
     *   Function<T,R>      接收一个T类型的参数，返回一个R类型的结果
     *   Consumer<T>        接收一个T类型的参数，不返回值
     *   Predicate<T>       接收一个T类型的参数，返回一个boolean类型的结果
     *   Supplier<T>        不接受参数，返回一个T类型的结果
     */
    @Test
    public void test2(){
        /**
         * 先看看如何创建它们
         */
        Function<Integer,String> function = item -> item +"返回值";
        //lambda语句，使用大括号，没有return关键字，表示没有返回值
        Consumer<String> consumer = iterm -> {System.out.println(iterm);};

        Predicate<String> predicate = iterm -> "".equals(iterm)||null==iterm;

        Supplier<String> supplier = () -> new String("我是supplier接口！！");
//        输出
        System.out.println(function.apply(123));
        consumer.accept("haha");
        System.out.println(predicate.test(""));
        System.out.println(supplier.get());
        /**
         * 再看看怎么使用
         * demo释义：
         * 1、创建一个String类型的集合
         * 2、将集合中的所有元素的末尾追加字符串'1'
         * 3、选出长度大于2的字符串
         * 4、遍历输出所有元素
         */
        List<String> list = Arrays.asList("zhangsan","lisi","wangwu","xiaoming","zhaoliu");

        list.stream()
                .map(value -> value + "1") //传入的是一个Function函数式接口
                .filter(value -> value.length() > 2) //传入的是一个Predicate函数式接口
                .forEach(value -> System.out.println(value)); //传入的是一个Consumer函数式接口
    }
    /**
     * Java8中对于接收两个参数的场景提供了相关的函数式接口
     * 接口名                      说明
     * BiFunction<T, U, R>      接收T类型和U类型的两个参数，返回一个R类型的结果
     * BiConsumer<T , U>        接收T类型和U类型的两个参数，不返回值
     * BiPredicate<T, U>        接收T类型和U类型的两个参数，返回一个boolean类型的结果
     */
    public void test3(){
        /**
         * Bi类型的接口创建
         */
        BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length()+str2.length();

        BiConsumer<String, String> biConsumer = (str1,str2) -> System.out.println(str1+str2);

        BiPredicate<String, String> biPredicate = (str1,str2) -> str1.length() > str2.length();


        /**
         * Bi类型的接口使用
         */
        int length = getLength("hello", "world", (str1,str2) -> str1.length() + str2.length()); //输出10
        boolean boolean1 = getBoolean("hello", "world", (str1,str2) -> str1.length() > str2.length()); //输出false

        System.out.println(length);
        System.out.println(boolean1);

        noResult("hello", "world", (str1,str2) -> System.out.println(str1+" "+str2)); //没有输出

    }
        public  static int getLength(String str1,String str2,BiFunction<String, String, Integer> function){
            return function.apply(str1, str2);
        }

        public static void noResult(String str1,String str2,BiConsumer<String, String> biConcumer){
            biConcumer.accept(str1, str2);
        }

        public static boolean getBoolean(String str1,String str2,BiPredicate<String, String> biPredicate){
            return biPredicate.test(str1, str2);
        }
    /**
     * 1、  Function接口的andThen方法和compose方法
     *
     * Compose方法：方法接收一个Function类型的参数，返回一个值。这也是一个标准的Function类型的定义。
     * 在compose方法内部也有一个apply方法。在执行compose方法中的apply方法之前，它先执行了before接口的apply方法，
     * 也是compose方法的输入参数。然后将before方法执行的返回值作为compose中apply方法的输入参数。实际上是形成了一种链式组合。
     *
     * andThen方法：该方法与compose方法很类似。不同之处在于，andThen是先执行自身的apply方法，将apply的返回值作为after接口的输入值。
     * 相对于compose方法，只是方向的不同
      */
    @Test
    public void test4(){
        String str1 = getLength1("hello",  value -> value.length(),value -> "hello的长度："+value); //输出:hello的长度：5
        System.out.println(str1);

        Integer result = getLength2("hello", value -> value, value -> value.length()); //输出：5
        System.out.println(result);

        Integer resInteger = getLength3("hello","world",(value1,value2)->value1+value2,value ->value.length());
        System.out.println(resInteger);
    }
    public  String getLength1(String str1,Function<String,Integer> function2,Function<Integer, String> function1){
        /**
         * 这里一定要注意，function1和function2的参数类型。
         * function2的输出类型与function1的输入类型一定要一致，
         * 否则编译不会通过
         */
        return function1.compose(function2).apply(str1);
    }
    public  static Integer getLength2(String str1,Function<String, String> function1,Function<String,Integer> function2){
        /**
         * 这里一定要注意，function1和function2的参数类型。
         * function1的输出类型与function2的输入类型一定要一致，(方向相反)
         * 否则编译不会通过
         */
        return function1.andThen(function2).apply(str1);
    }
    public static Integer getLength3(String str1,String str2,BiFunction<String, String, String> biFunction,Function<String,Integer> function){
        /**
         * biFunction只有andThen方法，这是有bi类型接口的特征决定的。
         * bi类型的接口需要接收两个参数，然而java中是没有返回两个参数的情况的
         * 所以只有andThen方法，且其参数是function类型的，接收的是一个参数，
         * 返回一个值
         */
        return biFunction.andThen(function).apply(str1, str2);
    }

    /**
     * Consumer接口的andThen方法
     */
    @Test
    public void test5(){
        noResult(Integer.valueOf(12),
                a -> {int num = a + 12;System.out.println(num);},
                b -> { int num = b + 24;System.out.println(num);}
        ); //输出：24,36
    }
    @Test
    public void test6(){
        noResultBi(Integer.valueOf(1),
                Integer.valueOf(5),
                (a,b) -> {int num = b-a+10;System.out.println(num);},
                (a,b) -> {int num =b+a+10;System.out.println(num);});
    }
    public static void noResult(Integer num,Consumer<Integer> consumer1,Consumer<Integer> consumer2){
        /**
         * 两个consumer的接收类型必须一致
         */
        consumer1.andThen(consumer2).accept(num);
    }

    public static void noResultBi(Integer num1,Integer num2,BiConsumer<Integer,Integer> consumer1,BiConsumer<Integer,Integer> consumer2){
        /**
         * 两个consumer的接收类型必须一致
         */
        consumer1.andThen(consumer2).accept(num1,num2);
    }
    /**
     *predicate接口的and、or、negate方法
     */
    @Test
    public void testPredicate(){
        getBoolean("hello", value -> value.length() > 2, value -> value.length() > 6);
    }
    public static boolean getBoolean(String str1,Predicate< String> predicate1,Predicate< String> predicate2){
        boolean test = predicate1.or(predicate2).test(str1);
        System.out.println(test); //输出true &&

        test = predicate1.and(predicate2).test(str1);
        System.out.println(test);//输出false ||

        test = predicate1.negate().test(str1);
        System.out.println(test);//输出false 取反
        return test;
    }
    public static  boolean getBooleanBi(String str1,String str2,BiPredicate<String, String> biPredicate1,BiPredicate<String, String> biPredicate2){
        boolean test = biPredicate1.and(biPredicate2).test(str1, str2);
        test = biPredicate1.negate().test(str1, str2);
        test = biPredicate1.or(biPredicate2).test(str1, str2);
        return test;
    }


}
