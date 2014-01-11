package pl.wroc.pwr.student.softcomputing.ui.views;

import pl.wroc.pwr.student.softcomputing.ui.components.ImagePanel;
import pl.wroc.pwr.student.softcomputing.ui.listeners.*;
import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class PlayerInfoTeachingView {
    private JPanel playerInfoPanel;
    private JButton figuresSelectDirectoryButton;
    private JTextField figuresImagesTextField;
    private JPanel figuresImagesListPanel;
    private JPanel imagesPreviewPanel;
    private JList figuresImagesList;
    private JButton figuresLoadImagesButton;
    private JCheckBox figuresScaleCheckBox;
    private JCheckBox figuresMomentumCheckBox;
    private JCheckBox figuresBNWCheckBox;
    private JCheckBox figuresGrayscaleCheckBox;
    private JCheckBox figuresMaxIterCheckBox;
    private JCheckBox figuresLearningRateCheckBox;
    private JCheckBox figuresErrorRateCheckBox;
    private JTextField figuresMaxIterTextField;
    private JTextField figuresLearningRateTextField;
    private JTextField figuresErrorRateTextField;
    private JTextField figuresMomentumTextField;
    private JTextField figuresScaleTextField;
    private JButton figuresSelectOutputFileButton;
    private JTextField figuresOutputFileTextField;
    private JButton figuresTeachButton;
    private JTextField suitsOutputFileTextField;
    private JButton suitsTeachButton;
    private JCheckBox suitsScaleCheckBox;
    private JTextField suitsMaxIterTextField;
    private JCheckBox suitsBNWCheckBox;
    private JButton suitsSelectOutputFileButton;
    private JButton suitsSelectDirectoryButton;
    private JTextField suitsImagesTextField;
    private JTextField suitsErrorRateTextField;
    private JTextField suitsMomentumTextField;
    private JCheckBox suitsLearningRateCheckBox;
    private JButton suitsLoadImagesButton;
    private JTextField suitsLearningRateTextField;
    private JCheckBox suitsMomentumCheckBox;
    private JCheckBox suitsMaxIterCheckBox;
    private JTextField suitsScaleTextField;
    private JCheckBox suitsGrayscaleCheckBox;
    private JPanel panel1;
    private JCheckBox suitsErrorRateCheckBox;
    private JList suitsImagesList;
    private JTextField figuresOutputFilename;
    private JTextField suitsOutputFilename;
    private ImagePanel imagePanel;

    private DefaultListModel figureImagesListModel;
    private DefaultListModel suitImagesListModel;

    private FileHolder figuresImagesDir = new FileHolder();
    private FileHolder figuresOutputDir = new FileHolder();
    private List<File> figuresImageFilesList = new ArrayList<File>();

    private FileHolder suitsImagesDir = new FileHolder();
    private FileHolder suitsOutputDir = new FileHolder();
    private List<File> suitsImageFilesList = new ArrayList<File>();

    public PlayerInfoTeachingView() {
        figuresScaleCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(figuresScaleTextField, figuresScaleCheckBox));
        figuresBNWCheckBox.addChangeListener(new CheckBoxDeselecterListener(figuresBNWCheckBox, figuresGrayscaleCheckBox));
        figuresGrayscaleCheckBox.addChangeListener(new CheckBoxDeselecterListener(figuresGrayscaleCheckBox, figuresBNWCheckBox));
        figuresMaxIterCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(figuresMaxIterTextField, figuresMaxIterCheckBox));
        figuresLearningRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(figuresLearningRateTextField, figuresLearningRateCheckBox));
        figuresErrorRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(figuresErrorRateTextField, figuresErrorRateCheckBox));
        figuresMomentumCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(figuresMomentumTextField, figuresMomentumCheckBox));

        suitsScaleCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(suitsScaleTextField, suitsScaleCheckBox));
        suitsBNWCheckBox.addChangeListener(new CheckBoxDeselecterListener(suitsBNWCheckBox, suitsGrayscaleCheckBox));
        suitsGrayscaleCheckBox.addChangeListener(new CheckBoxDeselecterListener(suitsGrayscaleCheckBox, suitsBNWCheckBox));
        suitsMaxIterCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(suitsMaxIterTextField, suitsMaxIterCheckBox));
        suitsLearningRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(suitsLearningRateTextField, suitsLearningRateCheckBox));
        suitsErrorRateCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(suitsErrorRateTextField, suitsErrorRateCheckBox));
        suitsMomentumCheckBox.addChangeListener(new CheckBoxEnablerChangeListener(suitsMomentumTextField, suitsMomentumCheckBox));

        figuresSelectDirectoryButton.addActionListener(new SelectDirActionListener(figuresImagesDir, figuresImagesTextField));
        figuresSelectOutputFileButton.addActionListener(new SelectDirActionListener(figuresOutputDir,figuresOutputFileTextField ));
        figuresLoadImagesButton.addActionListener(new LoadImagesActionListener(figuresImagesDir, figureImagesListModel, figuresImageFilesList));

        suitsSelectDirectoryButton.addActionListener(new SelectDirActionListener(suitsImagesDir, suitsImagesTextField));
        suitsSelectOutputFileButton.addActionListener(new SelectDirActionListener(suitsOutputDir,suitsOutputFileTextField ));
        suitsLoadImagesButton.addActionListener(new LoadImagesActionListener(suitsImagesDir, suitImagesListModel, suitsImageFilesList));

        figuresImagesList.addListSelectionListener(new ImagePreviewListSelectionListener(figuresImagesList, imagePanel, figuresImagesDir));
        suitsImagesList.addListSelectionListener(new ImagePreviewListSelectionListener(suitsImagesList, imagePanel, suitsImagesDir));

        figuresTeachButton.addActionListener(new TeachCardsActionListener(figuresScaleCheckBox,figuresScaleTextField, figuresBNWCheckBox,
                figuresGrayscaleCheckBox, figuresMaxIterCheckBox, figuresMaxIterTextField, figuresLearningRateCheckBox, figuresLearningRateTextField,
                figuresErrorRateCheckBox, figuresErrorRateTextField, figuresMomentumCheckBox, figuresMomentumTextField, figuresOutputFilename, figuresOutputDir, figuresImageFilesList,"figure"));
        suitsTeachButton.addActionListener(new TeachCardsActionListener(suitsScaleCheckBox,suitsScaleTextField, suitsBNWCheckBox,
                suitsGrayscaleCheckBox, suitsMaxIterCheckBox, suitsMaxIterTextField, suitsLearningRateCheckBox, suitsLearningRateTextField,
                suitsErrorRateCheckBox, suitsErrorRateTextField, suitsMomentumCheckBox, suitsMomentumTextField, suitsOutputFilename, suitsOutputDir, suitsImageFilesList,"suit"));
    }

    private void createUIComponents() {
        figureImagesListModel = new DefaultListModel();
        suitImagesListModel = new DefaultListModel();
        figuresImagesList = new JList(figureImagesListModel);
        suitsImagesList = new JList(suitImagesListModel);
    }
}
