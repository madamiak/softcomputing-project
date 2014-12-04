package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class SaveConsequentNameActionListener implements ActionListener {
    JTextField consequentNameTextField;
    List<RuleGroup> ruleGroups;
    DefaultListModel ruleListModel;
    JList ruleGroupsList;
    JList ruleList;

    public SaveConsequentNameActionListener(JTextField consequentNameTextField, List<RuleGroup> ruleGroups, DefaultListModel ruleListModel, JList ruleGroupsList, JList ruleList) {
        this.consequentNameTextField = consequentNameTextField;
        this.ruleGroups = ruleGroups;
        this.ruleListModel = ruleListModel;
        this.ruleGroupsList = ruleGroupsList;
        this.ruleList = ruleList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(consequentNameTextField.getText().equals(""))return;
        int groupSelected = ruleGroupsList.getSelectedIndex();
        if(groupSelected==-1)return;
        int ruleSelected = ruleList.getSelectedIndex();
        if(ruleSelected==-1)return;
        String newName = consequentNameTextField.getText();
        ruleGroups.get(groupSelected).getRules().get(ruleSelected).setConsequent(new Fact(newName));
        ruleListModel.setElementAt(newName,ruleSelected);
    }
}
