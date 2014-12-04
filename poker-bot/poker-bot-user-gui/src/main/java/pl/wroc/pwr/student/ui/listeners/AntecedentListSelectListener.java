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
public class AntecedentListSelectListener implements ListSelectionListener {
    JList antecedentList;
    JTextField antecedentNameTextField;

    public AntecedentListSelectListener(JList antecedentList, JTextField antecedentNameTextField) {
        this.antecedentList = antecedentList;
        this.antecedentNameTextField = antecedentNameTextField;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            antecedentNameTextField.setText(antecedentList.getSelectedValue().toString());
        }
    }
}
