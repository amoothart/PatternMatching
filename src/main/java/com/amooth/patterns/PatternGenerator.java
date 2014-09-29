package main.java.com.amooth.patterns;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class PatternGenerator {
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("generated_input.txt", "UTF-8");

        printWriter.println("100000");
        for(int i = 0; i<100000; i++) {
            Random random = new Random();
            int depth = random.nextInt(5) + 1;
            String pattern = "";

            for(int j = 0; j < depth; j++) {
                int useStar = random.nextInt(7);
                if(useStar == 0) {
                    pattern = pattern.concat("*");
                } else {
                    pattern = pattern.concat(RandomStringUtils.randomNumeric(2));
                }
                pattern = pattern.concat(",");
            }
            pattern = pattern.substring(0, pattern.length()-1);

            printWriter.println(pattern);
        }

        printWriter.println("100000");
        for(int i = 0; i<100000; i++) {
            Random random = new Random();
            int depth = random.nextInt(5) + 1;
            String path = "";

            int useSlashBegin = random.nextInt(4);
            if(useSlashBegin == 0) {
                path = path.concat("/");
            }

            for(int j = 0; j < depth; j++) {
                path = path.concat(RandomStringUtils.randomNumeric(2));
                path = path.concat("/");
            }

            int useSlashEnd = random.nextInt(4);
            if(useSlashEnd != 0) {
                path = path.substring(0, path.length()-1);
            }

            printWriter.println(path);
        }
        printWriter.close();
    }
}
