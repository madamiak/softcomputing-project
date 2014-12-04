package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 06.04.14.
 */
public class ExpertAPI {

    private ExpertAPI() {
    }

    public static Engine getEngine(List<RuleGroup> ruleGroups, KnowledgeBase knowledgeBase) {
        List<Rule> rules = new ArrayList<Rule>();
        for(RuleGroup ruleGroup : ruleGroups){
            rules.addAll(ruleGroup.getRules());
        }
        return new Engine(rules, knowledgeBase);
    }

    public static List<RuleGroup> getRuleGroupsFromFile(String fileName) throws IOException {
        List<RuleGroup> ruleGroups = new ArrayList<RuleGroup>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        List<String> ruleGroupStringList = null;
        String line;
        boolean starting = true;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Group: ")) {
                if (!starting) ruleGroups.add(new RuleGroup(ruleGroupStringList));
                ruleGroupStringList = new ArrayList<String>();
                ruleGroupStringList.add(line);
            } else ruleGroupStringList.add(line);
            starting = false;
        }
        ruleGroups.add(new RuleGroup(ruleGroupStringList));
        br.close();
        return ruleGroups;
    }

    public static void storeRuleGroupsInFile(String fileName, List<RuleGroup> ruleGroups) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for (RuleGroup ruleGroup : ruleGroups) {
            for (String s : ruleGroup.toStringArray()) {
                writer.println(s);
            }
        }
        writer.close();
    }

    public static List<KnowledgeCommandData> getKnowledgeSchemeFromFile(String fileName) throws IOException {
        List<KnowledgeCommandData> knowledgeScheme = new ArrayList<KnowledgeCommandData>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            knowledgeScheme.add(KnowledgeParser.parseCommandRecord(line));
        }
        br.close();
        return knowledgeScheme;
    }

    public static void storeKnowledgeSchemeInFile(String fileName, List<KnowledgeCommandData> knowledgeScheme) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for (KnowledgeCommandData commandData : knowledgeScheme) {
            writer.println(commandData);
        }
        writer.close();
    }
}
