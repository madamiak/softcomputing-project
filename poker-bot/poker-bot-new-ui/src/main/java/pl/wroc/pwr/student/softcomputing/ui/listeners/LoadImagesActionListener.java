package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class LoadImagesActionListener implements ActionListener {
    FileHolder dir;
    DefaultListModel modelToFill;
    List<File> fileListToFill;

    public LoadImagesActionListener(FileHolder dir, DefaultListModel modelToFill, List<File> fileListToFill) {
        this.dir = dir;
        this.modelToFill = modelToFill;
        this.fileListToFill=fileListToFill;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modelToFill.clear();
        fileListToFill.clear();
        if(dir.getFile()==null)
            JOptionPane.showMessageDialog(null,"First select images directory.");
        else{
            for(final File fileEntry : dir.getFile().listFiles()){
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
