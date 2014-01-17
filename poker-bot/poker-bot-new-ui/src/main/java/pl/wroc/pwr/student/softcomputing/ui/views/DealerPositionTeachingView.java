package pl.wroc.pwr.student.softcomputing.ui.views;

import pl.wroc.pwr.student.softcomputing.ui.components.ImagePanel;
import pl.wroc.pwr.student.softcomputing.ui.listeners.*;
import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 15.01.14.
 */
public class DealerPositionTeachingView {
    private JButton selectDirectoryButton;
    private JTextField imagesTextField;
    private JPanel imagesListPanel;
    private JButton loadImagesButton;
    private JList imagesList;
    private JCheckBox scaleCheckBox;
    private JCheckBox momentumCheckBox;
    private JCheckBox bnwCheckBox;
    private JCheckBox grayscaleCheckBox;
    private JCheckBox maxIterCheckBox;
    private JCheckBox learningRateCheckBox;
    private JCheckBox errorRateCheckBox;
    private JTextField scaleTextField;
    private JTextField maxIterTextField;
    private JTextField learningRateTextField;
    private JTextField errorRateTextField;
    private JTextField momentumTextField;
    private JButton selectOutputFileButton;
    private JTextField outputFileTextField;
    private JButton teachButton;
    private JTextField outputFilename;
    private JPanel imagesPreviewPanel;
    private ImagePanel imagePanel;
    private JPanel dealerPositionPanel;

    private DefaultListModel imagesListModel;

    private FileHolder imagesDir = new FileHolder();
    private FileHolder outputDir = new FileHolder();
    private List<File> imageFilesList = new ArrayList<File>();

    public DealerPositionTeachingView() {
        scaleCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(scaleTextField, scaleCheckBox));
        bnwCheckBox.addChangeListener(new CheckBoxDeselecterListener(bnwCheckBox, grayscaleCheckBox));
        grayscaleCheckBox.addChangeListener(new CheckBoxDeselecterListener(grayscaleCheckBox, bnwCheckBox));
        maxIterCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(maxIterTextField, maxIterCheckBox));
        learningRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(learningRateTextField, learningRateCheckBox));
        errorRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(errorRateTextField, errorRateCheckBox));
        momentumCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(momentumTextField, momentumCheckBox));

        selectDirectoryButton.addActionListener(new SelectDirActionListener(imagesDir, imagesTextField));
        selectOutputFileButton.addActionListener(new SelectDirActionListener(outputDir,outputFileTextField ));
        loadImagesButton.addActionListener(new LoadImagesActionListener(imagesDir, imagesListModel, imageFilesList));

        imagesList.addListSelectionListener(new ImagePreviewListSelectionListener(imagesList, imagePanel, imagesDir));

        teachButton.addActionListener(new TeachActionListener(scaleCheckBox,scaleTextField, bnwCheckBox,
                grayscaleCheckBox, maxIterCheckBox, maxIterTextField, learningRateCheckBox, learningRateTextField,
                errorRateCheckBox, errorRateTextField, momentumCheckBox, momentumTextField, outputFilename, outputDir, imageFilesList,"dealer"));
    }

    private void createUIComponents() {
        imagesListModel = new DefaultListModel();
        imagesList = new JList(imagesListModel);
    }
}
