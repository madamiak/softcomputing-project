package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;
import jess.*;
import pl.wroc.pwr.student.softcomputing.pokerbot.utils.InputReader;

/**
 * Created by RaV on 15.01.14.
 */
public class ExpertSystemMain {
    public static void main (String[] args){
        Rete engine = new Rete();
        Value v=null;
        System.out.println("Welcome to poker expert system.");
        String input = InputReader.readInput();
        while(!input.equals("exit")){
            //if(input.equals("fact")){
            //    input= InputReader.readInput();
                try {
                   v = engine.executeCommand(input);
                    System.out.println(v.toString());
                } catch (JessException e) {
                    e.printStackTrace();
                   // System.exit(-1);
                }
           // }
            input= InputReader.readInput();

        }
    }
}
