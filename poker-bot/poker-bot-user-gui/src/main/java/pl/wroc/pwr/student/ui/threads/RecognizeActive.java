package pl.wroc.pwr.student.ui.threads;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.*;
import pl.wroc.pwr.student.softcomputing.pokerbot.utils.ChainingData;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Fold;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.ui.component.ImagePanel;
import pl.wroc.pwr.student.ui.decisions.DecisionMaker;
import pl.wroc.pwr.student.ui.utils.FileHolder;
import pl.wroc.pwr.student.ui.utils.LabelUtil;
import pl.wroc.pwr.student.ui.utils.PokerTableUtil;
import pl.wroc.pwr.student.ui.utils.RecognizingDelegate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by RaV on 07.09.14.
 */
public class RecognizeActive implements Runnable{
    private static final long REFRESH_TIME = 500L;
    private ImagePanel imagePanel;
    private JTextArea output;
    private String currentDir;
    private String currentFile;
    private int tableCounter =0;
    private int recognizedCounter = 1;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;
    private final FileHolder ruleGroupsFile;
    private final FileHolder knowledgeSchemeFile;
    private final JLabel shortOutputLabel;

    public RecognizeActive(ImagePanel imagePanel, JTextArea output, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource, FileHolder ruleGroupsFile, FileHolder knowledgeSchemeFile, JLabel shortOutputLabel) {
        this.imagePanel = imagePanel;
        this.output = output;
        this.figuresDatasource = figuresDatasource;
        this.suitsDatasource = suitsDatasource;
        this.dealerDatasource = dealerDatasource;
        this.ruleGroupsFile = ruleGroupsFile;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
        this.shortOutputLabel = shortOutputLabel;
    }

    @Override
    public void run() {
        boolean saveFlag = true;
        initializeDir();
        while(true){
            BufferedImage img = PokerTableUtil.capture();
            if(img!=null){
                imagePanel.preview(img);
                saveImage(img);
                try{
                    String answer = recognize();
                    System.out.println(currentFile);
                    System.out.println(answer);
                    if(answer.equals("Inactive")){
                        shortOutputLabel.setText(LabelUtil.wrapOrange("Buttons inactive"));
                        new File(currentFile).delete();
                    }
                    else if(answer.equals("Not Pre-Flop")){
                        shortOutputLabel.setText(LabelUtil.wrapOrange("Not Pre-Flop"));
                        new File(currentFile).delete();
                        DecisionMaker.justFold();
                    }
                    else if(!answer.equals("No changes")){
                        File newNameFile = new File(currentDir+"\\"+answer+recognizedCounter+"BB"+PokerTableUtil.getLastCaptureBb()+".png");
                        System.out.println(currentFile);
                        System.out.println(newNameFile);
                        new File(currentFile).renameTo(newNameFile);
                        saveFlag=true;
                        recognizedCounter++;
                    }
                }catch(Exception e){
                    System.out.println("Could not recognize table.");
                    shortOutputLabel.setText(LabelUtil.wrapOrange("Not recognized"));
                    DecisionMaker.justFold();
                    if(!saveFlag)new File(currentFile).delete();
                    saveFlag=false;
                }
            }
            else output.append("No poker tables opened\n");
            try {
                sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                output.append("Interrupted\n");
                break;
            }
        }
    }

    private String recognize() {
        Table table= RecognizingDelegate.recognize(new File(currentFile), figuresDatasource, suitsDatasource, dealerDatasource);
        System.out.println(table.getFoldButtonStatus());
        if(table.getFoldButtonStatus()==Fold.INACTIVE){
            return "Inactive";
        }
        if(table.getGamePhase()!=0){
            return "Not Pre-Flop";
        }
        ConvertedData convertedData = getConvertedData(table);
        List<RuleGroup> ruleGroups = null;
        List<KnowledgeCommandData> knowledgeScheme = null;

        try {
            ruleGroups = ExpertAPI.getRuleGroupsFromFile(ruleGroupsFile.getFileDir());
        } catch (IOException e1) {
            System.out.println("Error occurred while reading rule groups from file. \n"+e1);
            e1.printStackTrace();
            return "Error: "+e1;
        }
        try {
            knowledgeScheme = ExpertAPI.getKnowledgeSchemeFromFile(knowledgeSchemeFile.getFileDir());
        } catch (IOException e1) {
            System.out.println("Error occurred while reading knowledge scheme from file. \n" + e1);
            e1.printStackTrace();
            return "Error: "+e1;
        }

        KnowledgeParser knowledgeParser = new KnowledgeParser(knowledgeScheme);
        KnowledgeBase knowledgeBase = knowledgeParser.createKnowledgeBase(convertedData);
        Engine engine = ExpertAPI.getEngine(ruleGroups,knowledgeBase);

        boolean answerFlag;
        String answer="";
        ChainingData chainingData = engine.performBackwardChaining(new Fact("Raise"));
        if(chainingData.isPositive()){
            answerFlag = true;
            answer="Player should RAISE in this situation!";
            shortOutputLabel.setText(LabelUtil.wrapGreen("Raise"));
        }else{
            answerFlag=false;
            answer="Player should FOLD in this situation!";
            shortOutputLabel.setText(LabelUtil.wrapRed("Fold"));
        }

        output.setText(knowledgeBase.toString());
        output.append("\n\n");
        output.append(answer);
        output.append("\n");
        output.append(convertedData.toString());
        output.append("\n");
        output.append(table.smallReport());
        output.append("\n");
        output.append(engine.getOutPut());
        DecisionMaker.changed();
        DecisionMaker.makeDecision(chainingData);
        if(answerFlag)return "Raise";
        else return "Fold";
    }

    private void saveImage(BufferedImage img){
        tableCounter++;
        currentFile=currentDir+"\\Table_"+ tableCounter+"BB"+PokerTableUtil.getLastCaptureBb()+".png";
        File outputfile = new File(currentFile);
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            output.append("Cannot save image :(\n");
            e.printStackTrace();
        }
    }

    private void initializeDir() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        currentDir="Recognized\\"+dateFormat.format(date);
        File theDir = new File(currentDir+"\\1");

        // if the directory does not exist, create it
        int i=2;
        while (theDir.exists()) {
            currentDir="Recognized\\"+dateFormat.format(date);
            theDir = new File(currentDir+"\\"+i);
            i++;
        }
        boolean result = false;
        currentDir=theDir.getPath();
        theDir = new File("");

        try{
            for(String folder : currentDir.split("\\\\")){
                theDir = new File(theDir.getAbsolutePath()+"\\"+folder);
                System.out.println(theDir);
                theDir.mkdir();
            }
            result = true;
        } catch(SecurityException se){
            output.append("Failed to create: "+theDir);
        }
        if(result) {
            System.out.println("DIR created: "+theDir.getAbsolutePath());
        }
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

        Converter converter = new Converter(table.getFoldButtonStatus()== Fold.ACTIVE,table.getDealerPosition(),table.getFirstCard().toString(),table.getSecondCard().toString(),totalChips,chipsAtTable, borders, PokerTableUtil.getLastCaptureBb());
        return converter.convertData();
    }
}
