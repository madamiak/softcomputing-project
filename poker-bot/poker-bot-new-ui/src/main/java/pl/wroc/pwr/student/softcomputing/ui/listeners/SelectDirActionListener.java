package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class SelectDirActionListener implements ActionListener {
    FileHolder dir;
    JTextField toSetDir;

    public SelectDirActionListener(FileHolder dir, JTextField toSetDir) {
        this.dir = dir;
        this.toSetDir = toSetDir;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if(dir.getFile()==null)
            chooser.setCurrentDirectory(new File("."));
        else
            chooser.setCurrentDirectory(dir.getFile());
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            toSetDir.setText(chooser.getSelectedFile().toString());
            dir.setFile(chooser.getSelectedFile());
        } else {
            System.out.println("No Selection");
            toSetDir.setText("No Selection");
            dir.setFile(null);
        }
    }
}
