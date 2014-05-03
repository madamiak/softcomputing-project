package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 06.04.14.
 */

public class RuleGroupTest {
    @Test
    public void test1(){
        List<Rule> ruleList = new ArrayList<Rule>();
        Antecedent antecedent = new Antecedent();
        antecedent.addFact("No Limpers");
        antecedent.addFact("No Raisers");
        ruleList.add(new Rule(new Fact("First to act"),antecedent));
        antecedent = new Antecedent();
        antecedent.addFact("First to act");
        ruleList.add(new Rule(new Fact("Under the gun"),antecedent));
        RuleGroup ruleGroup = new RuleGroup("Test rule group",ruleList);

        for(String s : ruleGroup.toStringArray()){
            System.out.println(s);
        }
    }
    @Test
    public void test2(){
        List<Rule> ruleList = new ArrayList<Rule>();
        Antecedent antecedent = new Antecedent();
        antecedent.addFact("No Limpers");
        antecedent.addFact("No Raisers");
        ruleList.add(new Rule(new Fact("First to act"),antecedent));
        antecedent = new Antecedent();
        antecedent.addFact("First to act");
        ruleList.add(new Rule(new Fact("Under the gun"),antecedent));
        RuleGroup ruleGroup = new RuleGroup("Test rule group",ruleList);

        RuleGroup ruleGroup2 = new RuleGroup(ruleGroup.toStringArray());

        for(String s : ruleGroup2.toStringArray()){
            System.out.println(s);
        }
    }
}
