package com.smoothstack.javabasics.weekend;

import java.util.List;

public class Assignment5 {
	public Boolean groupSumClump(List<Integer> ints, int target){
		//Reached end of list with no target found
		if(ints.size() == 0) {
			return false;
		}
		//Last element of list is not target
		if(ints.get(0) != target && ints.size() == 1) {
			return false;
		}
		//Last element of list is target
		if(ints.get(0) == target && ints.size() == 1) {
			return true;
		}
		//Value is not a clump and is target
		else if(ints.get(0) == target && ints.get(1) != target) {
			return true;
		}
		//Value is bigger than target, does not matter if clumped or not
		else if(ints.get(0) > target) {
			return groupSumClump(ints.subList(1, ints.size()),target);
		}
		Integer clump = ints.get(0);
		Integer clumpSum = clump;
		Integer i = 1;
		//Check size of clump
		while(ints.get(i) == clump) {
			clumpSum += clump;
			i++;
			if(i == ints.size()) {
				break;
			}
		}
		//Clump fits target
		if(clumpSum == target) {
			return true;
		}
		else {
			//Clump is smaller, call self to find whether can be used
			if(groupSumClump(ints.subList(i, ints.size()),target - clumpSum)) {
				return true;
			}
			//Could not find, skip clump
			else {
				return groupSumClump(ints.subList(i, ints.size()),target);
			}
		}
	}
}
