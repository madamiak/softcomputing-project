package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Rule;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class CreateRuleGroupNameActionListener implements ActionListener {
    private static final String NEW_RULE_GROUP_NAME = "New Rule Group";
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleGroupListModel;
    JList ruleGroupsList;

    public CreateRuleGroupNameActionListener(List<RuleGroup> ruleGroups, DefaultListModel ruleGroupListModel, JList ruleGroupsList) {
        this.ruleGroups = ruleGroups;
        this.ruleGroupListModel = ruleGroupListModel;
        this.ruleGroupsList = ruleGroupsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = ruleGroupsList.getSelectedIndex();
        if(selected==-1)selected = ruleGroupListModel.size();
        else selected++;
        ruleGroups.add(selected,new RuleGroup(NEW_RULE_GROUP_NAME, new ArrayList<Rule>()));
        ruleGroupListModel.add(selected, NEW_RULE_GROUP_NAME);
    }
}
