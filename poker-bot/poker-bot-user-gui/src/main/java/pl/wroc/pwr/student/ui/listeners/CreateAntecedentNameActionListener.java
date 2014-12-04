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
public class CreateAntecedentNameActionListener implements ActionListener {

    List<RuleGroup> ruleGroups;
    JList ruleGroupsList;
    JList ruleList;
    DefaultListModel antecedentListModel;
    JTextField antecedentNameTextField;

    public CreateAntecedentNameActionListener(List<RuleGroup> ruleGroups, JList ruleGroupsList, JList ruleList, DefaultListModel antecedentListModel, JTextField antecedentNameTextField) {
        this.ruleGroups = ruleGroups;
        this.ruleGroupsList = ruleGroupsList;
        this.ruleList = ruleList;
        this.antecedentListModel = antecedentListModel;
        this.antecedentNameTextField = antecedentNameTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(antecedentNameTextField.getText().equals(""))return;
        int groupSelected = ruleGroupsList.getSelectedIndex();
        if(groupSelected == -1)return;
        int ruleSelected = ruleList.getSelectedIndex();
        if(ruleSelected == -1)return;
        String newAntecedentName = antecedentNameTextField.getText();
        antecedentListModel.addElement(newAntecedentName);
        ruleGroups.get(groupSelected).getRules().get(ruleSelected).getAntecedent().getFactList().add(new Fact(newAntecedentName));
    }
}
