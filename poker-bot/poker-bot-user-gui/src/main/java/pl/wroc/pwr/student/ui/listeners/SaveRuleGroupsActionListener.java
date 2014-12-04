package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertAPI;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;
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
public class SaveRuleGroupsActionListener implements ActionListener {

    private static final String KBS_FORMAT = ".rgp";
    private FileHolder file;
    private JTextField display;
    private List<RuleGroup> ruleGroups;

    public SaveRuleGroupsActionListener(FileHolder file, List<RuleGroup> ruleGroups, JTextField display) {
        this.file = file;
        this.ruleGroups = ruleGroups;
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

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Rule Groups format (*.rgp)", "rgp");
        chooser.setFileFilter(filter);

        String filename;
        String dir;

        int responseValue = chooser.showSaveDialog(null);
        if (responseValue == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().getName();
            dir = chooser.getCurrentDirectory().toString();

            if(!filename.endsWith(KBS_FORMAT))filename = filename+KBS_FORMAT;

            file.setFileDir(dir+ File.separator+filename);
            try {
                ExpertAPI.storeRuleGroupsInFile(file.getFileDir(), ruleGroups);
                JOptionPane.showMessageDialog(null, String.format("Successfully saved rule gropus scheme to %s file.", filename));
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, String.format("Couldn't save rule groups to file: \n %s. \n Error message: %s", file.getFileDir(), e1));
            }
        }
        display.setText(file.getFileDir());
    }
}
