package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Antecedent;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Rule;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class DeleteRuleActionListener implements ActionListener {
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleListModel;
    JList ruleGroupsList;
    JList ruleList;

    public DeleteRuleActionListener(List<RuleGroup> ruleGroups, DefaultListModel ruleListModel, JList ruleGroupsList, JList ruleList) {
        this.ruleGroups = ruleGroups;
        this.ruleListModel = ruleListModel;
        this.ruleGroupsList = ruleGroupsList;
        this.ruleList = ruleList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int groupSelected = ruleGroupsList.getSelectedIndex();
        if(groupSelected==-1)return;
        int ruleSelected = ruleList.getSelectedIndex();
        if(ruleSelected==-1)return;
        ruleGroups.get(groupSelected).getRules().remove(ruleSelected);
        ruleListModel.remove(ruleSelected);
    }
}
