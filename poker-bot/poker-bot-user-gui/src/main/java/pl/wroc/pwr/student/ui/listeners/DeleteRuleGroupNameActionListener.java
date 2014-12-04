package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class DeleteRuleGroupNameActionListener implements ActionListener {
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleGroupListModel;
    JList ruleGroupsList;

    public DeleteRuleGroupNameActionListener(List<RuleGroup> ruleGroups, DefaultListModel ruleGroupListModel, JList ruleGroupsList) {
        this.ruleGroups = ruleGroups;
        this.ruleGroupListModel = ruleGroupListModel;
        this.ruleGroupsList = ruleGroupsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = ruleGroupsList.getSelectedIndex();
        if(selected==-1)return;
        ruleGroups.remove(selected);
        ruleGroupListModel.removeElementAt(selected);
    }
}
