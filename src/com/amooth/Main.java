package com.amooth;

//input edge cases (slashes
//accept file input
//allow for command line input
//output to a file
//mavenize
//put in git repo
//handle exceptions gracefully
//test cases
//invalid(extra /, identical patterns, missing file, all best matches start with a *, )

//perf thoughts
//key value setup by length since shorter or longer doesn't matter
//sort patterns alphabetically?
//turn it into a hash tree lookup

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileInput = args[0];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInput));

        List<String[]> patterns = new ArrayList<String[]>();
        String patternCountString = bufferedReader.readLine();
        int patternCount = Integer.valueOf(patternCountString);
        for(int i = 0; i < patternCount; i++) {
            String pattern = bufferedReader.readLine();
            patterns.add(i, pattern.split(","));
        }

        List<String[]> paths = new ArrayList<String[]>();
        String pathCountString = bufferedReader.readLine();
        int pathCount = Integer.valueOf(pathCountString);

        for(int i = 0; i< pathCount; i++) {
            String path = bufferedReader.readLine();
            if(path.startsWith("/")) {
                path = path.substring(1);
            }
            if(path.endsWith("/")) {
                path = path.substring(0, path.length()-1);
            }
            paths.add(i, path.split("/"));
        }

        for(String[] path : paths) {
            bestPatternMatch(patterns, path);
        }
    }



    private static void bestPatternMatch(List<String[]> patterns, String[] path){
        List<String[]> matchingPatterns = new ArrayList<String[]>();
        for(String[] pattern : patterns) {
            if(pathMatch(pattern, path)) {
                matchingPatterns.add(pattern);
            }
        }

        if(matchingPatterns.isEmpty()) {
            System.out.println("NO MATCH");
        } else {
            String[] bestPattern = breakTies(matchingPatterns);
            for(String subpattern : bestPattern) {
                System.out.print(subpattern);
                System.out.print(","); //TODO: kill trailing comma
            }
            System.out.println();
        }
    }

    private static boolean pathMatch(String[] pattern, String[] path) {
        if(pattern.length != path.length) {
            return false;
        }

        for(int i = 0; i<pattern.length; i++) {
            if(pattern[i].equals("*")) {
                continue;
            }
            if(!pattern[i].equals(path[i])) {
                return false;
            }
        }

        return true;
    }

    private static String[] breakTies(List<String[]> matchingPatterns) {
        List<String[]> bestPatterns = breakTiesByWildcardCount(matchingPatterns);

        if(bestPatterns.size() == 1) {
            return bestPatterns.get(0);
        }

        return breakTiesByLeftmostWildcard(bestPatterns);
    }

    private static List<String[]> breakTiesByWildcardCount(List<String[]> matchingPatterns) {
        int lowestNumberOfWildcards = Integer.MAX_VALUE;
        List<String[]> bestPatterns = new ArrayList<String[]>();
        for(String[] pattern : matchingPatterns) {
            int wildcardCount = 0;
            for(String subpattern : pattern) {
                if(subpattern.equals("*")) {
                    wildcardCount++;
                }
            }

            if(wildcardCount == lowestNumberOfWildcards) {
                bestPatterns.add(pattern);
            }

            if(wildcardCount < lowestNumberOfWildcards) {
                bestPatterns.clear();
                bestPatterns.add(pattern);
                lowestNumberOfWildcards = wildcardCount;
            }
        }

        return bestPatterns;
    }

    private static String[] breakTiesByLeftmostWildcard(List<String[]> matchingPatterns) {
        for(int i = 0; i < matchingPatterns.get(0).length; i++) {
            List<String[]> toEliminate = new ArrayList<String[]>();
            for(String[] possibleBest : matchingPatterns) {
                if(possibleBest[0].equals("*")) {
                    toEliminate.add(possibleBest);
                }
            }
            if(toEliminate.size() == matchingPatterns.size()) { //all pattterns had a wildcard, search for further nested wildcards
                continue;
            }

            matchingPatterns.removeAll(toEliminate);

            if(matchingPatterns.size() == 1) {
                return matchingPatterns.get(0);
            }
        }
        //TODO: at this point we probably received two identical patterns. Returning first.
        return matchingPatterns.get(0);
    }
}
