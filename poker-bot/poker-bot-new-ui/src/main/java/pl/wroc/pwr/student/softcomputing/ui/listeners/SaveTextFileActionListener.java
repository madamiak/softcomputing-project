package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.FileUtil;
import pl.wroc.pwr.student.softcomputing.ui.util.LabelUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by RaV on 04.01.14.
 */
public class SaveTextFileActionListener implements ActionListener {
    JTextArea textArea;

    public SaveTextFileActionListener(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Save file");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text file (*.txt)", "txt");
        chooser.setFileFilter(filter);

        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            System.out.println("File saved:"+chooser.getSelectedFile());
            System.out.println("Output text: "+textArea.getText());
            if(FileUtil.textToFile(textArea.getText(),chooser.getSelectedFile()))
                JOptionPane.showMessageDialog(null,"File successfully saved.");
            else
                JOptionPane.showMessageDialog(null,"Error while saving a file.");
        }
    }
}
