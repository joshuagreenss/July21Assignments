package com.smoothstack.javabasics.weekend;

import java.util.ArrayList;
import java.util.List;

public class Assignment4 {
	public List<String> noX(List<String> strings){
		List<String> vals = new ArrayList<>();
		StringBuilder clean;
		for(String s : strings) {
			clean = new StringBuilder();
			for(char c : s.toCharArray()) {
				if(c != 'x') {
					clean.append(c);
				}
			}
			if(clean.length() != 0) {
				vals.add(clean.toString());
			}
		}
		return vals;
	}
}
