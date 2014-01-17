package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.RecognizingDelegate;
import pl.wroc.pwr.student.softcomputing.ui.util.TeachingDelegate;
import pl.wroc.pwr.student.softcomputing.ui.util.TeachingParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class RecognizeSelectedActionListener implements ActionListener {
    private List<File> imageFilesList;
    private JTextArea output;
    private JList imageJList;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;

    public RecognizeSelectedActionListener(List<File> imageFilesList, JTextArea output, JList imageJList, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource) {
        this.imageFilesList = imageFilesList;
        this.output = output;
        this.imageJList = imageJList;
        this.figuresDatasource = figuresDatasource;
        this.suitsDatasource = suitsDatasource;
        this.dealerDatasource = dealerDatasource;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(imageFilesList.isEmpty()){
            displayMessage("No images selected.");
            return;
        }
        String out=RecognizingDelegate.recognize(getSelectedFile(), figuresDatasource, suitsDatasource, dealerDatasource);
        output.append(out);
//        output.append("\n\n---(Raport for "+getSelectedFile().getName()+")---\n");
//        output.append("\n"+out);
    }

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public File getSelectedFile() {
        return imageFilesList.get(imageJList.getSelectedIndex());
    }
}
