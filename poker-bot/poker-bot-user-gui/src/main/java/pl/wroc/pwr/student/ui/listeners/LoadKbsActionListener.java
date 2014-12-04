package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertAPI;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;
import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class LoadKbsActionListener implements ActionListener {
    private FileHolder file;
    private List<KnowledgeCommandData> knowledgeBaseScheme;
    private DefaultListModel kbsListModel;

    public LoadKbsActionListener(FileHolder file, List<KnowledgeCommandData> knowledgeBaseScheme, DefaultListModel kbsListModel) {
        this.file = file;
        this.knowledgeBaseScheme = knowledgeBaseScheme;
        this.kbsListModel = kbsListModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(file.getFileDir()==null){
            JOptionPane.showMessageDialog(null,"File have to be chosen before loading data.");
            return;
        }
        try {
            knowledgeBaseScheme.clear();
            knowledgeBaseScheme.addAll(ExpertAPI.getKnowledgeSchemeFromFile(file.getFileDir()));
            kbsListModel.removeAllElements();
            for(KnowledgeCommandData data : knowledgeBaseScheme){
                kbsListModel.addElement(data.getName());
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, String.format("Couldn't load knowledge base scheme from file: \n %s. \n File is probably corrupted. \n Error message: %s", file.getFileDir(), e1));
        }
    }
}
