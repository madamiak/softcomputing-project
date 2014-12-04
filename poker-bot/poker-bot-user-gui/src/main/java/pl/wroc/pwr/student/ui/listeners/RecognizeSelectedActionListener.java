package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.*;
import pl.wroc.pwr.student.softcomputing.pokerbot.utils.ChainingData;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Fold;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.ui.decisions.DecisionMaker;
import pl.wroc.pwr.student.ui.utils.FileHolder;
import pl.wroc.pwr.student.ui.utils.RecognizingDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 06.05.14.
 */
public class RecognizeSelectedActionListener implements ActionListener {
    private List<File> imageFilesList;
    private JTextArea output;
    private JList imageJList;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;
    private final FileHolder ruleGroupsFile;
    private final FileHolder knowledgeSchemeFile;

    public RecognizeSelectedActionListener(FileHolder ruleGroupsFile, FileHolder knowledgeSchemeFile, FileHolder dealerDatasource, FileHolder suitsDatasource, FileHolder figuresDatasource, JList imageJList, JTextArea output, List<File> imageFilesList) {
        this.ruleGroupsFile = ruleGroupsFile;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
        this.dealerDatasource = dealerDatasource;
        this.suitsDatasource = suitsDatasource;
        this.figuresDatasource = figuresDatasource;
        this.imageJList = imageJList;
        this.output = output;
        this.imageFilesList = imageFilesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isInputCorrect())return;
        Table table= RecognizingDelegate.recognize(getSelectedFile(), figuresDatasource, suitsDatasource, dealerDatasource);
        if(table.getFoldButtonStatus().equals(Fold.INACTIVE)){
            output.setText("Buttons inactive.");
            return;
        }
        output.setText(table.smallReport());
        ConvertedData convertedData = getConvertedData(table);

        List<RuleGroup> ruleGroups = null;
        List<KnowledgeCommandData> knowledgeScheme = null;

        try {
            ruleGroups = ExpertAPI.getRuleGroupsFromFile(ruleGroupsFile.getFileDir());
        } catch (IOException e1) {
            displayMessage("Error occurred while reading rule groups from file. \n"+e1);
            e1.printStackTrace();
            return;
        }
        try {
            knowledgeScheme = ExpertAPI.getKnowledgeSchemeFromFile(knowledgeSchemeFile.getFileDir());
        } catch (IOException e1) {
            displayMessage("Error occurred while reading knowledge scheme from file. \n" + e1);
            e1.printStackTrace();
            return;
        }

        KnowledgeParser knowledgeParser = new KnowledgeParser(knowledgeScheme);
        KnowledgeBase knowledgeBase = knowledgeParser.createKnowledgeBase(convertedData);
        Engine engine = ExpertAPI.getEngine(ruleGroups,knowledgeBase);

        String answer="";
        ChainingData chainingData = engine.performBackwardChaining(new Fact("Raise"));
        if(chainingData.isPositive()){
            answer="Player should RAISE in this situation!";
        }else{
            answer="Player should FOLD in this situation!";
        }
        //if(table.getFoldButtonStatus().equals(Fold.ACTIVE))DecisionMaker.makeDecision(chainingData);
        output.append("\n");
        output.append(knowledgeBase.toString());
        output.append("\n\n");
        output.append(answer);
        output.append("\n");
        output.append(convertedData.toString());
        output.append("\n");
        output.append(engine.getOutPut());
        output.append("\n");
        output.append(String.format(String.format("Proven: %d, Unproven: %d ",chainingData.getProvenCount(),chainingData.getUnprovenCount())));
    }

    private boolean isInputCorrect() {
        if(imageFilesList.isEmpty()){
            displayMessage("No images selected.");
            return false;
        }
        if(!isPicked(figuresDatasource)){
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if(!isPicked(suitsDatasource)){
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if(!isPicked(dealerDatasource)){
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if(!isPicked(ruleGroupsFile)){
            displayMessage("No rule groups file picked.");
            return false;
        }
        if(!isPicked(knowledgeSchemeFile)){
            displayMessage("No knowledge scheme file picked.");
            return false;
        }
        return true;
    }

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    private File getSelectedFile() {
        return imageFilesList.get(imageJList.getSelectedIndex());
    }

    private ConvertedData getConvertedData(Table table){
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> borders = new ArrayList<String>();
        borders.add(null);
        for(int i=0; i<6; i++){
            totalChips.add(table.getTotalChipsOf(i));
            chipsAtTable.add(table.getChipsOnTableOf(i));
            if(i>0)
                borders.add(table.getBorderOf(i).toString());
        }
        String fileName = getSelectedFile().toString();
        Converter converter;
        if(fileName.contains("BB")){
            int bb = Integer.parseInt(fileName.split("BB")[1].split("\\.")[0]);
            System.out.println("BB: "+bb);
            System.out.println(table.smallReport());
            converter = new Converter(table.getFoldButtonStatus()== Fold.ACTIVE,table.getDealerPosition(),table.getFirstCard().toString(),table.getSecondCard().toString(),totalChips,chipsAtTable, borders, bb);
        }
        else
            converter = new Converter(table.getFoldButtonStatus()== Fold.ACTIVE,table.getDealerPosition(),table.getFirstCard().toString(),table.getSecondCard().toString(),totalChips,chipsAtTable, borders);
        return converter.convertData();
    }

    private boolean isPicked(FileHolder fileHolder){
        if(fileHolder.getFileDir() == null)return false;
        if(fileHolder.getFileDir().equals(""))return false;
        return true;
    }
}
