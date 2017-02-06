package com.dodoi.demo.dataStructure.sort;

import com.dodoi.demo.tools.StrTool;

public class ShellSort {
	
	private static int[] sellSort(int[] arr){
		if(arr == null || arr.length<=1) {
			return arr;
		}
		int d = arr.length/2;
		/*
		while(true) {
			for(int i=0;i+d<arr.length;i++){
				for(int j=i+d;j<arr.length;j++){
					if(arr[j]<arr[j-d]){
						int temp = arr[j-d];
						arr[j-d] = arr[j];
						arr[j] = temp;
					}
				}
			}
			if(d==1){
				break;
			}
			d--;
		}
		 * */
		
		
		int jump = arr.length / 2;
		 
		while(jump>0){//控制增量
			for(int i=jump;i<arr.length;i++){//一轮比较、交换
				
				for(int j=i-jump;j>=0;j=j-jump){//相隔jump的元素比较交换
					if(arr[j]>arr[j+jump]){ //交换
						int temp = arr[j];
						arr[j] = arr[j+jump];
						arr[j+jump] = temp;
					}else {
						break;
					}
				}
			}
			jump = jump/2;
		}
	    
		return arr;
	}
	public static void main(String[] args){
		int[] arr = {6,3,5,2,9,1,7,0,8,4};
		System.out.println(StrTool.arrToString(sellSort(arr)));
	}
}
