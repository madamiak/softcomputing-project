package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by RaV on 21.04.14.
 */
public class BrowseActionListener implements ActionListener {
    private FileHolder file;
    private JTextField display;

    public BrowseActionListener(FileHolder file, JTextField display) {
        this.file = file;
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if(file.getFileDir()==null)
            chooser.setCurrentDirectory(new File("."));
        else
            chooser.setCurrentDirectory(new File(file.getFileDir()));
        chooser.setDialogTitle("Select file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Knowledge Base Scheme format (*.kbs)", "kbs");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            file.setFileDir(chooser.getSelectedFile().toString());
        } else {
            System.out.println("No Selection");
        }
        display.setText(file.getFileDir());
    }
}
