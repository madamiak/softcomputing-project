package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 06.04.14.
 */
public class RuleGroup {
    private static final String NAME_PREFIX = "Group: ";
    private List<Rule> rules;
    private String name;

    public RuleGroup(String name, List<Rule> rules) {
        this.rules = rules;
        this.name = name;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleGroup(List<String> ruleGroupText) {
        name = ruleGroupText.get(0).substring(NAME_PREFIX.length());
        rules = new ArrayList<Rule>();
        for (int i = 1; i < ruleGroupText.size(); i++){
            rules.add(Rule.getFromString(ruleGroupText.get(i)));
        }
    }

    public List<String> toStringArray(){
        List<String> list = new ArrayList<String>();
        list.add(NAME_PREFIX+name);
        for(Rule r : rules){
            list.add(r.toString());
        }
        return list;
    }
}
