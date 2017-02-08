package com.dodoi.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * java堆溢出
 * @author liujia
 * VM Args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

	static class OOMOjbect{
		
	}
	public static void main(String[] args){
		List<OOMOjbect> list = new ArrayList<OOMOjbect>();
		while(true){
			list.add(new OOMOjbect());
		}
	}
}
/*
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid5101.hprof ...
Heap dump file created [27715955 bytes in 0.135 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at com.dodoi.demo.jvm.HeapOOM.main(HeapOOM.java:19)
 * */
