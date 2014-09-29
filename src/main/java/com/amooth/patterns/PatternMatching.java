package main.java.com.amooth.patterns;

public class PatternMatching {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        PatternReader patternReader = new PatternReader();

        for(String[] path : patternReader.getPaths()) {
            PatternMatcher patternMatcher = new PatternMatcher(patternReader.getPatterns(path.length), path);

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

        long endTime = System.currentTimeMillis();
        //For performance testing
        //System.out.println("Program took: " + (endTime-beginTime));
    }
}
