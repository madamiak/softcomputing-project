package pl.wroc.pwr.student.ui.listeners;


import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class SelectDirActionListener implements ActionListener {
    FileHolder dir;
    JTextField toSetDir;
    DefaultListModel modelToFill;
    List<File> fileListToFill;

    public SelectDirActionListener(FileHolder dir, JTextField toSetDir, DefaultListModel modelToFill, List<File> fileListToFill) {
        this.dir = dir;
        this.toSetDir = toSetDir;
        this.modelToFill = modelToFill;
        this.fileListToFill=fileListToFill;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if(dir.getFileDir()==null)
            chooser.setCurrentDirectory(new File("."));
        else
            chooser.setCurrentDirectory(new File(dir.getFileDir()));
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            toSetDir.setText(chooser.getSelectedFile().toString());
            dir.setFileDir(chooser.getSelectedFile().toString());
        } else {
            System.out.println("No Selection");
            toSetDir.setText("No Selection");
            dir.setFileDir(null);
        }
        modelToFill.clear();
        fileListToFill.clear();
        if(dir.getFileDir()==null)
            JOptionPane.showMessageDialog(null,"First select images directory.");
        else{
            for(final File fileEntry : new File(dir.getFileDir()).listFiles()){
                String fileName = fileEntry.toString();
                if(!fileEntry.isDirectory()&&fileName.endsWith(".png")){
                    fileListToFill.add(fileEntry);
                    modelToFill.addElement(fileName.substring(fileName.lastIndexOf(File.separator)+1));
                    System.out.println(fileEntry);
                }
            }
        }
    }
}
