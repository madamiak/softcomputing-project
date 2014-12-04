package pl.wroc.pwr.student.ui.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

/**
 * Created by RaV on 20.09.14.
 */
public class KeyPresser {
    private static Robot robot;
    private static Random random;
    private static final int ALLIN_KEY = KeyEvent.VK_A;
    private static final int FOLD_KEY = KeyEvent.VK_D;

    static {
        random = new Random();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private KeyPresser() {
    }

    public static void allin(){
        try{
            System.out.println("Going all-in.");
            pressShortcut(ALLIN_KEY);
            System.out.println("Went all-in.");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fold(){
        try{
            System.out.println("Going to fold.");
            pressShortcut(FOLD_KEY);
            System.out.println("Folded.");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pressShortcut(int key) throws InterruptedException {
        robot.keyPress(key);
        sleep(abs(random.nextLong() % 150)+10);
        robot.keyRelease(key);
    }
}
