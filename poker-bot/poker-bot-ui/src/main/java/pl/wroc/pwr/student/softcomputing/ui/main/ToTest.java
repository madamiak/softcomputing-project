package pl.wroc.pwr.student.softcomputing.ui.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

public class ToTest {

	private JFrame frame;
	private JTextField inputDirectoryPathField;
	private JTextField textField;
	private JTextField outputFilePathField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToTest window = new ToTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ToTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 999, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane teachingTab = new JTabbedPane(JTabbedPane.TOP);
		teachingTab.setBounds(0, 0, 983, 605);
		frame.getContentPane().add(teachingTab);
		
		JPanel teachingTabPanel = new JPanel();
		teachingTab.addTab("Teaching", null, teachingTabPanel, null);
		teachingTab.setEnabledAt(0, true);
		GridBagLayout gbl_teachingTabPanel = new GridBagLayout();
		gbl_teachingTabPanel.columnWidths = new int[] {205, 120, 590};
		gbl_teachingTabPanel.rowHeights = new int[]{0, 107, 0, 144, 0, 0, 58, 0};
		gbl_teachingTabPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_teachingTabPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		teachingTabPanel.setLayout(gbl_teachingTabPanel);
		
		JPanel loadingFilesPanel = new JPanel();
		loadingFilesPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Load images", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_loadingFilesPanel = new GridBagConstraints();
		gbc_loadingFilesPanel.insets = new Insets(0, 0, 5, 5);
		gbc_loadingFilesPanel.fill = GridBagConstraints.BOTH;
		gbc_loadingFilesPanel.gridx = 0;
		gbc_loadingFilesPanel.gridy = 0;
		teachingTabPanel.add(loadingFilesPanel, gbc_loadingFilesPanel);
		loadingFilesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inputDirectoryPathField = new JTextField();
		loadingFilesPanel.add(inputDirectoryPathField);
		inputDirectoryPathField.setColumns(10);
		
		JButton browseInputDirectoryButton = new JButton("Browse");
		loadingFilesPanel.add(browseInputDirectoryButton);
		
		JPanel imagePreviewPanel = new JPanel();
		imagePreviewPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		imagePreviewPanel.setLayout(null);
		GridBagConstraints gbc_imagePreviewPanel = new GridBagConstraints();
		gbc_imagePreviewPanel.gridheight = 4;
		gbc_imagePreviewPanel.insets = new Insets(0, 0, 5, 0);
		gbc_imagePreviewPanel.fill = GridBagConstraints.BOTH;
		gbc_imagePreviewPanel.gridx = 2;
		gbc_imagePreviewPanel.gridy = 0;
		teachingTabPanel.add(imagePreviewPanel, gbc_imagePreviewPanel);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(10, 11, 570, 379);
		imagePreviewPanel.add(imagePanel);
		
		JPanel teachingTypePanel = new JPanel();
		teachingTypePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose what to teach", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingTypePanel.setLayout(null);
		GridBagConstraints gbc_teachingTypePanel = new GridBagConstraints();
		gbc_teachingTypePanel.gridheight = 2;
		gbc_teachingTypePanel.insets = new Insets(0, 0, 5, 5);
		gbc_teachingTypePanel.fill = GridBagConstraints.BOTH;
		gbc_teachingTypePanel.gridx = 0;
		gbc_teachingTypePanel.gridy = 1;
		teachingTabPanel.add(teachingTypePanel, gbc_teachingTypePanel);
		
		JRadioButton figureRadioButton = new JRadioButton("Figures");
		figureRadioButton.setBounds(18, 18, 109, 23);
		teachingTypePanel.add(figureRadioButton);
		
		JRadioButton suiteRadioButton = new JRadioButton("Suites");
		suiteRadioButton.setBounds(18, 44, 109, 23);
		teachingTypePanel.add(suiteRadioButton);
		
