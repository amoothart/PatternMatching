package com.amooth;

//allow for command line input
//output to a file
//mavenize
//put in git repo
//test cases( )

//perf thoughts
//key value setup by length since shorter or longer doesn't matter
//sort patterns alphabetically?
//turn it into a hash tree lookup

public class PatternMatching {

    public static void main(String[] args) {
        PatternReader patternReader = new PatternReader(args[0]);

        for(String[] path : patternReader.getPaths()) {
            PatternMatcher patternMatcher = new PatternMatcher(patternReader.getPatterns(), path);

            if(patternMatcher.getBestPattern() == null) {
               System.out.println("NO MATCH");
            } else {
                for(String subpattern : patternMatcher.getBestPattern()) {
                    System.out.print(subpattern);
                    System.out.print(","); //TODO: kill trailing comma
                }
                System.out.println();
            }
        }
    }
}
