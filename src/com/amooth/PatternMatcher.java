package com.amooth;

import java.util.ArrayList;
import java.util.List;

public class PatternMatcher {

    private final List<String[]> patterns;
    private final String[] path;
    private final List<String[]> matchingPatterns = new ArrayList<String[]>();
    private String[] bestPattern = null;

    public PatternMatcher(List<String[]> patterns, String[] path) {
        this.patterns = patterns;
        this.path = path;

        findBestPatternMatch();
    }

    private void findBestPatternMatch(){
        for(String[] pattern : patterns) {
            if(pathMatch(pattern, path)) {
                matchingPatterns.add(pattern);
            }
        }

        if(matchingPatterns.isEmpty()) {
            return;
        } else if(matchingPatterns.size() == 1) {
            bestPattern = matchingPatterns.get(0);
        } else {
            bestPattern = breakTies(matchingPatterns);
        }
    }

    private boolean pathMatch(String[] pattern, String[] path) {
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

    private String[] breakTies(List<String[]> matchingPatterns) {
        List<String[]> bestPatterns = breakTiesByWildcardCount(matchingPatterns);

        if(bestPatterns.size() == 1) {
            return bestPatterns.get(0);
        }

        return breakTiesByLeftmostWildcard(bestPatterns);
    }

    private List<String[]> breakTiesByWildcardCount(List<String[]> matchingPatterns) {
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

    private String[] breakTiesByLeftmostWildcard(List<String[]> matchingPatterns) {
        for(int i = 0; i < matchingPatterns.get(0).length; i++) {
            List<String[]> toEliminate = new ArrayList<String[]>();
            for(String[] possibleBest : matchingPatterns) {
                if(possibleBest[i].equals("*")) {
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

    public String[] getBestPattern() {
        return bestPattern;
    }
}
