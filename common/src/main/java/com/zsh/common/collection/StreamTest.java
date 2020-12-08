package com.zsh.common.collection;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        testStream();
    }



    private static void test() {
        //创建流的几种方式
        //1. 使用数组
        int[] a = new int[]{1, 2};
        String[] b = new String[]{"zhao", "shuai"};
        Arrays.stream(a).forEach(System.out::println);
        System.out.println("==================");
        //result：1
        //      2
        //2. 使用Collections
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.stream().forEach(System.out::println);
        System.out.println("==================");
        //result：aaa
        //bbb
        //3. 通过Stream.of方法直接传入多个元素构成一个流,只能接收String数组和多个String字符串，
//        Stream.of(a).forEach(System.out::println);
//        Stream.of(list).forEach(System.out::println);
//        result: [I@6be46e8f
//                [aaa, bbb]
        Stream.of(b).forEach(System.out::println);
        Stream.of("hello", "world").forEach(System.out::println);
        System.out.println("==================");
        //result:zhao
        //shuai
        //hello
        //world
        //4. 通过Stream.iterate方法使用迭代的方式构造一个无限流，然后使用limit限制流元素个数
        Stream.iterate(2, item -> item * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10).forEach(System.out::println);
        System.out.println("==================");
//       result: 2
//        4
//        8
//        ...
//        512
//        1024
//        0
//        10
//        20
//        ...
//        80
//        90
        //5. 通过Stream.generate方法从外部传入一个提供元素的Supplier来构造无限流，然后使用limit限制流元素个数
        //   流里面的元素都是一样的，都是Supplier提供的值，适用于常量的流或随机数的流
        Stream.generate(() -> "test").limit(3).forEach(System.out::println);
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
        System.out.println("==================");
//        result: test
//                test
//        test
//        0.8439390947219921
//        0.5440491694821437
//        0.6106531987624637
        //6. 通过IntStream或DoubleStream构造基本类型的流
        //其中range和rangeClosed的区别是循环范围的右区间是否闭包
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        IntStream.range(0, 3).mapToObj(i -> "x").forEach(System.out::println);
        DoubleStream.of(1.1, 2.2, 3.3).forEach(System.out::println);
        System.out.println("==================");
//        result: 1
//        2
//        1
//        2
//        3
//        x
//        x
//        x
//        1.1
//        2.2
//        3.3
        //7. 使用流行的APIs, 如Pattern.compile().splitAsStream()
        String sentence = "ma zhi chu is a Java wechat official account.";
        Pattern.compile("\\W").splitAsStream(sentence).forEach(System.out::println);
        System.out.println("==================");
//        result: ma
//                zhi
//                chu
//                is
//                a
//                Java
//                wechat
//                official
//                account
    }



    private static void testStream() {
// 流是Java API的新成员，它允许以声明性方式处理数据集合
// （通过查询语句来表达，而不是临时编写一个实现）。
// 就现在来说，可以把它们看成遍历数据集的高级迭代器。
// 此外，流还可以透明地并行处理，无需写任何多线程代码了！
//        流的使用一般包括三件事：
//•一个数据源（如集合）来执行一个查询；
//•一个中间操作链，形成一条流的流水线；
//•一个终端操作，执行流水线，并能生成结果。

//可以使用filter、distinct、skip和limit对流做筛选和切片。
//•可以使用map和flatMap提取或转换流中的元素。
//•可以使用findFirst和findAny方法查找流中的元素。
//      可以用allMatch、noneMatch和anyMatch方法让流匹配给定的谓词。
//•这些方法都利用了短路：找到结果就立即停止计算；没有必要处理整个流。
//•可以利用reduce方法将流中所有的元素迭代合并成一个结果，例如求和或查找最大元素。
//•filter和map等操作是无状态的，它们并不存储任何状态。
//      reduce等操作要存储状态才能计算出一个值。
//      sorted和distinct等操作也要存储状态，
//      因为它们需要把流中的所有元素缓存起来才能返回一个新的流。
//      这种操作称为有状态操作。
//
//•流有三种基本的原始类型特化：IntStream、DoubleStream和LongStream。它们的操作也有相应的特化。
//•流不仅可以从集合创建，也可从值、数组、文件以及iterate与generate等特定方法创建。
//•无限流是没有固定大小的流。

        //1. filter 中间操作
        //          该操作会接受一个谓词（一个返回boolean的函数）作为参数，
        //          并返回一个包括所有复合谓词的元素的流
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,4,2,5);
        list.stream().filter(i -> i > 3).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("==================");
