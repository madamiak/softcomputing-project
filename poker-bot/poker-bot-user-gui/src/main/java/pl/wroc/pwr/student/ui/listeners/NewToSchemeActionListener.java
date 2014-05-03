package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class NewToSchemeActionListener implements ActionListener {
    private List<KnowledgeCommandData> knowledgeBaseScheme;
    private JList schemeList;
    DefaultListModel kbsListModel;

    public NewToSchemeActionListener(List<KnowledgeCommandData> knowledgeBaseScheme, JList schemeList, DefaultListModel kbsListModel) {
        this.knowledgeBaseScheme = knowledgeBaseScheme;
        this.schemeList = schemeList;
        this.kbsListModel = kbsListModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        KnowledgeCommandData data = new KnowledgeCommandData();

        data.setName("New record");
        data.setParameterType(Enums.ParameterType.INT.toString());
        data.setParameterName(Enums.IntParameter.values()[0].toString());
        data.setComparator(Enums.Comparator.values()[0].toString());
        data.getValues().add("1");

        knowledgeBaseScheme.add(data);
        kbsListModel.addElement(data.getName());
        schemeList.setSelectedIndex(knowledgeBaseScheme.size()-1);
    }
}
