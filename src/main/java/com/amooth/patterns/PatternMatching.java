package main.java.com.amooth.patterns;

//allow for command line input
//put in git repo
//test cases( )

//perf thoughts
//key value setup by length since shorter or longer doesn't matter
//sort patterns alphabetically?
//turn it into a hash tree lookup

public class PatternMatching {

    public static void main(String[] args) {
        PatternReader patternReader = new PatternReader();

        for(String[] path : patternReader.getPaths()) {
            PatternMatcher patternMatcher = new PatternMatcher(patternReader.getPatterns(), path);

            if(patternMatcher.getBestPattern() == null) {
               System.out.println("NO MATCH");
            } else {
                String output = "";
                for(String subpattern : patternMatcher.getBestPattern()) {
                    output = output.concat(subpattern);
                    output = output.concat(",");
                }
                output = output.substring(0, output.length()-1); //clear the last comma
                System.out.println(output);
            }
        }
    }
}
