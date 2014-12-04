package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.*;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Fold;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
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
public class TestAllActionListener implements ActionListener {
    private List<File> imageFilesList;
    private JTextArea output;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;
    private final FileHolder ruleGroupsFile;
    private final FileHolder knowledgeSchemeFile;

    public TestAllActionListener(FileHolder ruleGroupsFile, FileHolder knowledgeSchemeFile, FileHolder dealerDatasource, FileHolder suitsDatasource, FileHolder figuresDatasource, JTextArea output, List<File> imageFilesList) {
        this.ruleGroupsFile = ruleGroupsFile;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
        this.dealerDatasource = dealerDatasource;
        this.suitsDatasource = suitsDatasource;
        this.figuresDatasource = figuresDatasource;
        this.output = output;
        this.imageFilesList = imageFilesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isInputCorrect()) return;

        List<RuleGroup> ruleGroups = null;
        List<KnowledgeCommandData> knowledgeScheme = null;

        try {
            ruleGroups = ExpertAPI.getRuleGroupsFromFile(ruleGroupsFile.getFileDir());
        } catch (IOException e1) {
            displayMessage("Error occurred while reading rule groups from file. \n" + e1);
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

        int count = 0;
        int correctRaise = 0;
        int correctFold = 0;
        int incorrectRaise = 0;
        int incorrectFold = 0;

        long millis = System.currentTimeMillis();

        StringBuilder raiseErrorLog = new StringBuilder();
        StringBuilder foldErrorLog = new StringBuilder();
        int counter =0;
        for (File file : imageFilesList) {
            counter++;
            System.out.println(String.format("Recognizing %d/%d",counter,imageFilesList.size()));
            output.setText(String.format("Recognizing %d/%d",counter,imageFilesList.size()));
            Table table = RecognizingDelegate.recognize(file, figuresDatasource, suitsDatasource, dealerDatasource);
            ConvertedData convertedData = getConvertedData(table);
            KnowledgeBase knowledgeBase = knowledgeParser.createKnowledgeBase(convertedData);
            Engine engine = ExpertAPI.getEngine(ruleGroups, knowledgeBase);
            count++;
            if (engine.performBackwardChaining(new Fact("Raise")).isPositive()) {
                if (file.getName().toLowerCase().contains("raise")) correctRaise++;
                else{
                    incorrectFold++;
                    foldErrorLog.append("\n");
                    foldErrorLog.append(file.getName());
                }
            } else {
                if (file.getName().toLowerCase().contains("fold")) correctFold++;
                else{
                    incorrectRaise++;
                    raiseErrorLog.append("\n");
                    raiseErrorLog.append(file.getName());
                }
            }
        }
        output.setText(String.format("Correctly recognized: %d/%d (%d%%) in average of %dms per table.\n Number of rules is %d.", correctRaise+correctFold, count, (correctRaise+correctFold) * 100 / count, (System.currentTimeMillis() - millis) / count, getRulesCount(ruleGroups)));
        output.append(String.format("\nRaises recognized correctly: %d/%d (%d%%)",correctRaise,correctRaise+incorrectRaise,(correctRaise*100)/(correctRaise+incorrectRaise)));
        output.append(String.format("\nFolds recognized correctly: %d/%d (%d%%)", correctFold, correctFold + incorrectFold, (correctFold * 100) / (correctFold + incorrectFold)));
        output.append("\nRaises recognized wrong:");
        output.append(raiseErrorLog.toString());
        output.append("\nFolds recognized wrong:");
        output.append(foldErrorLog.toString());
    }

    private boolean isInputCorrect() {
        if (imageFilesList.isEmpty()) {
            displayMessage("No images selected.");
            return false;
        }
        if (!isPicked(figuresDatasource)) {
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if (!isPicked(suitsDatasource)) {
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if (!isPicked(dealerDatasource)) {
            displayMessage("No figures NNET file picked.");
            return false;
        }
        if (!isPicked(ruleGroupsFile)) {
            displayMessage("No rule groups file picked.");
            return false;
        }
        if (!isPicked(knowledgeSchemeFile)) {
            displayMessage("No knowledge scheme file picked.");
            return false;
        }
        return true;
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private ConvertedData getConvertedData(Table table) {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> borders = new ArrayList<String>();
        borders.add(null);
        for (int i = 0; i < 6; i++) {
            totalChips.add(table.getTotalChipsOf(i));
            chipsAtTable.add(table.getChipsOnTableOf(i));
            if (i > 0)
                borders.add(table.getBorderOf(i).toString());
        }

        Converter converter = new Converter(table.getFoldButtonStatus() == Fold.ACTIVE, table.getDealerPosition(), table.getFirstCard().toString(), table.getSecondCard().toString(), totalChips, chipsAtTable, borders);
        return converter.convertData();
    }

    private boolean isPicked(FileHolder fileHolder) {
        if (fileHolder.getFileDir() == null) return false;
        if (fileHolder.getFileDir().equals("")) return false;
        return true;
    }

    private int getRulesCount(List<RuleGroup> ruleGroups) {
        int count = 0;
        for (RuleGroup ruleGroup : ruleGroups)
            count+=ruleGroup.getRules().size();
        return count;
    }
}
