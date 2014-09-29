package main.java.com.amooth.patterns;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternReader {

    private BufferedReader bufferedReader;
    private final Map<Integer, List<String[]>> patternsGroupedByLength = new HashMap<Integer, List<String[]>>();
    private final List<String[]> paths = new ArrayList<String[]>();

    public PatternReader() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            loadPatterns();
            loadPaths();
        } catch (IOException e) {
            //Would normally print nice error message, explicitly instructed not to add extra stdout
        }
    }

    private void loadPatterns() throws IOException {
        String patternCountString = bufferedReader.readLine();
        int patternCount = 0;
        try {
            patternCount = Integer.valueOf(patternCountString);
        } catch (NumberFormatException e) {
            //Would normally print nice error message
            //will force an empty List to be returned
        }
        for (int i = 0; i < patternCount; i++) {
            String pattern = bufferedReader.readLine();
            addPatternToMap(pattern.split(","));
        }
    }

    private void loadPaths() throws IOException {
        String pathCountString = bufferedReader.readLine();
        int pathCount = 0;
        try {
            pathCount = Integer.valueOf(pathCountString);
        } catch (NumberFormatException e) {
            //Would normally print nice error message
            //will force an empty List to be returned
        }

        for(int i = 0; i< pathCount; i++) {
            String path = bufferedReader.readLine();
            if(path.startsWith("/")) {
                path = path.substring(1);
            }
            if(path.endsWith("/")) {
                path = path.substring(0, path.length()-1);
            }
            String[] pathArray = path.split("/");
            paths.add(pathArray);

            if(!patternsGroupedByLength.containsKey(pathArray.length)) {
                patternsGroupedByLength.put(pathArray.length, new ArrayList<String[]>());
            }
        }
    }

    private void addPatternToMap(String[] pattern) {
        List<String[]> patterns = patternsGroupedByLength.get(pattern.length);
        if(patterns == null) {
            patterns = new ArrayList<String[]>();
            patternsGroupedByLength.put(pattern.length, patterns);
        }
        patterns.add(pattern);

    }

    public List<String[]> getPatterns(int patternLength) {
        return patternsGroupedByLength.get(patternLength);
    }

    public List<String[]> getPaths() {
        return paths;
    }
}
