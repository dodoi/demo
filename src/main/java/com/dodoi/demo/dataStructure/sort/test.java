package com.dodoi.demo.dataStructure.sort;

import com.dodoi.demo.tools.StrTool;

public class test {
	private static int[] insertSort(int[] arr){
		if( arr==null || arr.length <= 1){
			return arr;
		}
		for(int i=1;i<arr.length;i++){
			for(int j=i;j>0;j--){
				if(arr[j]<arr[j-1]){
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}else {
					//优化
					break;
				}
			}
		}
		
		return arr;
	}
	public static void main(String[] args){
		int[] arr = {6,3,5,2,9,1,7,0,8,4};
		System.out.println(StrTool.arrToString(insertSort(arr)));
	}
}
