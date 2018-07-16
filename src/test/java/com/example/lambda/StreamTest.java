package com.example.lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/7/13 16:31
 */
public class StreamTest {
    /**
     *  * Stream 是用函数式编程方式在集合类上进行复杂操作的工具，
     *  * 其集成了Java 8中的众多新特性之一的 聚合操作，
     *  * 开发者可以更容易地使用Lambda表达式，并且更方便地实现对集合的查找、遍历、过滤以及常见计算等。
     */
    @Test
    public void test1(){
        List<Person> personList =Arrays.asList(
                new Person("a","A",1),
                new Person("b","B",2),
                new Person("c","C",3),
                new Person("d","B",3)
        );
        //原生遍历  首先调用iterator方法，产生一个新的Iterator对象，进而控制整
        //个迭代过程，这就是外部迭代 迭代过程通过显式调用Iterator对象的hasNext和next方法完成迭代
        for (Iterator<Person> iterator = personList.iterator(); iterator.hasNext(); ) {
            Person next =  iterator.next();
            if (next.getLastName().equals("B")) {
                System.out.println(next.toString());
            }
        }
        /*迭代器提供next()、hasNext()等方法，开发者可以自行控制对元素的处理，以及处理方式，但是只能顺序处理；
        stream()方法返回的数据集无next()等方法，开发者无法控制对元素的迭代，迭代方式是系统内部实现的，
        同时系统内的迭代也不一定是顺序的，还可以并行，如parallelStream()方法。并行的方式在一些情况下，可以大幅提升处理的效率。*/
        // Java 8写法 聚合操作是Java 8针对集合类，使编程更为便利的方式 通过stream方法创建Stream，然后再通过filter方法对源数据进行过滤，最后通过foeEach方法进行迭代。
        personList.stream()
                .filter(person -> person.getFirstName().equals("G"))
                .forEach(student -> System.out.println(student.toString()));
    }
    /**Stream三步走策略
     * 1、创建Stream:通过stream()方法，取得集合对象的数据集。
     * 2、Intermediate(通过一系列中间（Intermediate）方法，对数据集进行过滤、检索等数据集的再次处理。)：
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered
     * 3、Terminal(方法完成对数据集中元素的处理)：
     * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator
     *
     * Short-circuiting：  anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */
    public void test2(){
        List<Person> personList =Arrays.asList(
                new Person("a","A",1),
                new Person("b","B",2),
                new Person("c","C",3),
                new Person("d","B",3)
        );
        long count = personList.stream()
                .filter(artist -> {
                    System.out.println(artist.getFirstName());
                    return artist.getAge()>1;
                })
                .count();
    }
    /**创建Stream的三种方式
     * 1、Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；
     * 2、Collection接口和数组的默认方法（默认方法,也使Java的新特性之一，后续介绍），把一个Collection对象转换成Stream
     * 3、其他： Random.ints()、 BitSet.stream()、Pattern.splitAsStream(java.lang.CharSequence)、JarFile.stream()
     */
    @Test
    //静态工厂方法
    public void test3(){
//        - of(T... values)：返回含有多个T元素的Stream
//        - of(T t)：返回含有一个T元素的Stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<String> stringStream = Stream.of("A");

        integerStream.filter(integer -> integer>2).forEach(integer -> System.out.println(integer));
//        - generate(Supplier<T> s)：返回一个无限长度的Stream
        Stream<Double> generateA = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return java.lang.Math.random();
            }
        });
        Stream<Double> generateB = Stream.generate(()-> java.lang.Math.random());
        Stream<Double> generateC = Stream.generate(java.lang.Math::random);
        generateA.forEach(aDouble -> System.out.println(aDouble));
//        - iterate(T seed, UnaryOperator<T> f) 该Stream也是无限长度的，应该使用filter、limit等来截取Stream，否则会一直循环下去。
        Stream.iterate(1, item -> item + 1)
                .limit(10)
                .forEach(System.out::println);
        // 打印结果：1，2，3，4，5，6，7，8，9，10
    }
    /**
     * 其他创建stream
     */
    public void test4(){
//        concat方法将两个Stream连接在一起，合成一个Stream。
        Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5))
                .forEach(integer -> System.out.print(integer + "  "));
        // 打印结果  1  2  3  4  5
//        distinct方法以达到去除掉原Stream中重复的元素，生成的新Stream中没有没有重复的元素
        Stream.of(1,2,3,1,2,3)
                .distinct()
                .forEach(System.out::println); // 打印结果：1，2，3
//        filter方法对原Stream按照指定条件过滤，在新建的Stream中，只包含满足条件的元素，将不满足条件的元素过滤掉。
        Stream.of(1, 2, 3, 4, 5)
                .filter(item -> item > 3)
                .forEach(System.out::println);// 打印结果：4，5
//        map方法将对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素
//        map传入的Lambda表达式必须是Function实例，参数可以为任意类型，而其返回值也是任性类型，javac会根据实际情景自行推断。
        Stream.of("a", "b", "hello")
                .map(item-> item.toUpperCase())
                .forEach(System.out::println); // 打印结果  A, B, HELLO
//        flatMap方法与map方法类似，都是将原Stream中的每一个元素通过转换函数转换，不同的是，该换转函数的对象是一个Stream，也不会再创建一个新的Stream，而是将原Stream的元素取代为转换的Stream
        Stream.of(1, 2, 3)
                .flatMap(integer -> Stream.of(integer * 10))
                .forEach(System.out::println);// 打印结果 10，20，30
        Stream.of(1, 2, 3, 4, 5)
                .peek(integer -> System.out.println("accept:" + integer))
                .forEach(System.out::println);
                // 打印结果accept:1     1    accept:2    2   accept:3    3    accept:4     4   accept:5    5
        Stream.of(1, 2, 3,4,5)
                .skip(2)
                .forEach(System.out::println);
            // 打印结果 3,4,5
        Stream.of(5, 4, 3, 2, 1)
                .sorted()
                .forEach(System.out::println);
        // 打印结果     // 1，2，3,4,5
        Stream.of(1, 2, 3, 4, 5)
                .sorted((o1, o2) -> o1)
                .forEach(System.out::println);
        // 打印结果     // 5, 4, 3, 2, 1
    }
    /**
     * collector    收集器
     */
    public void test5(){
        List<Integer> collectList = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());
        System.out.println("collectList: " + collectList);
        // 打印结果
        // collectList: [1, 2, 3, 4]
        Optional<Integer> collectMaxBy = Stream.of(1, 2, 3, 4)
                .collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
        System.out.println("collectMaxBy:" + collectMaxBy.get());
        // 打印结果  maxBy:在指定条件下的，Stream的最大元素
        // collectMaxBy:4
    }


}
