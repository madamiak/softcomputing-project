package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertAPI;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;
import pl.wroc.pwr.student.ui.utils.FileHolder;
import pl.wroc.pwr.student.ui.views.RulesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class LoadRuleGroupsActionListener implements ActionListener {
    private FileHolder file;
    private List<RuleGroup> ruleGroups;
    private DefaultListModel kbsListModel;
    private RulesView rulesView;

    public LoadRuleGroupsActionListener(FileHolder file, List<RuleGroup> ruleGroups, DefaultListModel kbsListModel, RulesView rulesView) {
        this.file = file;
        this.ruleGroups = ruleGroups;
        this.kbsListModel = kbsListModel;
        this.rulesView = rulesView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(file.getFileDir()==null){
            JOptionPane.showMessageDialog(null,"File have to be chosen before loading data.");
            return;
        }
        try {
            rulesView.clearFields();
            ruleGroups.clear();
            ruleGroups.addAll(ExpertAPI.getRuleGroupsFromFile(file.getFileDir()));
            for(RuleGroup data : ruleGroups){
                kbsListModel.addElement(data.getName());
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, String.format("Couldn't load rule groups from file: \n %s. \n File is probably corrupted. \n Error message: %s", file.getFileDir(), e1));
        }
    }
}
