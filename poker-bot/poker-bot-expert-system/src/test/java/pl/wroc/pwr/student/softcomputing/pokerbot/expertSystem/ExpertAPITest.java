package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Border;

import java.util.ArrayList;
import java.util.List;

import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.BooleanParameter.SUITED;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.Comparator.EQUAL;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.Comparator.FALSE;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.Comparator.GREATER;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.IntParameter.EFFECTIVE_STACK_IN_BB;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.ParameterType.*;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.StringParameter.HIGHER_CARD_FIGURE;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.StringParameter.LOWER_CARD_FIGURE;

/**
 * Created by RaV on 06.04.14.
 */
public class ExpertAPITest {

    @Test
    public void testStoreRuleGroupsInFile() throws Exception {
        List<RuleGroup> ruleGroups = new ArrayList<RuleGroup>();

        List<Rule> ruleList = new ArrayList<Rule>();
        Antecedent antecedent = new Antecedent();
        antecedent.addFact("No Limpers");
        antecedent.addFact("No Raisers");
        ruleList.add(new Rule(new Fact("First to act"), antecedent));
        antecedent = new Antecedent();
        antecedent.addFact("First to act");
        ruleList.add(new Rule(new Fact("Under the gun"), antecedent));
        RuleGroup ruleGroup = new RuleGroup("Position rule group", ruleList);
        ruleGroups.add(ruleGroup);

        ruleList = new ArrayList<Rule>();
        antecedent = new Antecedent();
        antecedent.addFact("Suited");
        antecedent.addFact("Ace");
        antecedent.addFact("King");
        ruleList.add(new Rule(new Fact("Top Hand"), antecedent));
        antecedent = new Antecedent();
        antecedent.addFact("2");
        antecedent.addFact("7");
        ruleList.add(new Rule(new Fact("Worst Hand"), antecedent));
        ruleGroup = new RuleGroup("Hands rule group", ruleList);
        ruleGroups.add(ruleGroup);

        ExpertAPI.storeRuleGroupsInFile("testRuleGroupsFile.txt", ruleGroups);
    }

    @Test
    public void testGetRuleGroupsFromFile() throws Exception {
        List<RuleGroup> ruleGroups = ExpertAPI.getRuleGroupsFromFile("testRuleGroupsFile.txt");
        for (RuleGroup ruleGroup : ruleGroups) {
            for (String s : ruleGroup.toStringArray()) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void testStoreKnowledgeSchemeInFile() throws Exception {
        List<KnowledgeCommandData> knowledgeScheme = new ArrayList<KnowledgeCommandData>();
        knowledgeScheme.add(KnowledgeParser.generateCommandRecord("Random border", BORDER, Border.Random, EQUAL));
        knowledgeScheme.add(KnowledgeParser.generateCommandRecord("Not suited cards", BOOLEAN, SUITED, FALSE));
        knowledgeScheme.add(KnowledgeParser.generateCommandRecord("Lower figure = 8", STRING, LOWER_CARD_FIGURE, EQUAL, "8"));
        knowledgeScheme.add(KnowledgeParser.generateCommandRecord("Effective stack > 5", INT, EFFECTIVE_STACK_IN_BB, GREATER, "5"));
        knowledgeScheme.add(KnowledgeParser.generateCommandRecord("Pair", STRING, HIGHER_CARD_FIGURE, EQUAL, LOWER_CARD_FIGURE));
        ExpertAPI.storeKnowledgeSchemeInFile("testKnowledgeScheme.txt",knowledgeScheme);
    }

    @Test
    public void testGetKnowledgeSchemeFromFile() throws Exception {
        List<KnowledgeCommandData> knowledgeScheme = ExpertAPI.getKnowledgeSchemeFromFile("testKnowledgeScheme.txt");
        for (KnowledgeCommandData commandData : knowledgeScheme) {
            System.out.println(commandData);
        }
    }
}
