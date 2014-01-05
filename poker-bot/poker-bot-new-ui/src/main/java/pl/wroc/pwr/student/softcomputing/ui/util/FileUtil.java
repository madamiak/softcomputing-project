package pl.wroc.pwr.student.softcomputing.ui.util;

import java.io.*;

/**
 * Created by RaV on 05.01.14.
 */
public class FileUtil {
    public static boolean textToFile(String text, File file){
        String postfix="";
        if(file.toString().lastIndexOf(".")==-1)
            postfix=".txt";
        try {
            FileWriter write = new FileWriter(file+postfix);
            PrintWriter print = new PrintWriter(write);
            text.replaceAll("\\n",System.lineSeparator());
            String[] lines = text.split("\n");
            for(String line : lines){
                print.println(line);
            }
            print.close();
        } catch (IOException e) {
            System.out.println("File saving exception");
            return false;
        }
        return true;
    }
}
