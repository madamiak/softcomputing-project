package pl.wroc.pwr.student.ui.views;

import pl.wroc.pwr.student.ui.listeners.*;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Border;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeCommandData;
import pl.wroc.pwr.student.ui.utils.CardFigure;
import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class KnowledgeSchemesView {
    private JPanel knowledgePane;
    private JPanel loadSavePanel;
    private JTextField fileTextField;
    private JButton saveButton;
    private JButton browseButton;
    private JButton loadButton;
    private JPanel mainPanel;
    private JList schemeList;
    private JPanel editPanel;
    private JButton deleteFromSchemeButton;
    private JButton newSchemeElementButton;
    private JButton saveToScheme;
    private JTextField recordNameField;
    private JPanel namePanel;
    private JComboBox typeComboBox;
    private JPanel typePanel;
    private JPanel paramNamePanel;
    private JComboBox parameterNameComboBox;
    private JPanel comparatorPanel;
    private JComboBox comparatorComboBox;
    private JComboBox value2ComboBox;
    private JPanel valuesPanel;
    private JComboBox value1ComboBox;

    private DefaultListModel kbsListModel;
    private DefaultComboBoxModel typeComboBoxModel;
    private DefaultComboBoxModel paramNameComboBoxModel;
    private DefaultComboBoxModel comparatorComboBoxModel;
    private DefaultComboBoxModel value1ComboBoxModel;
    private DefaultComboBoxModel value2ComboBoxModel;

    private FileHolder knowledgeSchemeFile;
    private List<KnowledgeCommandData> knowledgeBaseScheme = new ArrayList<KnowledgeCommandData>();

    public KnowledgeSchemesView() {
        knowledgeSchemeFile = new FileHolder();
        browseButton.addActionListener(new BrowseActionListener(knowledgeSchemeFile, fileTextField, new FileNameExtensionFilter("Knowledge Base Scheme format (*.kbs)", "kbs")));
        saveButton.addActionListener(new SaveKbsActionListener(knowledgeSchemeFile, knowledgeBaseScheme, fileTextField));
        loadButton.addActionListener(new LoadKbsActionListener(knowledgeSchemeFile, knowledgeBaseScheme, kbsListModel));
        schemeList.addListSelectionListener(new KbsListSelectListener(this, schemeList, knowledgeBaseScheme));
        typeComboBox.addActionListener(new TypeComboBoxActionListener(this));
        comparatorComboBox.addActionListener(new ComparatorComboBoxActionListener(this));

        deleteFromSchemeButton.addActionListener(new DeleteFromSchemeActionListener(knowledgeBaseScheme,schemeList, kbsListModel));
        saveToScheme.addActionListener(new SaveToSchemeActionListener(knowledgeBaseScheme,schemeList, kbsListModel, recordNameField, typeComboBox,parameterNameComboBox,comparatorComboBox,value1ComboBox,value2ComboBox));
        newSchemeElementButton.addActionListener(new NewToSchemeActionListener(knowledgeBaseScheme,schemeList,kbsListModel));

        fillTypeComboBox();
        refreshParameterNameComboBox();
        refreshComparatorComboBox();
    }

    private void createUIComponents() {
        kbsListModel = new DefaultListModel();
        schemeList = new JList(kbsListModel);

        typeComboBoxModel = new DefaultComboBoxModel<Enums.ParameterType>();
        typeComboBox = new JComboBox(typeComboBoxModel);

        paramNameComboBoxModel = new DefaultComboBoxModel<String>();
        parameterNameComboBox = new JComboBox(paramNameComboBoxModel);

        comparatorComboBoxModel = new DefaultComboBoxModel<Enums.Comparator>();
        comparatorComboBox = new JComboBox(comparatorComboBoxModel);

        value1ComboBoxModel = new DefaultComboBoxModel<String>();
        value1ComboBox = new JComboBox(value1ComboBoxModel);

        value2ComboBoxModel = new DefaultComboBoxModel<String>();
        value2ComboBox = new JComboBox(value2ComboBoxModel);
    }

    private void fillTypeComboBox(){
        typeComboBoxModel.removeAllElements();
        for(Enums.ParameterType type : Enums.ParameterType.values()){
            typeComboBoxModel.addElement(type);
        }
    }

    public void refreshParameterNameComboBox(){
        Enums.ParameterType type = (Enums.ParameterType) typeComboBoxModel.getSelectedItem();
        paramNameComboBoxModel.removeAllElements();;
        switch (type) {
            case INT:
                for(Enums.IntParameter intParameter : Enums.IntParameter.values())
                    paramNameComboBoxModel.addElement(intParameter);
                break;
            case STRING:
                for(Enums.StringParameter stringParameter : Enums.StringParameter.values())
                    paramNameComboBoxModel.addElement(stringParameter);
                break;
            case BOOLEAN:
                for(Enums.BooleanParameter booleanParameter : Enums.BooleanParameter.values())
                    paramNameComboBoxModel.addElement(booleanParameter);
                break;
            case BORDER:
                for(Border border : Border.values())
                    paramNameComboBoxModel.addElement(border);
                break;
        }
    }

    public void refreshComparatorComboBox(){
        Enums.ParameterType type = (Enums.ParameterType) typeComboBoxModel.getSelectedItem();
        comparatorComboBoxModel.removeAllElements();;
        System.out.println(type);
        switch (type) {
            case INT:
                comparatorComboBoxModel.addElement(Enums.Comparator.EQUAL);
                comparatorComboBoxModel.addElement(Enums.Comparator.NOT_EQUAL);
                comparatorComboBoxModel.addElement(Enums.Comparator.GREATER);
                comparatorComboBoxModel.addElement(Enums.Comparator.LESS);
                comparatorComboBoxModel.addElement(Enums.Comparator.BETWEEN);
                break;
            case STRING:
                comparatorComboBoxModel.addElement(Enums.Comparator.EQUAL);
                comparatorComboBoxModel.addElement(Enums.Comparator.NOT_EQUAL);
                break;
            case BOOLEAN:
                comparatorComboBoxModel.addElement(Enums.Comparator.TRUE);
                comparatorComboBoxModel.addElement(Enums.Comparator.FALSE);
                break;
            case BORDER:
                comparatorComboBoxModel.addElement(Enums.Comparator.EQUAL);
                comparatorComboBoxModel.addElement(Enums.Comparator.NOT_EQUAL);
                break;
        }
    }

    public void refreshValuesPanel(){
        if(comparatorComboBox.getSelectedItem()==null)return;
        switch ((Enums.Comparator)comparatorComboBoxModel.getSelectedItem()){
            case EQUAL:
                fillValuesForOneValueComparator();
                break;
            case GREATER:
                fillValuesForOneValueComparator();
                break;
            case LESS:
                fillValuesForOneValueComparator();
                break;
            case BETWEEN:
                fillValuesForTwoValueComparator();
                break;
            case NOT_EQUAL:
                fillValuesForOneValueComparator();
                break;
            case TRUE:
                displayForNoValues();
                break;
            case FALSE:
                displayForNoValues();
                break;
        }
    }

    private void fillValuesForOneValueComparator(){
        Enums.ParameterType type = (Enums.ParameterType) typeComboBoxModel.getSelectedItem();
        value1ComboBoxModel.removeAllElements();
        value2ComboBoxModel.removeAllElements();
        switch (type) {
            case INT:
                displayFor1SelectValue();
                for(int i=1;i<=1000;i++){
                    value1ComboBoxModel.addElement(i);
                    value2ComboBoxModel.addElement(i);
                }
                break;
            case STRING:
                displayFor1SelectValue();
                for(CardFigure figure : CardFigure.values())
                    value1ComboBoxModel.addElement(figure);
                break;
            case BOOLEAN:
                displayForNoValues();
                break;
            case BORDER:
                displayForNoValues();
                break;
        }
    }

    private void fillValuesForTwoValueComparator(){
        Enums.ParameterType type = (Enums.ParameterType) typeComboBoxModel.getSelectedItem();
        value1ComboBoxModel.removeAllElements();
        value2ComboBoxModel.removeAllElements();
        switch (type) {
            case INT:
                displayFor2SelectValues();
                for(int i=1;i<=1000;i++){
                    value1ComboBoxModel.addElement(i);
                    value2ComboBoxModel.addElement(i);
                }
                break;
            case STRING:
                displayFor2SelectValues();
                for(CardFigure figure : CardFigure.values()){
                    value1ComboBoxModel.addElement(figure);
                    value2ComboBoxModel.addElement(figure);
                }
                break;
            case BOOLEAN:
                displayForNoValues();
                break;
            case BORDER:
                displayForNoValues();
                break;
        }
    }

    private void displayFor1SelectValue(){
        hideAllValues();
        value1ComboBox.setVisible(true);
    }

    private void displayFor2SelectValues(){
        hideAllValues();
        value1ComboBox.setVisible(true);
        value2ComboBox.setVisible(true);
    }

    private void displayForNoValues(){
        hideAllValues();
    }

    private void hideAllValues(){
        value1ComboBox.setVisible(false);
        value2ComboBox.setVisible(false);
    }

    public void setFieldsForElement(KnowledgeCommandData data) {
        recordNameField.setText(data.getName());
        typeComboBox.setSelectedItem(Enums.ParameterType.getType(data.getParameterType()));
        switch(Enums.ParameterType.getType(data.getParameterType())){
            case INT:
                parameterNameComboBox.setSelectedItem(Enums.IntParameter.getParameter(data.getParameterName()));
                break;
            case STRING:
                parameterNameComboBox.setSelectedItem(Enums.StringParameter.getParameter(data.getParameterName()));
                break;
            case BOOLEAN:
                parameterNameComboBox.setSelectedItem(Enums.BooleanParameter.getParameter(data.getParameterName()));
                break;
            case BORDER:
                parameterNameComboBox.setSelectedItem(Border.getBorder(data.getParameterName()));
                break;
        }
        comparatorComboBox.setSelectedItem(Enums.Comparator.getComparator(data.getComparator()));
        if(data.getValues().size()>0)value1ComboBoxModel.setSelectedItem(data.getValues().get(0));
        if(data.getValues().size()>1)value2ComboBoxModel.setSelectedItem(data.getValues().get(1));
    }


}
