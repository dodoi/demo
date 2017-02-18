package com.dodoi.demo.reflect;

import java.lang.reflect.Field;

public class TestFieldUse {
	private String proprety = null;

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.dodoi.demo.reflect.TestFieldUse");
		Object obj = clazz.newInstance();
		// 可以直接对 private 的属性赋值
		Field field = clazz.getDeclaredField("proprety");
		field.setAccessible(true);
		field.set(obj, "Java反射机制");
		System.out.println(field.get(obj));
	}
}
