package pl.wroc.pwr.student.softcomputing.ui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.ImageRecognitionPlugin;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.ui.main.listeners.ChooseDirectoryListener;
import pl.wroc.pwr.student.softcomputing.ui.main.listeners.ChooseFileListener;
import pl.wroc.pwr.student.softcomputing.ui.main.listeners.ClickListListener;
import pl.wroc.pwr.student.softcomputing.ui.main.listeners.ClickTeachButtonListener;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.FilesListContainer;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasFilesList;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasImage;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasNetworkParameters;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasProgress;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTeachingParameters;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTeachingType;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTextValue;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.ImageCanvas;

public class MainView implements MainDisplay {

	private JFrame frame;
	private ChooseDirectoryListener chooseDirectoryListener = new ChooseDirectoryListener();
	private ChooseFileListener chooseFileListener = new ChooseFileListener();
	private ClickListListener clickListListener = new ClickListListener();
	private ClickTeachButtonListener clickTeachButtonListener = new ClickTeachButtonListener();
	private JTextField inputDirectoryPathField;
	private JTextField scaleField;
	private JTextField outputFilePathField;
	private JTabbedPane teachingTab;
	private JPanel teachingTabPanel;
	private JPanel recognizingTabPanel;
	private FilesListContainer listDataModel = new FilesListContainer();
	private ImageCanvas imageCanvas = new ImageCanvas();
	private JList<File> filesList;
	private HasTeachingParameters teachingParameters;
	private HasTextValue inputDirectory;
	private HasTextValue outputFile;
	private HasProgress progress;
	private HasTeachingType teachingType;
	private HasNetworkParameters networkParameters;
	private JTextField textField_5;
	private File nnetFile;
	private File image;

	public MainView() {
		initialize();
	}

	@Override
	public void setFrameVisible(boolean visible) {
		frame.setVisible(visible);
	}

	@Override
	public void setMainController(MainController mainController) {
		chooseDirectoryListener.setController(mainController);
		chooseFileListener.setController(mainController);
		clickListListener.setController(mainController);
		clickTeachButtonListener.setController(mainController);
	}

	@Override
	public HasFilesList getFilesList() {
		return listDataModel;
	}

	@Override
	public HasImage getImage() {
		return imageCanvas;
	}

	@Override
	public HasTextValue getInputDirectory() {
		return inputDirectory;
	}

	@Override
	public HasTextValue getOutputFile() {
		return outputFile;
	}

	@Override
	public HasProgress getProgress() {
		return progress;
	}

	@Override
	public HasTeachingParameters getTeachingParameters() {
		return teachingParameters;
	}

	@Override
	public HasNetworkParameters getNetworkParameters() {
		return networkParameters;
	}

	@Override
	public HasTeachingType getTeachingType() {
		return teachingType;
	}

	@Override
	public void refreshList() {
		filesList.setListData(listDataModel);
	}

	@Override
	public void refreshCanvas() {
		imageCanvas.repaint();
	}

	private void initialize() {
		createFrame();
		createMenuBar();
		createContent();
	}

	private void createMenuBar() {
		JMenuBar menuBar = setupMenuBar();
		JMenu fileMenu = setupFileMenu(menuBar);
		addExitMenuItem(fileMenu);
	}

	private void addExitMenuItem(JMenu fileMenu) {
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		frame.getContentPane().setLayout(null);
	}

	private JMenu setupFileMenu(JMenuBar menuBar) {
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		return fileMenu;
	}

	private JMenuBar setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		return menuBar;
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 999, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	private void createContent() {
		createTab();
		addTeachingTab();
		addRecognizingTab();
	}

	private void createTab() {
		teachingTab = new JTabbedPane(JTabbedPane.TOP);
		teachingTab.setBounds(0, 0, 983, 605);
		frame.getContentPane().add(teachingTab);
	}

	private void addTeachingTab() {
		createTeachingTabPanel();
		addLoadingFilesPanel();
		addTeachingTypePanel();
		addTeachingParametersPanel();
		addFileListPanel();
		addImagePreviewPanel();
		addSavingFilePanel();
		addTeachingExecutionPanel();
	}

