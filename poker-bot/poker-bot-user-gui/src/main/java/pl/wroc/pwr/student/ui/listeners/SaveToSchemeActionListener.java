package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class SaveToSchemeActionListener implements ActionListener {
    private List<KnowledgeCommandData> knowledgeBaseScheme;
    private JList schemeList;
    DefaultListModel kbsListModel;
    JTextField recordNameField;
    JComboBox typeComboBox;
    JComboBox parameterNameComboBox;
    JComboBox comparatorComboBox;
    JComboBox value1ComboBox;
    JComboBox value2ComboBox;

    public SaveToSchemeActionListener(List<KnowledgeCommandData> knowledgeBaseScheme, JList schemeList, DefaultListModel kbsListModel, JTextField recordNameField, JComboBox typeComboBox, JComboBox parameterNameComboBox, JComboBox comparatorComboBox, JComboBox value1ComboBox, JComboBox value2ComboBox) {
        this.knowledgeBaseScheme = knowledgeBaseScheme;
        this.schemeList = schemeList;
        this.kbsListModel = kbsListModel;
        this.recordNameField = recordNameField;
        this.typeComboBox = typeComboBox;
        this.parameterNameComboBox = parameterNameComboBox;
        this.comparatorComboBox = comparatorComboBox;
        this.value1ComboBox = value1ComboBox;
        this.value2ComboBox = value2ComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(schemeList.getSelectedIndex()==-1)return;
        int index = schemeList.getSelectedIndex();
        kbsListModel.set(index, recordNameField.getText());

        knowledgeBaseScheme.get(index).setName(recordNameField.getText());
        knowledgeBaseScheme.get(index).setComparator(comparatorComboBox.getSelectedItem().toString());
        knowledgeBaseScheme.get(index).setParameterName(parameterNameComboBox.getSelectedItem().toString());
        knowledgeBaseScheme.get(index).setParameterType(typeComboBox.getSelectedItem().toString());
        List<String> values = new ArrayList<String>();
        switch((Enums.Comparator)comparatorComboBox.getSelectedItem()){
            case EQUAL:
                if(Enums.ParameterType.getType(typeComboBox.getSelectedItem().toString())!=Enums.ParameterType.BORDER)
                    values.add(value1ComboBox.getSelectedItem().toString());
                break;
            case GREATER:
                values.add(value1ComboBox.getSelectedItem().toString());
                break;
            case LESS:
                values.add(value1ComboBox.getSelectedItem().toString());
                break;
            case BETWEEN:
                values.add(value1ComboBox.getSelectedItem().toString());
                values.add(value2ComboBox.getSelectedItem().toString());
                break;
            case NOT_EQUAL:
                if(Enums.ParameterType.getType(typeComboBox.getSelectedItem().toString())!=Enums.ParameterType.BORDER)
                    values.add(value1ComboBox.getSelectedItem().toString());
                break;
            case TRUE:
                break;
            case FALSE:
                break;
        }
        knowledgeBaseScheme.get(index).setValues(values);
    }
}
