package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Rule;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class RuleListSelectListener implements ListSelectionListener {
    DefaultListModel antecedentListModel;
    JList ruleGroupList;
    JList ruleList;
    List<RuleGroup> ruleGroups;
    JTextField consequentNameTextField;
    JTextField antecedentNameTextField;

    public RuleListSelectListener(DefaultListModel antecedentListModel, JList ruleList, JList ruleGroupList, List<RuleGroup> ruleGroups, JTextField consequentNameTextField, JTextField antecedentNameTextField) {
        this.antecedentListModel = antecedentListModel;
        this.ruleList = ruleList;
        this.ruleGroupList = ruleGroupList;
        this.ruleGroups = ruleGroups;
        this.consequentNameTextField = consequentNameTextField;
        this.antecedentNameTextField = antecedentNameTextField;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            antecedentNameTextField.setText("");
            RuleGroup data = ruleGroups.get(ruleGroupList.getSelectedIndex());
            Rule rule = data.getRules().get(ruleList.getSelectedIndex());
            antecedentListModel.removeAllElements();
            consequentNameTextField.setText(((Fact)rule.getConsequent()).getName());
            for(Fact fact : rule.getAntecedent().getFactList()){
                antecedentListModel.addElement(fact.getName());
            }
        }
    }
}
