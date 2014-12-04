package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Rule;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class DuplicateRuleGroupActionListener implements ActionListener {
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleGroupListModel;
    JList ruleGroupsList;

    public DuplicateRuleGroupActionListener(List<RuleGroup> ruleGroups, DefaultListModel ruleGroupListModel, JList ruleGroupsList) {
        this.ruleGroups = ruleGroups;
        this.ruleGroupListModel = ruleGroupListModel;
        this.ruleGroupsList = ruleGroupsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = ruleGroupsList.getSelectedIndex();
        if(selected==-1)return;
        ruleGroups.add(selected+1,new RuleGroup(ruleGroups.get(selected)));
        ruleGroupListModel.add(selected + 1, ruleGroups.get(selected).getName());
    }
}
