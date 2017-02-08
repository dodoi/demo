package com.dodoi.demo.jvm;

/**
 * 虚拟机栈和本地方法栈
 * @author liujia
 * VM Args: -Xss256k
 * Xss:栈容量
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public  void stackLeak(){
		stackLength ++;
		stackLeak();
	}
	public static void main(String[] args){
		JavaVMStackSOF sof = new JavaVMStackSOF();
		try{
			sof.stackLeak();	
		}catch(Exception e){
			System.out.println("stackLength:"+sof.stackLength);
			throw e;
		}
		
	}

}
/*
Exception in thread "main" java.lang.StackOverflowError
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
	at com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 * */