	private void createTeachingTabPanel() {
		teachingTabPanel = new JPanel();
		teachingTab.addTab("Teaching", null, teachingTabPanel, null);
		teachingTab.setEnabledAt(0, true);
		GridBagLayout gbl_teachingTabPanel = new GridBagLayout();
		gbl_teachingTabPanel.columnWidths = new int[] { 205, 120, 590 };
		gbl_teachingTabPanel.rowHeights = new int[] { 0, 107, 0, 62, 0, 0, 58,
				0 };
		gbl_teachingTabPanel.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_teachingTabPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, Double.MIN_VALUE };
		teachingTabPanel.setLayout(gbl_teachingTabPanel);
	}

	private void addLoadingFilesPanel() {
		JPanel loadingFilesPanel = setupLoadingFilesPanel();
		addInputDirectoryPathField(loadingFilesPanel);
		addBrowseDirectoryButton(loadingFilesPanel);
	}

	private JPanel setupLoadingFilesPanel() {
		JPanel loadingFilesPanel = new JPanel();
		loadingFilesPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Load images",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_loadingFilesPanel = new GridBagConstraints();
		gbc_loadingFilesPanel.insets = new Insets(0, 0, 5, 5);
		gbc_loadingFilesPanel.fill = GridBagConstraints.BOTH;
		gbc_loadingFilesPanel.gridx = 0;
		gbc_loadingFilesPanel.gridy = 0;
		teachingTabPanel.add(loadingFilesPanel, gbc_loadingFilesPanel);
		loadingFilesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		return loadingFilesPanel;
	}

	private void addInputDirectoryPathField(JPanel loadingFilesPanel) {
		inputDirectoryPathField = new JTextField();
		inputDirectory = new HasTextValue(inputDirectoryPathField);
		loadingFilesPanel.add(inputDirectoryPathField);
		inputDirectoryPathField.setColumns(10);
	}

	private void addBrowseDirectoryButton(JPanel savingFilePanel) {
		JButton browseOutputFileButton = new JButton("Browse");
		browseOutputFileButton.addActionListener(chooseDirectoryListener);
		savingFilePanel.add(browseOutputFileButton);
	}

	private void addTeachingTypePanel() {
		JPanel teachingTypePanel = setupTeachingTypePanel();
		ButtonGroup teachingTypeButtonGroup = new ButtonGroup();
		JRadioButton figureRadioButton = createFigureRadioButton();
		JRadioButton suitRadioButton = createSuitRadioButton();
		teachingTypePanel.add(suitRadioButton);
		teachingTypePanel.add(figureRadioButton);
		teachingTypeButtonGroup.add(suitRadioButton);
		teachingTypeButtonGroup.add(figureRadioButton);
		teachingType = new HasTeachingType(figureRadioButton, suitRadioButton);
	}

	private JPanel setupTeachingTypePanel() {
		JPanel teachingTypePanel = new JPanel();
		teachingTypePanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Choose what to teach",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingTypePanel.setLayout(null);
		GridBagConstraints gbc_teachingTypePanel = new GridBagConstraints();
		gbc_teachingTypePanel.gridheight = 2;
		gbc_teachingTypePanel.insets = new Insets(0, 0, 5, 5);
		gbc_teachingTypePanel.fill = GridBagConstraints.BOTH;
		gbc_teachingTypePanel.gridx = 0;
		gbc_teachingTypePanel.gridy = 1;
		teachingTabPanel.add(teachingTypePanel, gbc_teachingTypePanel);
		return teachingTypePanel;
	}

	private JRadioButton createFigureRadioButton() {
		JRadioButton figureRadioButton = new JRadioButton("Figures");
		figureRadioButton.setBounds(18, 18, 109, 23);
		figureRadioButton.setSelected(true);
		return figureRadioButton;
	}

	private JRadioButton createSuitRadioButton() {
		JRadioButton suitRadioButton = new JRadioButton("Suits");
		suitRadioButton.setBounds(18, 44, 109, 23);
		return suitRadioButton;
	}

	private void addTeachingParametersPanel() {
		JPanel teachingParametersPanel = setupTeachingParametersPanel();
		JCheckBox scaleCheckBox = createScaleCheckBox();
		JTextField scaleTextField = createScaleTextField();
		JCheckBox blackAndWhiteCheckBox = createBlackAndWhiteCheckBox();
		JCheckBox grayedScaleCheckBox = createGrayedScaleCheckBox();
		JCheckBox maxIterationsCheckBox = createMaxIterationsCheckBox();
		JTextField maxIterationsField = createMaxIterationsField();
		JCheckBox learningRateCheckBox = createLearningRateCheckBox();
		JTextField learningRateField = createLearningRateField();
		JCheckBox errorRateCheckBox = createErrorRateCheckBox();
		JTextField errorRateField = createMaxErrorRateField();
		JCheckBox momentumCheckBox = createMomentumCheckBox();
		JTextField momentumField = createMomentumField();
		teachingParameters = new HasTeachingParameters(scaleCheckBox,
				scaleTextField, blackAndWhiteCheckBox, grayedScaleCheckBox);
		networkParameters = new HasNetworkParameters(maxIterationsField,
				learningRateField, errorRateField, momentumField);
		teachingParametersPanel.add(scaleCheckBox);
		teachingParametersPanel.add(scaleTextField);
		teachingParametersPanel.add(blackAndWhiteCheckBox);
		teachingParametersPanel.add(grayedScaleCheckBox);
		teachingParametersPanel.add(maxIterationsCheckBox);
		teachingParametersPanel.add(maxIterationsField);
		teachingParametersPanel.add(learningRateCheckBox);
		teachingParametersPanel.add(learningRateField);
		teachingParametersPanel.add(errorRateCheckBox);
		teachingParametersPanel.add(errorRateField);
		teachingParametersPanel.add(momentumCheckBox);
		teachingParametersPanel.add(momentumField);
	}

	private JPanel setupTeachingParametersPanel() {
		JPanel teachingParametersPanel = new JPanel();
		teachingParametersPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Teaching parameters",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingParametersPanel.setLayout(null);
		GridBagConstraints gbc_teachingParametersPanel = new GridBagConstraints();
		gbc_teachingParametersPanel.gridheight = 4;
		gbc_teachingParametersPanel.insets = new Insets(0, 0, 0, 5);
		gbc_teachingParametersPanel.fill = GridBagConstraints.BOTH;
		gbc_teachingParametersPanel.gridx = 0;
		gbc_teachingParametersPanel.gridy = 3;
		teachingTabPanel.add(teachingParametersPanel,
				gbc_teachingParametersPanel);
		return teachingParametersPanel;
	}

	private JCheckBox createScaleCheckBox() {
		JCheckBox scaleCheckBox = new JCheckBox("Scale");
		scaleCheckBox.setBounds(16, 21, 90, 23);
		return scaleCheckBox;
	}

	private JTextField createScaleTextField() {
		scaleField = new JTextField();
		scaleField.setBounds(112, 22, 78, 20);
		scaleField.setColumns(10);
		return scaleField;
	}

	private JCheckBox createBlackAndWhiteCheckBox() {
		JCheckBox chckbxBlackAndWhite = new JCheckBox("Black and white");
		chckbxBlackAndWhite.setBounds(16, 47, 174, 23);
		return chckbxBlackAndWhite;
	}

	private JCheckBox createGrayedScaleCheckBox() {
		JCheckBox chckbxGrayedScale = new JCheckBox("Grayed scale");
		chckbxGrayedScale.setBounds(16, 73, 174, 23);
		return chckbxGrayedScale;
	}

	private JCheckBox createMaxIterationsCheckBox() {
		JCheckBox chckbxMaxIterations = new JCheckBox("Max iterations");
		chckbxMaxIterations.setBounds(16, 99, 97, 23);
		return chckbxMaxIterations;
	}

	private JCheckBox createLearningRateCheckBox() {
		JCheckBox chckbxLearningRate = new JCheckBox("Learning rate");
		chckbxLearningRate.setBounds(16, 125, 97, 23);
		return chckbxLearningRate;
	}

	private JCheckBox createErrorRateCheckBox() {
		JCheckBox chckbxErrorRate = new JCheckBox("Error rate");
		chckbxErrorRate.setBounds(16, 151, 97, 23);
		return chckbxErrorRate;
	}

	private JCheckBox createMomentumCheckBox() {
		JCheckBox chckbxMomentum = new JCheckBox("Momentum");
		chckbxMomentum.setBounds(16, 177, 97, 23);
		return chckbxMomentum;
	}

	private JTextField createMaxIterationsField() {
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(112, 100, 78, 20);
		textField_1.setColumns(10);
		return textField_1;
	}

	private JTextField createLearningRateField() {
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(112, 126, 78, 20);
		textField_2.setColumns(10);
		return textField_2;
	}

	private JTextField createMaxErrorRateField() {
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(112, 152, 78, 20);
		textField_3.setColumns(10);
		return textField_3;
	}

	private JTextField createMomentumField() {
		JTextField textField_4 = new JTextField();
		textField_4.setBounds(112, 178, 78, 20);
		textField_4.setColumns(10);
		return textField_4;
	}

	private void addFileListPanel() {
		JPanel fileListPanel = setupFileListPanel();
		addFilesList(fileListPanel);
	}

	private JPanel setupFileListPanel() {
		JPanel fileListPanel = new JPanel();
		fileListPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Files",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_fileListPanel = new GridBagConstraints();
		gbc_fileListPanel.gridheight = 6;
		gbc_fileListPanel.insets = new Insets(0, 0, 5, 5);
		gbc_fileListPanel.fill = GridBagConstraints.BOTH;
		gbc_fileListPanel.gridx = 1;
		gbc_fileListPanel.gridy = 0;
		teachingTabPanel.add(fileListPanel, gbc_fileListPanel);
		fileListPanel.setLayout(new BorderLayout(0, 0));
		return fileListPanel;
	}

	private void addFilesList(JPanel fileListPanel) {
		filesList = new JList<File>();
		filesList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		filesList.addListSelectionListener(clickListListener);
		fileListPanel.add(new JScrollPane(filesList), BorderLayout.CENTER);
	}

	private void addImagePreviewPanel() {
		JPanel imagePreviewPanel = setupImagePreviewPanel();
		addImagePanel(imagePreviewPanel);
	}

	private JPanel setupImagePreviewPanel() {
		JPanel imagePreviewPanel = new JPanel();
		imagePreviewPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Preview",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		imagePreviewPanel.setLayout(null);
		GridBagConstraints gbc_imagePreviewPanel = new GridBagConstraints();
		gbc_imagePreviewPanel.gridheight = 4;
		gbc_imagePreviewPanel.insets = new Insets(0, 0, 5, 0);
		gbc_imagePreviewPanel.fill = GridBagConstraints.BOTH;
		gbc_imagePreviewPanel.gridx = 2;
		gbc_imagePreviewPanel.gridy = 0;
		teachingTabPanel.add(imagePreviewPanel, gbc_imagePreviewPanel);
		return imagePreviewPanel;
	}

	private void addImagePanel(JPanel imagePreviewPanel) {
		imageCanvas.setBounds(10, 11, 570, 379);
		imagePreviewPanel.add(imageCanvas);
	}

	private void addSavingFilePanel() {
		JPanel savingFilePanel = setupSavingFilePanel();
		addOutputFilePathField(savingFilePanel);
		addBrowseFileButton(savingFilePanel);
	}

	private JPanel setupSavingFilePanel() {
		JPanel savingFilePanel = new JPanel();
		savingFilePanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Output file",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_savingFilePanel = new GridBagConstraints();
		gbc_savingFilePanel.insets = new Insets(0, 0, 5, 5);
		gbc_savingFilePanel.fill = GridBagConstraints.BOTH;
		gbc_savingFilePanel.gridx = 1;
		gbc_savingFilePanel.gridy = 6;
		teachingTabPanel.add(savingFilePanel, gbc_savingFilePanel);
		savingFilePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		return savingFilePanel;
	}

	private void addOutputFilePathField(JPanel savingFilePanel) {
		outputFilePathField = new JTextField();
		outputFile = new HasTextValue(outputFilePathField);
		savingFilePanel.add(outputFilePathField);
		outputFilePathField.setColumns(10);
	}

	private void addBrowseFileButton(JPanel savingFilePanel) {
		JButton browseOutputFileButton = new JButton("Browse");
		browseOutputFileButton.addActionListener(chooseFileListener);
		savingFilePanel.add(browseOutputFileButton);
	}

	private void addTeachingExecutionPanel() {
		JPanel teachingExecutionPanel = setupTeachingExecutionPanel();
		addTeachButton(teachingExecutionPanel);
		addProgressBar(teachingExecutionPanel);
		addElapsedTimeLabel(teachingExecutionPanel);
		addElapsedTimeValueLabel(teachingExecutionPanel);
	}

	private JPanel setupTeachingExecutionPanel() {
		JPanel teachingExecutionPanel = new JPanel();
		teachingExecutionPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Teaching",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingExecutionPanel.setLayout(null);
		GridBagConstraints gbc_teachingExecutionPanel = new GridBagConstraints();
		gbc_teachingExecutionPanel.gridheight = 3;
		gbc_teachingExecutionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_teachingExecutionPanel.fill = GridBagConstraints.BOTH;
		gbc_teachingExecutionPanel.gridx = 2;
		gbc_teachingExecutionPanel.gridy = 4;
		teachingTabPanel
				.add(teachingExecutionPanel, gbc_teachingExecutionPanel);
		return teachingExecutionPanel;
	}

	private void addTeachButton(JPanel teachingExecutionPanel) {
		JButton executeTeachingButton = new JButton("Teach");
		executeTeachingButton.setBounds(10, 23, 240, 23);
		executeTeachingButton.addActionListener(clickTeachButtonListener);
		teachingExecutionPanel.add(executeTeachingButton);
	}

	private void addProgressBar(JPanel teachingExecutionPanel) {
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 53, 240, 23);
		progress = new HasProgress(progressBar);
		teachingExecutionPanel.add(progressBar);
	}

	private void addElapsedTimeLabel(JPanel teachingExecutionPanel) {
		JLabel elapsedTimeLabel = new JLabel("Elapsed time:");
		elapsedTimeLabel.setBounds(10, 87, 84, 14);
		teachingExecutionPanel.add(elapsedTimeLabel);
	}

	private void addElapsedTimeValueLabel(JPanel teachingExecutionPanel) {
		JLabel ElapsedTimeValueLabel = new JLabel("");
		ElapsedTimeValueLabel.setBounds(99, 87, 46, 14);
		teachingExecutionPanel.add(ElapsedTimeValueLabel);
	}

	private void addRecognizingTab() {
		createRecognizingTabPanel();
	}

	private void createRecognizingTabPanel() {
		recognizingTabPanel = new JPanel();
		teachingTab.addTab("Recognizing", null, recognizingTabPanel, null);
		recognizingTabPanel.setLayout(null);

		final ImageCanvas panel = new ImageCanvas();
		panel.setBounds(10, 45, 560, 350);
		recognizingTabPanel.add(panel);

		JButton btnBrowse = new JButton("Browse image");
		btnBrowse.setBounds(10, 11, 150, 23);
		btnBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("Choose");
				fileChooser.setDialogTitle("Choose file to recognize");
				fileChooser.setCurrentDirectory(new File(".."));
				fileChooser.showOpenDialog(null);
				image = fileChooser.getSelectedFile();
				panel.set(image);
				panel.repaint();
			}
		});
		recognizingTabPanel.add(btnBrowse);

		JButton btnNnet = new JButton("Browse data source");
		btnNnet.setBounds(170, 11, 150, 23);
		btnNnet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("Choose");
				fileChooser.setDialogTitle("Choose file to recognize");
				fileChooser.setCurrentDirectory(new File(".."));
				fileChooser.showOpenDialog(null);
				nnetFile = fileChooser.getSelectedFile();
			}
		});
		recognizingTabPanel.add(btnNnet);

		JButton btnRecognize = new JButton("Recognize");
		btnRecognize.setBounds(10, 406, 100, 23);
		btnRecognize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NeuralNetwork nnet = NeuralNetwork.load(nnetFile
						.getAbsolutePath());
				TableParser parser = new TableParserImpl();
				parser.loadTable(image.getAbsolutePath());
				String[] params = nnetFile.getName().substring(0, nnetFile.getName().indexOf(".")).split("-");
				String teachingtype = params[0];
				String scale = params[1];
				String bnw = params[2];
				String g = params[3];
				System.out.println(teachingtype+scale+bnw+g);
				textField_5.setText("");
				if (teachingtype.equals("f")) {
					BufferedImage card1 = parser.parseFirstCard();
					recognize(nnet, prepareInput(card1, bnw.equals("y"), g.equals("y"),
							Double.parseDouble("0." + scale)));
					BufferedImage card2 = parser.parseSecondCard();
					recognize(nnet, prepareInput(card2, bnw.equals("y"), g.equals("y"),
							Double.parseDouble("0." + scale)));
				} else if (teachingtype.equals("s")) {
					BufferedImage suit1 = parser.parseFirstSuit();
                    recognize(nnet, prepareInput(suit1, bnw.equals("y"), g.equals("y"),
                    		Double.parseDouble("0." + scale)));
                    BufferedImage suit2 = parser.parseSecondSuit();
                    recognize(nnet, prepareInput(suit2, bnw.equals("y"), g.equals("y"),
                    		Double.parseDouble("0." + scale)));
				}
			}
		});
		recognizingTabPanel.add(btnRecognize);

		textField_5 = new JTextField();
		textField_5.setBounds(10, 440, 86, 20);
		recognizingTabPanel.add(textField_5);
		textField_5.setColumns(10);
	}

	private double[] prepareInput(BufferedImage image, boolean blackAndWhite,
			boolean grayed, double scalingRatio) {
		final ImageProcessor processor = new ImageProcessorImpl();
		image = processor.scale(image, scalingRatio);
		if (blackAndWhite) {
			image = processor.convertToBlackAndWhite(image);
		}
		if (grayed) {
			image = processor.convertToGrayscale(image);
		}
		final ImageConverter converter = new ImageToArrayConverter();
		return converter.convert(image);
	}

	private void recognize(NeuralNetwork loadedMlPerceptron, double[] input) {
		loadedMlPerceptron.setInput(input);
		loadedMlPerceptron.calculate();
		double[] networkOutput = loadedMlPerceptron.getOutput();
		System.out.println(String.format("Output: %s\tInput: %s",
				convertOutput(networkOutput), Arrays.toString(input)));
		textField_5.setText(textField_5.getText() + convertOutput(networkOutput));
		
	}

	private String convertOutput(double[] output) {
		String out = "";
		for (double d : output) {
			if (d > 0.5) {
				out += "1";
			} else {
				out += "0";
			}
		}
		switch (out) {
		case "0000":
			return "2";
		case "0001":
			return "3";
		case "0010":
			return "4";
		case "0011":
			return "5";
		case "0100":
			return "6";
		case "0101":
			return "7";
		case "0110":
			return "8";
		case "0111":
			return "9";
		case "1000":
			return "T";
		case "1001":
			return "J";
		case "1010":
			return "Q";
		case "1011":
			return "K";
		case "1100":
			return "A";
		case "00":
			return "h";
		case "01":
			return "s";
		case "10":
			return "d";
		case "11":
			return "c";
		}

		return null;
	}

}
