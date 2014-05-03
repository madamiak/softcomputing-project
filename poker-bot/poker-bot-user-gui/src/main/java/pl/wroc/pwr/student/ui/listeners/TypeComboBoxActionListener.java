package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.ui.views.KnowledgeSchemesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RaV on 21.04.14.
 */
public class TypeComboBoxActionListener implements ActionListener {
    KnowledgeSchemesView knowledgeSchemesView;

    public TypeComboBoxActionListener(KnowledgeSchemesView knowledgeSchemesView) {
        this.knowledgeSchemesView = knowledgeSchemesView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        knowledgeSchemesView.refreshParameterNameComboBox();
        knowledgeSchemesView.refreshComparatorComboBox();
        knowledgeSchemesView.refreshValuesPanel();
    }
}