//       result: 4
//        5
//        6
//        4
//        5
        //2. distinct 中间操作
        //            返回一个元素各异（根据流所生成元素的hashcode和equals方法实现）的流
        list.stream().filter(i -> i > 3).distinct().forEach(System.out::println);
        System.out.println("====================");
//        result:  4
//        5
//        6
        //3. limit  中间操作
        //          会返回一个不超过给定长度的流
        list.stream().filter(i -> i > 3).limit(2).forEach(System.out::println);
        System.out.println("====================");
//      result:  4
//        5
        //4. skip   中间操作
        //          返回一个扔掉了前n个元素的流
        list.stream().skip(6).forEach(System.out::println);
        System.out.println("====================");
//       result:   4
//        2
//        5
        //5.  map   中间操作
        //          接受一个函数作为参数。这个函数会被应用到每个元素上
        //          并将其映射成一个新的元素（使用映射一词，是因为它和
        //          转换类似，但其中的细微差别在于它是“创建一个新版本”
        //          而不是去“修改”）
        list.stream().limit(3).map(i -> i+ Math.random()).forEach(System.out::println);
        System.out.println("==================");
//       result:    1.2206558999590897
//        2.091248494886778
//        3.967410391131113
        //6.  flatMap  中间操作
        //             使用flatMap方法的效果是，各个数组并不是分别映射成一个流，
        //             而是映射成流的内容。所有使用map(Array::stream)时生成的
        //             单个流都被合并起来，即扁平化为一个流
        List<String> words = Arrays.asList("Goodbye", "world");
        words.stream().map(i -> i.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
        System.out.println("=======================");
//      result  G o d b y e w r l
        //7. sorted   中间操作
        //            返回排序后的流
        list.stream().skip(4).sorted().forEach(System.out::println);
        System.out.println("===================");
//       result:   2
//        4
//        5
//        5
//        6
        //8.  anyMatch   终端操作
        //               可以回答“流中是否有一个元素能匹配给定的谓词”。
        System.out.println(list.stream().anyMatch(i -> i>4));
        System.out.println("======================");
//      result:  true
        //9.  allMatch   终端操作
        //               会看看流中的元素是否都能匹配给定的谓词
        System.out.println(list.stream().allMatch(i -> i<5));
        System.out.println("=================");
//       rsult:  false
        //10.  noneMatch   终端操作
        //                可以确保流中没有任何元素与给定的谓词匹配
        System.out.println(list.stream().noneMatch(i -> i>10));
        System.out.println("========================");
        //result:   true
        //11.  findAny   终端操作
        //               将返回当前流中的任意元素
        Optional<Integer> any = list.stream().skip(4).findAny();
        System.out.println(any.get());
        System.out.println("====================");
        //result:  5
        //12.  findFirst  终端操作
        //                有些流有一个出现顺序（encounter order）来指定
        //                流中项目出现的逻辑顺序（比如由List或排序好的数据列生成的流）。
        //                对于这种流，可能想要找到第一个元素。
        Optional<Integer> first = list.stream().sorted().skip(5).findFirst();
        System.out.println(first.get());
        System.out.println("==================");
        //result:  4
        //13.  forEach   终端操作
        //               遍历流
        list.stream().skip(5).limit(2).forEach(System.out::println);
        System.out.println("========================");
//       result:   6
//        4
        //14.  collect   终端操作
        //               收集器
        System.out.println(list.stream().skip(4).collect(Collectors.toList()).toString());
        System.out.println("=====================");
        //result:  [5, 6, 4, 2, 5]
        //15.  reduce    终端操作
        //               归约reduce接受两个参数：
        //               •一个初始值，这里是0；
        //               •一个BinaryOperator<T>来将两个元素结合起来产生一个新值，
        //                  这里我们用的是lambda (a, b) -> a + b。
        System.out.println(list.stream().reduce(0, (a, b) -> (a+b)));
        System.out.println("====================");
        //result:  32
        //16.  count    终端操作
        //              返回此流中元素的计数
        System.out.println(list.stream().filter(i -> i>3).count());
        System.out.println("==================");
        //result:  5
    }


}
