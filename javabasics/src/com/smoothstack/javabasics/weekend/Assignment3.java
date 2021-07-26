package com.smoothstack.javabasics.weekend;

import java.util.List;

public class Assignment3 {
	public List<Integer> doubling(List<Integer> nums){
		List<Integer> result = nums.stream().map((i)->i*2).toList();
		return result;
	}
}
