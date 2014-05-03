package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertAPI;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;
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
public class SaveKbsActionListener implements ActionListener {

    private static final String KBS_FORMAT = ".kbs";
    private FileHolder file;
    private JTextField display;
    private List<KnowledgeCommandData> knoledgeBaseScheme;

    public SaveKbsActionListener(FileHolder file, List<KnowledgeCommandData> knoledgeBaseScheme, JTextField display) {
        this.file = file;
        this.knoledgeBaseScheme = knoledgeBaseScheme;
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

        String filename;
        String dir;

        int responseValue = chooser.showSaveDialog(null);
        if (responseValue == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().getName();
            dir = chooser.getCurrentDirectory().toString();

            if(!filename.endsWith(KBS_FORMAT))filename = filename+KBS_FORMAT;

            file.setFileDir(dir+ File.separator+filename);
            System.out.println(knoledgeBaseScheme);
            try {
                ExpertAPI.storeKnowledgeSchemeInFile(file.getFileDir(),knoledgeBaseScheme);
                JOptionPane.showMessageDialog(null, String.format("Successfully saved knowledge base scheme to %s file.", filename));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, String.format("Couldn't save knowledge base scheme to file: \n %s. \n Error message: %s", file.getFileDir(), e1));
            }
        }
        display.setText(file.getFileDir());
    }
}
