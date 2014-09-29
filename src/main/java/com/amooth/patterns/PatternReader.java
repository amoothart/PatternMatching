package main.java.com.amooth.patterns;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatternReader {

    private BufferedReader bufferedReader;
    private final List<String[]> patterns = new ArrayList<String[]>();
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
            //Would normally print nice error message, explicitly instructed not to add extra stdout
            //will force an empty List to be returned
        }
        for (int i = 0; i < patternCount; i++) {
            String pattern = bufferedReader.readLine();
            patterns.add(i, pattern.split(","));
        }
    }

    private void loadPaths() throws IOException {
        String pathCountString = bufferedReader.readLine();
        int pathCount = 0;
        try {
            pathCount = Integer.valueOf(pathCountString);
        } catch (NumberFormatException e) {
            //Would normally print nice error message, explicitly instructed not to add extra stdout
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
            paths.add(i, path.split("/"));
        }
    }

    public List<String[]> getPatterns() {
        return patterns;
    }

    public List<String[]> getPaths() {
        return paths;
    }
}
