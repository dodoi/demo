package com.dodoi.demo.jvm;

/**
 * 虚拟机栈和本地方法栈
 * @author liujia VM Args: -Xss2m(可以设置大点) Xss:栈容量
 */
public class JavaVMStackOOM {

	private void dodo() {
		while (true) {

		}
	}

	public void stackLeakByThread() {

		while (true) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					dodo();
				}

			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();

	}

}
/*
 * Exception in thread "main" java.lang.StackOverflowError at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15) at
 * com.dodoi.demo.jvm.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 */
