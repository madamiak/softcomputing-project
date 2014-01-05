package pl.wroc.pwr.student.softcomputing.ui.views;

import javax.swing.*;

/**
 * Created by RaV on 04.01.14.
 */
public class MainView {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private TeachingTab teachingTab;
    private JList teachingList;
    private JPanel teachingPanel;

    public static void main(String[] args) {

        initLookAndFeel();
        JFrame frame = new JFrame("Poker Bot");
        frame.setContentPane(new MainView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void initLookAndFeel() {
        String lookAndFeel = null;
        lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
    }
}
