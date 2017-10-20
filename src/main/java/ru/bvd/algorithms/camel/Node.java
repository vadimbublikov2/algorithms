package ru.bvd.algorithms.camel;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Node extends HashMap<Character, Node>  { 
	Set<String> classNameParts = new TreeSet<>();
		
	public Set<String> getClassNameParts() {
		return classNameParts;
	};
	
	public String getFirstClassNamePart() {
		for (String s : classNameParts) {
			return s;
		}
		return "";
	}
	
	public Set<String> getClassNamesByPattern(String pattern) {
		return classNameParts;
	}
}
