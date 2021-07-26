package com.smoothstack.javabasics.weekend;

import java.util.List;

public class Assignment5 {
	public Boolean groupSumClump(List<Integer> ints, int target){
		if(ints.size() == 0) {
			return false;
		}
		if(ints.get(0) != target && ints.size() == 1) {
			return false;
		}

		if(ints.get(0) == target && ints.size() == 1) {
			return true;
		}
		else if(ints.get(0) == target && ints.get(1) != target) {
			return true;
		}
		else if(ints.get(0) > target) {
			return groupSumClump(ints.subList(1, ints.size()),target);
		}
		Integer clump = ints.get(0);
		Integer clumpSum = clump;
		Integer i = 1;
		while(ints.get(i) == clump) {
			clumpSum += clump;
			i++;
		}
		if(clumpSum == target) {
			return true;
		}
		else {
			if(groupSumClump(ints.subList(i, ints.size()),target - clumpSum)) {
				return true;
			}
			else {
				return groupSumClump(ints.subList(i, ints.size()),target);
			}
		}
	}
}
