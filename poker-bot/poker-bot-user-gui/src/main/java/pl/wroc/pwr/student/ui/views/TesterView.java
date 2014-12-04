package pl.wroc.pwr.student.ui.views;

import pl.wroc.pwr.student.ui.component.ImagePanel;
import pl.wroc.pwr.student.ui.listeners.*;
import pl.wroc.pwr.student.ui.utils.FileHolder;
import pl.wroc.pwr.student.ui.utils.LabelUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 04.05.14.
 */
public class TesterView {
    private JPanel mainPanel;
    private JTextField imagesDirTextField;
    private JButton pickImagesDirectoryButton;
    private JList imagesList;
    private JTextField playerSuitsTextField;
    private JButton playerSuitsPickButton;
    private JLabel playerSuitsInfoLabel;
    private JButton playerFiguresPickButton;
    private JLabel playerFiguresInfoLabel;
    private JTextField playerFiguresTextField;
    private JTextField dealerTextField;
    private JButton dealerPickButton;
    private JLabel dealerInfoLabel;
    private JButton markAsWrongButton;
    private JButton recognizeSelectedButton;
    private JButton testAllButton;
    private JButton loadAllButton;
    private JTextArea outPutTextArea;
    private ImagePanel imagePanel;
    private JTextField knowledgeSchemeTextField;
    private JTextField rulesTextField;
    private JButton pickKnowledgeSchemeButton;
    private JButton pickRulesButton;
    private JButton recognizeActiveButton;
    private JLabel activeInfoLabel;

    private FileHolder figuresDatasource = new FileHolder();
    private FileHolder suitsDatasource = new FileHolder();
    private FileHolder dealerDatasource = new FileHolder();

    private FileHolder imagesDir = new FileHolder();
    private FileHolder ruleGroupsFile = new FileHolder();
    private FileHolder knowledgeSchemeFile = new FileHolder();
    private List<File> imageFilesList = new ArrayList<File>();
    private DefaultListModel imagesListModel;

    public TesterView() {


        playerFiguresInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        playerSuitsInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        dealerInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        activeInfoLabel.setText(LabelUtil.wrapOrange("Active info"));

        pickRulesButton.addActionListener(new BrowseActionListener(ruleGroupsFile, rulesTextField, new FileNameExtensionFilter("Rule Groups format (*.rgp)", "rgp")));
        pickKnowledgeSchemeButton.addActionListener(new BrowseActionListener(knowledgeSchemeFile, knowledgeSchemeTextField, new FileNameExtensionFilter("Knowledge Base Scheme format (*.kbs)", "kbs")));
        pickImagesDirectoryButton.addActionListener(new SelectDirActionListener(imagesDir, imagesDirTextField, imagesListModel, imageFilesList));
        imagesList.addListSelectionListener(new ImagePreviewListSelectionListener(imagesList, imagePanel, imagesDir));

        loadAllButton.addActionListener(new LoadAllFIlesActionListener(ruleGroupsFile, rulesTextField,knowledgeSchemeFile, knowledgeSchemeTextField,figuresDatasource,playerFiguresTextField,playerFiguresInfoLabel,suitsDatasource,playerSuitsTextField,playerSuitsInfoLabel,dealerDatasource, dealerTextField, dealerInfoLabel));

        markAsWrongButton.addActionListener(new MarkFileActionListener(imagesList,imageFilesList,imagesListModel));

        playerFiguresPickButton.addActionListener(new SelectFileActionListener(figuresDatasource,playerFiguresTextField,playerFiguresInfoLabel));
        playerSuitsPickButton.addActionListener(new SelectFileActionListener(suitsDatasource,playerSuitsTextField,playerSuitsInfoLabel));
        dealerPickButton.addActionListener(new SelectFileActionListener(dealerDatasource, dealerTextField, dealerInfoLabel));

        recognizeSelectedButton.addActionListener(new RecognizeSelectedActionListener(ruleGroupsFile,knowledgeSchemeFile,dealerDatasource,suitsDatasource,figuresDatasource,imagesList,outPutTextArea, imageFilesList));
        recognizeActiveButton.addActionListener(new RecognizeActiveActionListener(outPutTextArea, imagePanel, recognizeActiveButton, figuresDatasource, suitsDatasource, dealerDatasource, ruleGroupsFile, knowledgeSchemeFile, activeInfoLabel));
        testAllButton.addActionListener(new TestAllActionListener(ruleGroupsFile,knowledgeSchemeFile,dealerDatasource,suitsDatasource,figuresDatasource,outPutTextArea, imageFilesList));

        Font font = new Font("Verdana", Font.PLAIN, 10);
        outPutTextArea.setFont(font);
    }



    private void createUIComponents() {
        imagesListModel = new DefaultListModel();
        imagesList = new JList(imagesListModel);
    }
}
