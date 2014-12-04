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
public class DeleteAntecedentNameActionListener implements ActionListener {

    List<RuleGroup> ruleGroups;
    JList ruleGroupsList;
    JList ruleList;
    JList antecedentList;
    DefaultListModel antecedentListModel;

    public DeleteAntecedentNameActionListener(List<RuleGroup> ruleGroups, JList ruleGroupsList, JList ruleList, JList antecedentList, DefaultListModel antecedentListModel) {
        this.ruleGroups = ruleGroups;
        this.ruleGroupsList = ruleGroupsList;
        this.ruleList = ruleList;
        this.antecedentList = antecedentList;
        this.antecedentListModel = antecedentListModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int groupSelected = ruleGroupsList.getSelectedIndex();
        if(groupSelected == -1)return;
        int ruleSelected = ruleList.getSelectedIndex();
        if(ruleSelected == -1)return;
        int antecedentSelected = antecedentList.getSelectedIndex();
        if(antecedentSelected== -1)return;
        antecedentListModel.remove(antecedentSelected);
        ruleGroups.get(groupSelected).getRules().get(ruleSelected).getAntecedent().getFactList().remove(antecedentSelected);
    }
}
