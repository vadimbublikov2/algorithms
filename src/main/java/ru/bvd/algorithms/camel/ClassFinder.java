package ru.bvd.algorithms.camel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ClassFinder {
	Node root = new Node();
		
	public static void main(String[] args) throws IOException {
		String pattern = "F*iIn*Str ";
		
		ClassFinder classFinder = new ClassFinder();
		
		Stream<String> stream = Files.lines(Paths.get("src/main/resources/classes.txt"));

		stream.forEach(line -> {
        	classFinder.addClassNameRecursive(classFinder.root, line.trim().substring( Math.max(0, line.lastIndexOf(".")) ));
        });
		
		List<String> sp = classFinder.splitPattern(pattern);
		List<String> includesAll = new ArrayList<>();

		//includesAll.addAll( 
		String s = classFinder.extractIncludesRecursive(classFinder.root);
		System.out.println(s);
		//		);
		
		includesAll.stream().forEach(System.out::println);
	}
		
	private String extractIncludesRecursive (Node node) {
		if (node.isEmpty())
			return node.getFirstClassNamePart();
		String s = null;
		for ( Map.Entry<Character, Node> entry : node.entrySet() ) {
			s = extractIncludesRecursive(entry.getValue());
			break;
		}
		
		return s;
	}
	
	private int addClassNameRecursive (Node node, String className) {
		int beginIndex = 0;
		for (; beginIndex < className.length(); beginIndex++) {
			if (Character.isUpperCase(className.charAt(beginIndex))) {
				Character firstLetter = className.charAt(beginIndex);
				int offset;
				Node targetNode = node.get(firstLetter);
				if (targetNode == null) {
					targetNode = new Node();
					node.put(firstLetter, targetNode);
				}	
				offset = addClassNameRecursive(targetNode, className.substring(beginIndex+1));
				//System.out.println(className.substring(beginIndex, beginIndex+offset+1) );
				targetNode.getClassNameParts().add( className.substring(beginIndex, beginIndex+offset+1) );
				
				return beginIndex;
			}			
		}
		//System.out.println("");		
		return beginIndex;
	}
	
	private List<String> splitPattern(String pattern) {        				
		List<String> splitPatterns = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		for ( char c : pattern.toCharArray() ) {
        	if ( Character.isUpperCase(c) && stringBuilder.length()>0 ) {
        		splitPatterns.add( stringBuilder.toString() );
        		stringBuilder.setLength(0);
        	}
        	stringBuilder.append(c);
        }	
		if (stringBuilder.length()>0)
			splitPatterns.add( stringBuilder.toString() );
		
		return splitPatterns;
	}
	
}
