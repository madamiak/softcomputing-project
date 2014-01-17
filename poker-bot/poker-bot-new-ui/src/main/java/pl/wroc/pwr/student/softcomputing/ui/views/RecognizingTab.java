package pl.wroc.pwr.student.softcomputing.ui.views;

import pl.wroc.pwr.student.softcomputing.ui.components.ImagePanel;
import pl.wroc.pwr.student.softcomputing.ui.listeners.*;
import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.LabelUtil;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 05.01.14.
 */
public class RecognizingTab {
    private JPanel recognizingTab;
    private ImagePanel imagePanel;
    private JTextField imagesTextField;
    private JButton selectDirectoryButton;
    private JPanel figuresImagesListPanel;
    private JButton loadImagesButton;
    private JList imagesList;
    private JTextField dealerTextField;
    private JButton dealerPickButton;
    private JLabel dealerInfoLabel;
    private JTextField playerFiguresTextField;
    private JButton playerFiguresPickButton;
    private JLabel playerFiguresInfoLabel;
    private JTextField playerSuitsTextField;
    private JButton playerSuitsPickButton;
    private JLabel playerSuitsInfoLabel;
    private JTextArea outPutTextArea;
    private JButton clearOutputButton;
    private JButton saveOutputButton;
    private JButton recognizeSelectedButton;
    private JButton recognizeAllButton;

    private DefaultListModel imagesListModel;

    private FileHolder figuresDatasource = new FileHolder();
    private FileHolder suitsDatasource = new FileHolder();
    private FileHolder dealerDatasource = new FileHolder();

    private FileHolder imagesDir = new FileHolder();
    private List<File> imageFilesList = new ArrayList<File>();

    public RecognizingTab() {
        playerFiguresInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        playerSuitsInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        dealerInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));

        selectDirectoryButton.addActionListener(new SelectDirActionListener(imagesDir, imagesTextField));
        imagesList.addListSelectionListener(new ImagePreviewListSelectionListener(imagesList, imagePanel, imagesDir));

        playerFiguresPickButton.addActionListener(new SelectFileActionListener(figuresDatasource,playerFiguresTextField,playerFiguresInfoLabel));
        playerSuitsPickButton.addActionListener(new SelectFileActionListener(suitsDatasource,playerSuitsTextField,playerSuitsInfoLabel));
        dealerPickButton.addActionListener(new SelectFileActionListener(dealerDatasource,dealerTextField,dealerInfoLabel));

        loadImagesButton.addActionListener(new LoadImagesActionListener(imagesDir, imagesListModel, imageFilesList));
        saveOutputButton.addActionListener(new SaveTextFileActionListener(outPutTextArea));
        clearOutputButton.addActionListener(new ClearTextAreaActionListener(outPutTextArea));
        recognizeSelectedButton.addActionListener(new RecognizeSelectedActionListener(imageFilesList,outPutTextArea, imagesList, figuresDatasource, suitsDatasource, dealerDatasource));
        recognizeAllButton.addActionListener(new RecognizeAllActionListener(imageFilesList,outPutTextArea, imagesList, figuresDatasource, suitsDatasource, dealerDatasource));

        Font font = new Font("Verdana", Font.PLAIN, 10);
        outPutTextArea.setFont(font);
    }

    private void createUIComponents() {
        imagesListModel = new DefaultListModel();
        imagesList = new JList(imagesListModel);
    }


}