		JPanel fileListPanel = new JPanel();
		fileListPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Files", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_fileListPanel = new GridBagConstraints();
		gbc_fileListPanel.gridheight = 6;
		gbc_fileListPanel.insets = new Insets(0, 0, 5, 5);
		gbc_fileListPanel.fill = GridBagConstraints.BOTH;
		gbc_fileListPanel.gridx = 1;
		gbc_fileListPanel.gridy = 0;
		teachingTabPanel.add(fileListPanel, gbc_fileListPanel);
		fileListPanel.setLayout(new BorderLayout(0, 0));
		
		JList<String> filesList = new JList<String>();
		fileListPanel.add(filesList, BorderLayout.CENTER);
		
		JPanel teachingExecutionPanel = new JPanel();
		teachingExecutionPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Teaching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingExecutionPanel.setLayout(null);
		GridBagConstraints gbc_teachingExecutionPanel = new GridBagConstraints();
		gbc_teachingExecutionPanel.gridheight = 3;
		gbc_teachingExecutionPanel.fill = GridBagConstraints.BOTH;
		gbc_teachingExecutionPanel.gridx = 2;
		gbc_teachingExecutionPanel.gridy = 4;
		teachingTabPanel.add(teachingExecutionPanel, gbc_teachingExecutionPanel);
		
		JButton executeTeachingButton = new JButton("Teach");
		executeTeachingButton.setBounds(10, 23, 240, 23);
		teachingExecutionPanel.add(executeTeachingButton);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 53, 240, 23);
		teachingExecutionPanel.add(progressBar);
		
		JLabel elapsedTimeLabel = new JLabel("Elapsed time:");
		elapsedTimeLabel.setBounds(10, 87, 84, 14);
		teachingExecutionPanel.add(elapsedTimeLabel);
		
		JLabel ElapsedTimeValueLabel = new JLabel("");
		ElapsedTimeValueLabel.setBounds(99, 87, 46, 14);
		teachingExecutionPanel.add(ElapsedTimeValueLabel);
		
		JPanel teachingParametersPanel = new JPanel();
		teachingParametersPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Teaching parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		teachingParametersPanel.setLayout(null);
		GridBagConstraints gbc_teachingParametersPanel = new GridBagConstraints();
		gbc_teachingParametersPanel.gridheight = 4;
		gbc_teachingParametersPanel.insets = new Insets(0, 0, 0, 5);
		gbc_teachingParametersPanel.fill = GridBagConstraints.BOTH;
		gbc_teachingParametersPanel.gridx = 0;
		gbc_teachingParametersPanel.gridy = 3;
		teachingTabPanel.add(teachingParametersPanel, gbc_teachingParametersPanel);
		
		JCheckBox scaleCheckBox = new JCheckBox("Scale");
		scaleCheckBox.setBounds(16, 21, 90, 23);
		teachingParametersPanel.add(scaleCheckBox);
		
		textField = new JTextField();
		textField.setBounds(112, 22, 78, 20);
		teachingParametersPanel.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxBlackAndWhite = new JCheckBox("Black and white");
		chckbxBlackAndWhite.setBounds(16, 47, 174, 23);
		teachingParametersPanel.add(chckbxBlackAndWhite);
		
		JCheckBox chckbxGrayedScale = new JCheckBox("Grayed scale");
		chckbxGrayedScale.setBounds(16, 73, 174, 23);
		teachingParametersPanel.add(chckbxGrayedScale);
		
		JPanel savingFilePanel = new JPanel();
		savingFilePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Output file", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_savingFilePanel = new GridBagConstraints();
		gbc_savingFilePanel.insets = new Insets(0, 0, 5, 5);
		gbc_savingFilePanel.fill = GridBagConstraints.BOTH;
		gbc_savingFilePanel.gridx = 1;
		gbc_savingFilePanel.gridy = 6;
		teachingTabPanel.add(savingFilePanel, gbc_savingFilePanel);
		savingFilePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		outputFilePathField = new JTextField();
		savingFilePanel.add(outputFilePathField);
		outputFilePathField.setColumns(10);
		
		JButton browseOutputFileButton = new JButton("Browse");
		savingFilePanel.add(browseOutputFileButton);
		
		JPanel recognizingTabPanel = new JPanel();
		teachingTab.addTab("Recognizing", null, recognizingTabPanel, null);
	}
}
