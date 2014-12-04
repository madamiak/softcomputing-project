package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class MarkFileActionListener implements ActionListener {
    private final String BAD_DECISION_TEXT = "(BadDecision)";

    JList imageList;
    List<File> fileList;
    DefaultListModel imageListModel;

    public MarkFileActionListener(JList imageList, List<File> fileList, DefaultListModel imageListModel) {
        this.imageList = imageList;
        this.fileList = fileList;
        this.imageListModel = imageListModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = imageList.getSelectedIndex();
        if (selected == -1) return;
        int dialogResult = JOptionPane.showConfirmDialog(null, String.format("Would you like to mark file: %s as bad decision?", imageListModel.get(selected)));
        if (dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.CANCEL_OPTION) return;
        if (!fileList.get(selected).getName().contains(BAD_DECISION_TEXT)) {
            String oldFile = fileList.get(selected).toString();
            File newFile = new File(oldFile.substring(0, oldFile.lastIndexOf('.')) + BAD_DECISION_TEXT + oldFile.substring(oldFile.lastIndexOf('.')));
            if (newFile.exists()) {
                JOptionPane.showMessageDialog(null, "There is already file with that name marked as bad decision.");
                return;
            }
            fileList.get(selected).renameTo(newFile);
            String oldName = imageListModel.get(selected).toString();
            imageListModel.setElementAt(oldName.substring(0, oldName.lastIndexOf('.')) + BAD_DECISION_TEXT + oldName.substring(oldName.lastIndexOf('.')),selected);
        }
    }
}
