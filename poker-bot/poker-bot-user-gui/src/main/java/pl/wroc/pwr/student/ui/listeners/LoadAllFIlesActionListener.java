package pl.wroc.pwr.student.ui.listeners;


import pl.wroc.pwr.student.ui.utils.FileHolder;
import pl.wroc.pwr.student.ui.utils.LabelUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class LoadAllFIlesActionListener implements ActionListener {
    FileHolder ruleGroupsFile;
    JTextField rulesTextField;
    FileHolder knowledgeSchemeFile;
    JTextField knowledgeSchemeTextField;
    FileHolder figuresDatasource;
    JTextField playerFiguresTextField;
    JLabel playerFiguresInfoLabel;
    FileHolder suitsDatasource;
    JTextField playerSuitsTextField;
    JLabel playerSuitsInfoLabel;
    FileHolder dealerDatasource;
    JTextField dealerTextField;
    JLabel dealerInfoLabel;

    public LoadAllFIlesActionListener(FileHolder ruleGroupsFile, JTextField rulesTextField, FileHolder knowledgeSchemeFile, JTextField knowledgeSchemeTextField, FileHolder figuresDatasource, JTextField playerFiguresTextField, JLabel playerFiguresInfoLabel, FileHolder suitsDatasource, JTextField playerSuitsTextField, JLabel playerSuitsInfoLabel, FileHolder dealerDatasource, JTextField dealerTextField, JLabel dealerInfoLabel) {
        this.ruleGroupsFile = ruleGroupsFile;
        this.rulesTextField = rulesTextField;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
        this.knowledgeSchemeTextField = knowledgeSchemeTextField;
        this.figuresDatasource = figuresDatasource;
        this.playerFiguresTextField = playerFiguresTextField;
        this.playerFiguresInfoLabel = playerFiguresInfoLabel;
        this.suitsDatasource = suitsDatasource;
        this.playerSuitsTextField = playerSuitsTextField;
        this.playerSuitsInfoLabel = playerSuitsInfoLabel;
        this.dealerDatasource = dealerDatasource;
        this.dealerTextField = dealerTextField;
        this.dealerInfoLabel = dealerInfoLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Select fikes directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("Selected file directory: " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection");
        }
        List<File> fileList = Arrays.asList(chooser.getSelectedFile().listFiles());
        for(File file : fileList){
            if(file.getName().endsWith(".rgp")){
                ruleGroupsFile.setFileDir(file.toString());
                rulesTextField.setText(file.toString());
            }else if(file.getName().endsWith(".kbs")){
                knowledgeSchemeFile.setFileDir(file.toString());
                knowledgeSchemeTextField.setText(file.toString());
            }else if(file.getName().endsWith(".nnet")&&validateFilename(file.toString())){
                if(file.getName().contains("Dealer")){
                    dealerTextField.setText(file.toString());
                    dealerInfoLabel.setText(LabelUtil.wrapGreen("File picked"));
                    dealerDatasource.setFileDir(file.toString());
                }else if(file.getName().contains("Figures")){
                    playerFiguresTextField.setText(file.toString());
                    playerFiguresInfoLabel.setText(LabelUtil.wrapGreen("File picked"));
                    figuresDatasource.setFileDir(file.toString());
                }else if(file.getName().contains("Suits")){
                    playerSuitsTextField.setText(file.toString());
                    playerSuitsInfoLabel.setText(LabelUtil.wrapGreen("File picked"));
                    suitsDatasource.setFileDir(file.toString());
                }
            }
        }
    }

    private boolean validateFilename(String filename){
        String[] elements = filename.split("-");
        try{
            int i = elements.length-3;
            if(!elements[i].equals(""))
                Float.parseFloat(elements[i].replace(",","."));
            i++;
            if(!(elements[i].equals("")||elements[i].equals("t")))
                return false;
            i++;
            elements[i]=elements[i].substring(0,elements[i].length()-5);
            System.out.println(elements[i]);
            if(!(elements[i].equals("")||elements[i].equals("t")))
                return false;
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
