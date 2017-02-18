package com.dodoi.demo.reflect;
public class TestPackageName {
    public static void main(String[] args) throws Exception {
        TestPackageName testReflect = new TestPackageName();
        System.out.println(testReflect.getClass().getName());
        // 结果 com.dodoi.demo.reflect.TestReflect
    }
}