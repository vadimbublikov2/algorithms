package ru.bvd.algorithms.camel;

import java.util.*;


public class Node extends HashMap<Character, Node>  { 
	Set<String> classNameParts = new TreeSet<>();
		
	public Set<String> getClassNameParts() {
		return classNameParts;
	};
	
	public List<String> getFirstClassNamePart() {
		List<String> res = new ArrayList<>();
		for (String s : classNameParts) {
			res.add(s);
			break;
		}
		return res;
	}
	
	public Set<String> getClassNamesByPattern(String pattern) {
		return classNameParts;
	}
}
