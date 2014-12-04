package pl.wroc.pwr.student.ui.listeners;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.*;
import pl.wroc.pwr.student.ui.utils.FileHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.04.14.
 */
public class VerifyActionListener implements ActionListener {
    List<RuleGroup> ruleGroups;
    FileHolder knowledgeSchemeFile;

    public VerifyActionListener(List<RuleGroup> ruleGroups, FileHolder knowledgeSchemeFile) {
        this.ruleGroups = ruleGroups;
        this.knowledgeSchemeFile = knowledgeSchemeFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<KnowledgeCommandData> knowledgeBaseScheme = new ArrayList<KnowledgeCommandData>();
        try {
            knowledgeBaseScheme.clear();
            knowledgeBaseScheme.addAll(ExpertAPI.getKnowledgeSchemeFromFile(knowledgeSchemeFile.getFileDir()));
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, String.format("Couldn't load knowledge base scheme from file: \n %s. \n File is probably corrupted. \n Error message: %s", knowledgeSchemeFile.getFileDir(), e1));
            return;
        }

        List<String> knowledgeBasePossible = new ArrayList<String>();

        for(RuleGroup ruleGroup : ruleGroups){
            for(Rule rule : ruleGroup.getRules()){
                knowledgeBasePossible.add(((Fact)rule.getConsequent()).getName());
            }
        }

        for(KnowledgeCommandData knowledgeCommandData : knowledgeBaseScheme){
            knowledgeBasePossible.add(knowledgeCommandData.getName());
        }

        StringBuilder report = new StringBuilder("Following facts could not be found in knowledge base based on scheme provided, or as rules consequences:");
        boolean verified = true;

        for(RuleGroup ruleGroup : ruleGroups){
            for(Rule rule : ruleGroup.getRules()){
                for(Fact fact : rule.getAntecedent().getFactList()){
                    boolean found = false;
                    for(String knowledge : knowledgeBasePossible) {
                        if (fact.getName().toLowerCase().equals(knowledge.toLowerCase())) {
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        report.append(String.format("\n %s -> %s -> %s", ruleGroup.getName(), ((Fact)rule.getConsequent()).getName(), fact.getName()));
                        verified = false;
                    }
                }
            }
        }
        if(verified)
            JOptionPane.showMessageDialog(null, "Rules were successfully verified.");
        else
            JOptionPane.showMessageDialog(null, report);
    }
}
