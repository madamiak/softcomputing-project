package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.softcomputing.ui.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        Table table=RecognizingDelegate.recognize(getSelectedFile(), figuresDatasource, suitsDatasource, dealerDatasource);
        output.setText(table.smallReport());
        output.append("\n");
        output.append(ReasoningDelegate.reason(table));
    }

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public File getSelectedFile() {
        return imageFilesList.get(imageJList.getSelectedIndex());
    }
}
