package com.dodoi.demo.dataStructure.sort;

import com.dodoi.demo.tools.StrTool;
/**
 * 
 * @author liujia
 * 思路：当插入第i个元素k时，需要与前i-1个已排序的数据元素进行比较，将k插入到适当的位置，使得插入后数据仍是排序的。
 */
public class InsertionSort {

	/**
	 * 插入排序
	 * 
	 * @paramarr
	 * @return
	 */
	private static int[] insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return arr;
		}
		System.out.println("插入排序第0遍："+StrTool.arrToString(arr));
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					// TODO:
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} else {
					// 接下来是无用功
					break;
				}
			}
			System.out.println("插入排序第"+i+"遍："+StrTool.arrToString(arr));
		}
		return arr;
	}
	
	public static void main(String[] args){
		int[] arr = {6,3,5,2,9,1,7,0,8,4};
		insertSort(arr);
	}
}
