package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Rule;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;
import pl.wroc.pwr.student.ui.views.RulesView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class RuleGroupListSelectListener implements ListSelectionListener {
    DefaultListModel ruleListModel;
    JList ruleList;
    List<RuleGroup> ruleGroups;
    JTextField ruleGroupNameTextField;
    RulesView rulesView;

    public RuleGroupListSelectListener(DefaultListModel ruleListModel, JList ruleList, List<RuleGroup> ruleGroups, JTextField ruleGroupNameTextField, RulesView rulesView) {
        this.ruleListModel = ruleListModel;
        this.ruleList = ruleList;
        this.ruleGroups = ruleGroups;
        this.ruleGroupNameTextField = ruleGroupNameTextField;
        this.rulesView = rulesView;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            rulesView.clearFieldsButGroups();
            RuleGroup data = ruleGroups.get(ruleList.getSelectedIndex());
            ruleGroupNameTextField.setText(data.getName());
            for(Rule rule : data.getRules()){
                ruleListModel.addElement(((Fact)rule.getConsequent()).getName());
            }
        }
    }
}
