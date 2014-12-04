package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class SaveRuleGroupNameActionListener implements ActionListener {
    JTextField ruleGroupNameTextField;
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleGroupListModel;
    JList ruleGroupsList;

    public SaveRuleGroupNameActionListener(JTextField ruleGroupNameTextField, List<RuleGroup> ruleGroups, DefaultListModel ruleGroupListModel, JList ruleGroupsList) {
        this.ruleGroupNameTextField = ruleGroupNameTextField;
        this.ruleGroups = ruleGroups;
        this.ruleGroupListModel = ruleGroupListModel;
        this.ruleGroupsList = ruleGroupsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(ruleGroupNameTextField.getText().equals(""))return;
        int selected = ruleGroupsList.getSelectedIndex();
        if(selected==-1)return;
        String newName = ruleGroupNameTextField.getText();
        ruleGroups.get(selected).setName(newName);
        ruleGroupListModel.setElementAt(newName,selected);
    }
}
