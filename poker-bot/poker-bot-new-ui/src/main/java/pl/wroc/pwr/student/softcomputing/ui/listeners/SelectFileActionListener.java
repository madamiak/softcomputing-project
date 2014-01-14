package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.LabelUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by RaV on 04.01.14.
 */
public class SelectFileActionListener implements ActionListener {
    FileHolder file;
    JTextField toSetDir;
    JLabel infoLabel;

    public SelectFileActionListener(FileHolder file, JTextField toSetDir, JLabel infoLabel) {
        this.file = file;
        this.toSetDir = toSetDir;
        this.infoLabel = infoLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if(file.getFile()==null)
            chooser.setCurrentDirectory(new File("."));
        else
            chooser.setCurrentDirectory(file.getFile());
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Neural networ format (*.nnet)", "nnet");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            if(validateFilename(chooser.getSelectedFile().toString())){
                toSetDir.setText(chooser.getSelectedFile().toString());
                infoLabel.setText(LabelUtil.wrapGreen("File picked"));
                file.setFile(chooser.getSelectedFile());
            }else{
                System.out.println("No Selection");
                toSetDir.setText("No Selection");
                infoLabel.setText(LabelUtil.wrapRed("Wrong file postfix"));
                file.setFile(null);
            }
        } else {
            System.out.println("No Selection");
            toSetDir.setText("No Selection");
            infoLabel.setText(LabelUtil.wrapOrange("Not picked"));
            file.setFile(null);
        }
    }

    private boolean validateFilename(String filename){
        String[] elements = filename.split("-");
        try{
            int i = elements.length-3;
            if(!elements[i].equals(""))
                Float.parseFloat(elements[i].replace(",","."));
            i++;
            if(!(elements[i].equals("")||elements[i].equals("t")))
                return false;
            i++;
            elements[i]=elements[i].substring(0,elements[i].length()-5);
            System.out.println(elements[i]);
            if(!(elements[i].equals("")||elements[i].equals("t")))
                return false;
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
