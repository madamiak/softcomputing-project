package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by RaV on 04.01.14.
 */
public class ClearTextAreaActionListener implements ActionListener {
    JTextArea textArea;

    public ClearTextAreaActionListener(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     textArea.setText("");
    }
}
