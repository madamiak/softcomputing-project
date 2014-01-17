package pl.wroc.pwr.student.softcomputing.pokerbot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by RaV on 13.01.14.
 */
public class InputReader {
    public static String readInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = null;
        try {
            input = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read input!");
            System.exit(1);
        }
        return  input;
    }
}
