package com.smoothstack.javabasics.weekend;

import java.util.List;

public class Assignment3 {
	//Maps *2 to each integer in list
	public List<Integer> doubling(List<Integer> nums){
		List<Integer> result = nums.stream().map((i)->i*2).toList();
		return result;
	}
}
