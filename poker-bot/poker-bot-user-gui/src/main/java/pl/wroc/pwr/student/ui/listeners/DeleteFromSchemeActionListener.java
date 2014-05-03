package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class DeleteFromSchemeActionListener  implements ActionListener {
    private List<KnowledgeCommandData> knoledgeBaseScheme;
    private JList schemeList;
    DefaultListModel kbsListModel;

    public DeleteFromSchemeActionListener(List<KnowledgeCommandData> knoledgeBaseScheme, JList schemeList, DefaultListModel kbsListModel) {
        this.knoledgeBaseScheme = knoledgeBaseScheme;
        this.schemeList = schemeList;
        this.kbsListModel = kbsListModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(schemeList.getSelectedIndex()==-1)return;
        int index = schemeList.getSelectedIndex();
        knoledgeBaseScheme.remove(index);
        kbsListModel.remove(index);
    }
}
