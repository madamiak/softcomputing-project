package pl.wroc.pwr.student.ui.views;

import javax.swing.*;

/**
 * Created by RaV on 19.04.14.
 */
public class MainView {
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Poker Player Decision Aided System");
        frame.setContentPane(new MainView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
