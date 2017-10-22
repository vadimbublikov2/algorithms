package ru.bvd.algorithms.camel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ClassFinder {
	public static void main(String[] args) throws IOException {
		String pattern = "FBar";
        //String pattern = "FooBar";
        //String pattern = "Bar";
		Stream<String> stream = Files.lines(Paths.get("src/main/resources/classes.txt"));

        List<String> spitPattern = splitPattern(pattern);
        //System.out.println(String.join("-",spitPattern));

        stream.forEach(line -> {
			List<String> lClassName = splitClassName(line);

            while (!lClassName.isEmpty() && lClassName.size() >= spitPattern.size()) {
                boolean res = checkRecursive(spitPattern, lClassName, 0);
                if (res) {
                    System.out.println(line + ":" + res);
                    System.out.println();
                    break;
                }
                lClassName.remove(0);
            }
        });

	}

    private static boolean check(String patternWithSpace, String className) {
        String pattern = patternWithSpace.replace(" ", "");
	    if (!pattern.contains("*")) {
            return className.startsWith(pattern);
        } else {
            for (int i = 0; i < className.length(); i++) {
                boolean matched = true;
                boolean wildcarded = false;
                for (int j = 0; j < pattern.length(); j++) {
                    if (className.length() <= i + j) return false;
                    char r = pattern.charAt(j);
                    char c = className.charAt(i + j);
                    if (r == '*') {
                        wildcarded = true;
                    } else if (r==c) {
                        wildcarded = false;
                        continue;
                    } else {
                        if (wildcarded) {
                            continue;
                        } else {
                            matched = false;
                            break;
                        }
                    }
                }

                if (matched) {
                    return true;
                } else {
                    continue;
                }
            }
            return false;
        }
    };



	private static boolean checkRecursive(List<String> pattern, List<String> className, int index) {
        if (pattern.size() == index) {
            if (pattern.size() == className.size()) {
                return true;
            } else if (pattern.get(index-1).endsWith(" "))  {
                return false;
            } else {
                return true;
            }

        }
        if (!check(pattern.get(index), className.get(index)))
            return false;

        System.out.println(pattern.get(index) + " = " + className.get(index));

	    return checkRecursive(pattern,className,index+1);
    }


	private static List<String> splitClassName(String line) {
		List<String> linkClassName = new LinkedList<>();
		splitClassNameRecursive(linkClassName, line.trim().substring( Math.max(0, line.lastIndexOf(".")) ));
		Collections.reverse(linkClassName);
		return linkClassName;
	}

	private static int splitClassNameRecursive(List<String> splitName, String className) {
		int beginIndex = 0;
		for (; beginIndex < className.length(); beginIndex++) {
			if (Character.isUpperCase(className.charAt(beginIndex))) {
				int offset = splitClassNameRecursive(splitName, className.substring(beginIndex+1));
				splitName.add( className.substring(beginIndex, beginIndex+offset+1) );
				return beginIndex;
			}
		}
		return beginIndex;
	}

    private static List<String> splitPattern(String patternOriginal) {
	    String pattern =
                patternOriginal.endsWith("*") ? patternOriginal.substring(0,patternOriginal.length()-2) : patternOriginal;
	    //pattern = "*" + pattern;

        List<String> splitPatterns = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for ( char c : pattern.toCharArray() ) {
            if ( Character.isUpperCase(c) && stringBuilder.length()>0 ) {
                splitPatterns.add( stringBuilder.toString() );
                stringBuilder.setLength(0);
            }
            stringBuilder.append(c);
        }

        if (splitPatterns.isEmpty() && !Character.isUpperCase(pattern.charAt(0))) {
            String patternEnd = null;
            String patternClean;
            if (pattern.endsWith(" ")) {
                patternEnd = pattern.substring(pattern.length()-2).toUpperCase();
                patternClean = pattern.substring(0, pattern.length()-2);
            } else {
                patternClean = pattern;
            }

            if (patternClean.length()>0) splitPatterns.addAll( Arrays.asList(patternClean.toUpperCase().split("")) );
            if (patternEnd != null) {
                splitPatterns.add(patternEnd);
            }

            return splitPatterns;
        }

        if (stringBuilder.length()>0)
            splitPatterns.add( stringBuilder.toString() );

        return splitPatterns;
    }

}
