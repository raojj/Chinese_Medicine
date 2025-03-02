/*
Java8 新特性简介
    Java 8 (又称为 jdk 1.8) 是 Java 语言开发的一个主要版本。
    Java 8 是oracle公司于2014年3月发布，可以看成是自Java 5 以来最具革命性的版本。Java 8为Java语言、编译器、类库、开发 工具与JVM带来了大量新特性。
Lambda 表达式：语法
    Lambda 表达式：在Java 8 语言中引入的一种新的语法元素和操
    作符。这个操作符为 “->” ， 该操作符被称为 Lambda 操作符或箭头操作符。它将 Lambda 分为两个部分：
    左侧：指定了 Lambda 表达式需要的参数列表
    右侧：指定了 Lambda 体，是抽象方法的实现逻辑，也即Lambda 表达式要执行的功能。
    语法格式一：无参，无返回值
        Runnable r1 = () -> {System.out.println("hello Lambda");};
    语法格式二：Lambda 需要一个参数，但是没有返回值。
        Consumer<String> con = (String str) -> {System.out.println(str);};
    语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
        Consumer<String> con = (str) -> {System.out.println(str);};
    语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
        Consumer<String> con = str -> {System.out.println(str);};
    语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
        Comparator<Integer> com = (x,y)->{
            System.out.println("实现函数接口方法");
            return Integer.compare(x,y);
        };
    语法格式六：当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略
        Comparator<Integer> com = (x,y)-> Integer.compare(x,y);
 */
