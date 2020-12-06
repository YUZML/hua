package com.zsh.common.collection;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        //创建流的几种方式
        //1. 使用数组
        int[] a = new int[]{1, 2};
        String[] b = new String[]{"zhao", "shuai"};
        Arrays.stream(a).forEach(System.out::println);
        //result：1
        //      2
        //2. 使用Collections
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.stream().forEach(System.out::println);
        //result：aaa
        //bbb
        //3. 通过Stream.of方法直接传入多个元素构成一个流,只能接收String数组和多个String字符串，
//        Stream.of(a).forEach(System.out::println);
//        Stream.of(list).forEach(System.out::println);
//        result: [I@6be46e8f
//                [aaa, bbb]
        Stream.of(b).forEach(System.out::println);
        Stream.of("hello", "world").forEach(System.out::println);
        //result:zhao
        //shuai
        //hello
        //world
        //4. 通过Stream.iterate方法使用迭代的方式构造一个无限流，然后使用limit限制流元素个数
        Stream.iterate(2, item -> item * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10).forEach(System.out::println);
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
}
