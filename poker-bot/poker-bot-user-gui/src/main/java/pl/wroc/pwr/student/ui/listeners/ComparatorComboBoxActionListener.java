package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.ui.views.KnowledgeSchemesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RaV on 21.04.14.
 */
public class ComparatorComboBoxActionListener  implements ActionListener {
    KnowledgeSchemesView knowledgeSchemesView;

    public ComparatorComboBoxActionListener(KnowledgeSchemesView knowledgeSchemesView) {
        this.knowledgeSchemesView = knowledgeSchemesView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        knowledgeSchemesView.refreshValuesPanel();
    }
}