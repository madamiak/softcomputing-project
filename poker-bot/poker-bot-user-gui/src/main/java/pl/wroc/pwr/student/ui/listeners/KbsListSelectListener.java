package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;
import pl.wroc.pwr.student.ui.views.KnowledgeSchemesView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class KbsListSelectListener implements ListSelectionListener {
    KnowledgeSchemesView knowledgeSchemesView;
    JList schemeList;
    List<KnowledgeCommandData> knowledgeBaseScheme;

    public KbsListSelectListener(KnowledgeSchemesView knowledgeSchemesView, JList schemeList, List<KnowledgeCommandData> knowledgeBaseScheme) {
        this.knowledgeSchemesView = knowledgeSchemesView;
        this.schemeList=schemeList;
        this.knowledgeBaseScheme=knowledgeBaseScheme;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            KnowledgeCommandData data = knowledgeBaseScheme.get(schemeList.getSelectedIndex());
            knowledgeSchemesView.setFieldsForElement(data);
        }
    }
}
