package com.smoothstack.javabasics.weekend;

import java.util.List;

public class Assignment2 {
	public List<Integer> getRight(List<Integer> nums){
		List<Integer> rights = List.copyOf(nums);
		rights = rights.stream().map((i)->i%10).toList();
		return rights;
	}
}
