package pl.wroc.pwr.student.ui.decisions;

import pl.wroc.pwr.student.softcomputing.pokerbot.utils.ChainingData;
import pl.wroc.pwr.student.ui.utils.KeyPresser;
import pl.wroc.pwr.student.ui.utils.PokerTableUtil;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by RaV on 14.09.14.
 */
public class DecisionMaker {

    private static final int PROVEN_CAPO = 40;
    private static final int UNPROVEN_CAPO = 500;
    private static final int TIME_CONSTANT = 4;
    private static final double FOLD_SCALAR = 0.6;

    private static boolean changed = false;

    private DecisionMaker() {
    }

    public static void makeDecision(ChainingData chainingData){
        changed = false;
        try {
            sleep(secToMilis(calculateTime(chainingData)));
            focusTable();
            if(chainingData.isPositive())KeyPresser.allin();
            else KeyPresser.fold();
        } catch (InterruptedException e) {
            System.out.println("Sleep exception.");
            e.printStackTrace();
        }
    }

    public static void justFold(){
        try {
            sleep(secToMilis(calculateRandomTime()));
            focusTable();
            if(!changed)KeyPresser.fold();
        } catch (InterruptedException e) {
            System.out.println("Sleep exception.");
            e.printStackTrace();
        }
    }


    public static void changed(){
        changed=true;
    }

    private static long secToMilis(double sec){
        return (long)(sec*1000);
    }

    private static double calculateTime(ChainingData chainingData) {
        double gaussian = getGaussianRandom();
        double proven = getProvenValue(chainingData);
        double time = TIME_CONSTANT * gaussian * proven;
        System.out.println(String.format("Time = %f, %d*%f*%f",time,TIME_CONSTANT,gaussian,proven));
        return time;
    }

    private static double calculateRandomTime() {
        double gaussian = getGaussianRandom();
        double time = TIME_CONSTANT * gaussian * 0.5;
        System.out.println(String.format("Random time = %f, %d*%f*0.5",time,TIME_CONSTANT,gaussian));
        return time;
    }

    private static double getProvenValue(ChainingData chainingData) {
        if(chainingData.isPositive()){
            return chainingData.getUnprovenCount() > UNPROVEN_CAPO ? 1 : (double)chainingData.getUnprovenCount()/ UNPROVEN_CAPO;
        }
        return chainingData.getProvenCount() > PROVEN_CAPO ? 1 : ((double)chainingData.getProvenCount()/ PROVEN_CAPO)*FOLD_SCALAR;
    }

    private static Double getGaussianRandom() {
        Random random = new Random();
        Double gaussian =(random.nextGaussian()+1)/8+0.4;
        return gaussian>0.1 ? gaussian : 0.1;
    }
    private static void focusTable() {
        PokerTableUtil.focusPokerTable();
    }

}
