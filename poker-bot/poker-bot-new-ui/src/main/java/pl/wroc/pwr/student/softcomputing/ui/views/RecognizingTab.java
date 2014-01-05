package pl.wroc.pwr.student.softcomputing.ui.views;

import pl.wroc.pwr.student.softcomputing.ui.components.ImagePanel;
import pl.wroc.pwr.student.softcomputing.ui.listeners.*;
import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.LabelUtil;

import javax.swing.*;
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
    private JTextField foldTextField;
    private JButton foldPickButton;
    private JLabel foldInfoLabel;
    private JTextField dealerTextField;
    private JButton dealerPickButton;
    private JLabel dealerInfoLabel;
    private JTextField numberTextField;
    private JButton numberPickButton;
    private JLabel numberInfoLabel;
    private JTextField bigBlindTextField;
    private JButton bigBlindPickButton;
    private JLabel bigBlindInfoLabel;
    private JTextField playerFiguresTextField;
    private JButton playerFiguresPickButton;
    private JLabel playerFiguresInfoLabel;
    private JTextField playerSuitsTextField;
    private JButton playerSuitsPickButton;
    private JLabel playerSuitsInfoLabel;
    private JTextField playerChipsTextField;
    private JButton playerChipsPickButton;
    private JLabel playerChipsInfoLabel;
    private JTextField opponentChipsTextField;
    private JButton opponentChipsPickButton;
    private JLabel opponentChipsInfoLabel;
    private JTextField opponentAtTableTextField;
    private JButton opponentAtTablePickButton;
    private JLabel opponentAtTableInfoLabel;
    private JTextArea outPutTextArea;
    private JButton clearOutputButton;
    private JButton saveOutputButton;
    private JButton recognizeSelectedButton;

    private DefaultListModel imagesListModel;

    private FileHolder figuresDatasource = new FileHolder();
    private FileHolder suitsDatasource = new FileHolder();

    private FileHolder imagesDir = new FileHolder();
    private List<File> imageFilesList = new ArrayList<File>();

    public RecognizingTab() {
        playerFiguresInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));
        playerSuitsInfoLabel.setText(LabelUtil.wrapOrange("Not picked"));

        selectDirectoryButton.addActionListener(new SelectDirActionListener(imagesDir, imagesTextField));
        imagesList.addListSelectionListener(new ImagePreviewListSelectionListener(imagesList, imagePanel, imagesDir));

        playerFiguresPickButton.addActionListener(new SelectFileActionListener(figuresDatasource,playerFiguresTextField,playerFiguresInfoLabel));
        playerSuitsPickButton.addActionListener(new SelectFileActionListener(suitsDatasource,playerSuitsTextField,playerSuitsInfoLabel));

        loadImagesButton.addActionListener(new LoadImagesActionListener(imagesDir, imagesListModel, imageFilesList));
        saveOutputButton.addActionListener(new SaveTextFileActionListener(outPutTextArea));
        clearOutputButton.addActionListener(new ClearTextAreaActionListener(outPutTextArea));
    }

    private void createUIComponents() {
        imagesListModel = new DefaultListModel();
        imagesList = new JList(imagesListModel);
    }


}
