package pl.wroc.pwr.student.ui.listeners;


import pl.wroc.pwr.student.ui.component.ImagePanel;
import pl.wroc.pwr.student.ui.threads.RecognizeActive;
import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by RaV on 04.01.14.
 */
public class RecognizeActiveActionListener implements ActionListener {
    private JTextArea output;
    private JButton me;
    private Thread recognitionThread;
    private ImagePanel imagePanel;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;
    private final FileHolder ruleGroupsFile;
    private final FileHolder knowledgeSchemeFile;
    private final JLabel shortOutputLabel;

    public RecognizeActiveActionListener(JTextArea output, ImagePanel imagePanel, JButton me, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource, FileHolder ruleGroupsFile, FileHolder knowledgeSchemeFile, JLabel shortOutputLabel) {
        this.output = output;
        this.me = me;
        this.imagePanel = imagePanel;
        this.figuresDatasource = figuresDatasource;
        this.suitsDatasource = suitsDatasource;
        this.dealerDatasource = dealerDatasource;
        this.ruleGroupsFile = ruleGroupsFile;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
        this.shortOutputLabel = shortOutputLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isInputCorrect())return;
        if(me.getText().equals("Recognize active (start)")){
            me.setText("Recognize active (stop)");
            recognitionThread = new Thread(new RecognizeActive(imagePanel, output, figuresDatasource, suitsDatasource, dealerDatasource, ruleGroupsFile, knowledgeSchemeFile, shortOutputLabel), "Recognition Thread");
            recognitionThread.start();
           output.append("Recognition started\n");
        }
        else{
            me.setText("Recognize active (start)");
            recognitionThread.interrupt();
            output.append("Recognition stopped\n");
        }

    }

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
    private boolean isInputCorrect() {
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

    private boolean isPicked(FileHolder fileHolder){
        if(fileHolder.getFileDir() == null)return false;
        if(fileHolder.getFileDir().equals(""))return false;
        return true;
    }
}
