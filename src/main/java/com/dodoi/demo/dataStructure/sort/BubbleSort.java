package com.dodoi.demo.dataStructure.sort;

import com.dodoi.demo.tools.StrTool;

/**
 * 
 * @author liujia
 *         思路：将相邻两个数据元素进行比较，如果反序，则交换，对于一个数据序列经过一趟后，最大的数据移到最后以为，其他的较大的数据也向后移动，
 *         此过程叫冒泡
 */
public class BubbleSort {

	private static int[] bubbleSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}
		boolean change = false; // 优化
		for (int i = 1; i < arr.length; i++) {
			change = false;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					change = true;
				}
			}
			if (!change) {
				break;
			}
		}

		return arr;
	}

	public static void main(String[] args) {
		int[] arr = { 6, 3, 5, 2, 9, 1, 7, 0, 8, 4 };
		System.out.println(StrTool.arrToString(bubbleSort(arr)));
	}
}
