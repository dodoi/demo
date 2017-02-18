package com.dodoi.demo.reflect;
public class TestGetClass {
    public static void main(String[] args) throws Exception {
    	Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // 第一种方式；一般采用这种形式
        class1 = Class.forName("com.dodoi.demo.reflect.TestGetClass");
        // 第二种方式；一般采用这种形式
        class2 = new TestGetClass().getClass();
        // 第三种方式；一般采用这种形式
        class3 = TestGetClass.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }
}