package pl.wroc.pwr.student.ui.views;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.RuleGroup;
import pl.wroc.pwr.student.ui.listeners.*;
import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 03.05.14.
 */
public class RulesView {
    private JPanel loadSavePanel;
    private JTextField ruleFileTextField;
    private JButton ruleSaveButton;
    private JButton ruleBrowseButton;
    private JButton ruleLoadButton;
    private JList ruleGroupsList;
    private JPanel ruleGroupEditPanel;
    private JPanel mainPanel;
    private JList ruleList;
    private JTextField ruleGroupNameTextField;
    private JButton newRuleButton;
    private JButton saveConsequentButton;
    private JButton deleteRuleButton;
    private JTextField knowledgeSchemeFileTextField;
    private JButton verifyButton;
    private JButton knowledgeBrowseButton;
    private JButton duplicateRuleButton;
    private JButton newRuleGroupButton;
    private JPanel ruleEditPanel;
    private JButton duplicateRuleGroupButton;
    private JButton deleteRuleGroupButton;
    private JButton saveGroupNameButton;
    private JTextField consequentTextField;
    private JList antecedentList;
    private JTextField antecedentNameTextField;
    private JButton newAtecedentButton;
    private JButton saveAtecedentButton;
    private JButton deleteAtecedentButton;


    private DefaultListModel ruleGroupListModel;
    private DefaultListModel ruleListModel;
    private DefaultListModel antecedentListModel;

    private FileHolder ruleGroupsFile;
    private FileHolder knowledgeSchemeFile;
    private List<RuleGroup> ruleGroups = new ArrayList<RuleGroup>();

    public RulesView() {
        ruleGroupsFile = new FileHolder();
        knowledgeSchemeFile = new FileHolder();
        ruleBrowseButton.addActionListener(new BrowseActionListener(ruleGroupsFile, ruleFileTextField, new FileNameExtensionFilter("Rule Groups format (*.rgp)", "rgp")));
        ruleLoadButton.addActionListener(new LoadRuleGroupsActionListener(ruleGroupsFile, ruleGroups, ruleGroupListModel, this));
        ruleSaveButton.addActionListener(new SaveRuleGroupsActionListener(ruleGroupsFile, ruleGroups, ruleFileTextField));
        ruleGroupsList.addListSelectionListener(new RuleGroupListSelectListener(ruleListModel, ruleGroupsList, ruleGroups, ruleGroupNameTextField, this));

        saveGroupNameButton.addActionListener(new SaveRuleGroupNameActionListener(ruleGroupNameTextField, ruleGroups, ruleGroupListModel, ruleGroupsList));
        duplicateRuleGroupButton.addActionListener(new DuplicateRuleGroupActionListener(ruleGroups,ruleGroupListModel,ruleGroupsList));
        deleteRuleGroupButton.addActionListener(new DeleteRuleGroupNameActionListener(ruleGroups,ruleGroupListModel,ruleGroupsList));
        newRuleGroupButton.addActionListener(new CreateRuleGroupNameActionListener(ruleGroups, ruleGroupListModel, ruleGroupsList));

        ruleList.addListSelectionListener(new RuleListSelectListener(antecedentListModel,ruleList,ruleGroupsList,ruleGroups,consequentTextField,antecedentNameTextField));
        saveConsequentButton.addActionListener(new SaveConsequentNameActionListener(consequentTextField, ruleGroups, ruleListModel, ruleGroupsList, ruleList));
        duplicateRuleButton.addActionListener(new DuplicateRuleActionListener(ruleGroups,ruleListModel,ruleGroupsList,ruleList));
        newRuleButton.addActionListener(new CreateRuleActionListener(ruleGroups, ruleListModel, ruleGroupsList, ruleList));
        deleteRuleButton.addActionListener(new DeleteRuleActionListener(ruleGroups, ruleListModel, ruleGroupsList, ruleList));

        antecedentList.addListSelectionListener(new AntecedentListSelectListener(antecedentList, antecedentNameTextField));
        saveAtecedentButton.addActionListener(new SaveAntecedentNameActionListener(ruleGroups, ruleGroupsList, ruleList, antecedentList, antecedentListModel, antecedentNameTextField));
        newAtecedentButton.addActionListener(new CreateAntecedentNameActionListener(ruleGroups, ruleGroupsList, ruleList, antecedentListModel, antecedentNameTextField));
        deleteAtecedentButton.addActionListener(new DeleteAntecedentNameActionListener(ruleGroups, ruleGroupsList, ruleList, antecedentList, antecedentListModel));

        knowledgeBrowseButton.addActionListener(new BrowseActionListener(knowledgeSchemeFile, knowledgeSchemeFileTextField, new FileNameExtensionFilter("Knowledge Base Scheme format (*.kbs)", "kbs")));
        verifyButton.addActionListener(new VerifyActionListener(ruleGroups, knowledgeSchemeFile));
    }

    private void createUIComponents() {
        ruleGroupListModel = new DefaultListModel();
        ruleGroupsList = new JList(ruleGroupListModel);

        ruleListModel = new DefaultListModel();
        ruleList = new JList(ruleListModel);

        antecedentListModel = new DefaultListModel();
        antecedentList = new JList(antecedentListModel);
    }

    public void clearFields(){
        ruleGroupListModel.removeAllElements();
        clearFieldsButGroups();
    }

    public void clearFieldsButGroups(){
        ruleListModel.removeAllElements();
        antecedentListModel.removeAllElements();

        ruleGroupNameTextField.setText("");
        consequentTextField.setText("");
        antecedentNameTextField.setText("");
    }
}
