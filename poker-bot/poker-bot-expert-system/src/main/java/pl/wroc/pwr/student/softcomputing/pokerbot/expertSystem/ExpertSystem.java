package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 15.01.14.
 */
public class ExpertSystem {
    public static void main (String[] args){
    }

    public Engine getBasicStrategyEngine(ConvertedData convertedData){
        KnowledgeBase knowledgeBase = getBasicKnowledgeBaseFromConverdedData(convertedData);
        System.out.println("Knowledgebase: "+knowledgeBase);
        List<Rule> rules = getBasicStrategyRuleList();
        return new Engine(rules,knowledgeBase);
    }

    private KnowledgeBase getBasicKnowledgeBaseFromConverdedData(ConvertedData convertedData){
        List<Fact> facts = new ArrayList<Fact>();
        if(convertedData.getNumberOfLimpers()==0)facts.add(new Fact("No Limpers"));
        if(convertedData.getNumberOfLimpers()>0)facts.add(new Fact("One or more Limpers"));
        if(convertedData.getNumberOfRaisers()==0)facts.add(new Fact("No Raisers"));
        if(convertedData.getNumberOfRaisers()==1)facts.add(new Fact("One Raiser"));
        if(convertedData.getNumberOfRaisers()>1)facts.add(new Fact("Two or more Raiser"));
        facts.add(new Fact("Higher card "+convertedData.getHigherFigure()));
        facts.add(new Fact("Lower card "+convertedData.getLowerFigure()));
        facts.add(new Fact("Cards suited "+convertedData.areCardsSuited()));
        facts.add(new Fact("Position "+convertedData.getPosition()));
        if(convertedData.getEffectiveStackInBb()<6)facts.add(new Fact("Effective stack < 6"));
        if(convertedData.getEffectiveStackInBb()>5&&convertedData.getEffectiveStackInBb()<9)facts.add(new Fact("Effective stack 6 7 8"));
        if(convertedData.getEffectiveStackInBb()>8)facts.add(new Fact("Effective stack > 8"));
        if(convertedData.getHigherFigure().equals(convertedData.getLowerFigure()))facts.add(new Fact("Got pair"));


        return new KnowledgeBase(facts);
    }

    private List<Rule> getBasicStrategyRuleList(){


        List<Rule> rules = new ArrayList<Rule>();
        List<Fact> conditions;

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("No Limpers"));
        conditions.add(new Fact("No Raisers"));
        rules.add(new Rule(new Fact("First to act"),new Antecedent(conditions)));

        addBasicStrategyCardGroupARules(rules);
        addBasicStrategyCardGroupBRules(rules);
        addBasicStrategyCardGroupCRules(rules);
        addBasicStrategyCardGroupDRules(rules);
        addBasicStrategyCardGroupERules(rules);

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Effective stack > 8"));
        conditions.add(new Fact("Position 1"));
        conditions.add(new Fact("Card Group A"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 2"));
        conditions.add(new Fact("Effective stack > 8"));
        conditions.add(new Fact("Card Group B"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 3"));
        conditions.add(new Fact("Effective stack > 8"));
        conditions.add(new Fact("Card Group C"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 4"));
        conditions.add(new Fact("Effective stack > 8"));
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Position 1"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        conditions.add(new Fact("Card Group C"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 2"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        conditions.add(new Fact("Card Group C"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 3"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 4"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        conditions.add(new Fact("Card Group E"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 5"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 5"));
        conditions.add(new Fact("Effective stack > 8"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Position 1"));
        conditions.add(new Fact("Effective stack < 6"));
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 2"));
        conditions.add(new Fact("Effective stack < 6"));
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 3"));
        conditions.add(new Fact("Effective stack < 6"));
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 4"));
        conditions.add(new Fact("Effective stack < 6"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("First to act"));
        conditions.add(new Fact("Position 5"));
        conditions.add(new Fact("Effective stack < 6"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("One or more Limpers"));
        conditions.add(new Fact("No Raisers"));
        conditions.add(new Fact("Effective stack > 8"));
        conditions.add(new Fact("Card Group A"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("One or more Limpers"));
        conditions.add(new Fact("No Raisers"));
        conditions.add(new Fact("Effective stack 6 7 8"));
        conditions.add(new Fact("Card Group B"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("One or more Limpers"));
        conditions.add(new Fact("No Raisers"));
        conditions.add(new Fact("Effective stack < 6"));
        conditions.add(new Fact("Card Group C"));
        rules.add(new Rule(new Fact("Raise"),new Antecedent(conditions)));


        return rules;
    }

    private void addBasicStrategyCardGroupERules(List<Rule> rules) {
        List<Fact> conditions;
        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Card Group D"));
        rules.add(new Rule(new Fact("Card Group E"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        rules.add(new Rule(new Fact("Card Group E"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 4"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 8"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 6"));
        conditions.add(new Fact("Lower card 4"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 5"));
        conditions.add(new Fact("Lower card 4"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group E"), new Antecedent(conditions)));
    }

    private void addBasicStrategyCardGroupDRules(List<Rule> rules) {
        List<Fact> conditions;
        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Card Group C"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 8"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 7"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 7"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 6"));
        conditions.add(new Fact("Lower card 5"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group D"),new Antecedent(conditions)));
    }

    private void addBasicStrategyCardGroupCRules(List<Rule> rules) {
        List<Fact> conditions;
        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Card Group B"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 6"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 8"));
        conditions.add(new Fact("Lower card 7"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group C"),new Antecedent(conditions)));
    }

    private void addBasicStrategyCardGroupBRules(List<Rule> rules) {
        List<Fact> conditions;
        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Card Group A"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card 9"));
        conditions.add(new Fact("Lower card 8"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group B"),new Antecedent(conditions)));
    }

    private void addBasicStrategyCardGroupARules(List<Rule> rules) {
        List<Fact> conditions;

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card J"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card Q"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Lower card K"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card A"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Got pair"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card J"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card Q"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card J"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card K"));
        conditions.add(new Fact("Lower card Q"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card J"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card Q"));
        conditions.add(new Fact("Lower card J"));
        conditions.add(new Fact("Cards suited false"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card J"));
        conditions.add(new Fact("Lower card T"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));

        conditions = new ArrayList<Fact>();
        conditions.add(new Fact("Higher card T"));
        conditions.add(new Fact("Lower card 9"));
        conditions.add(new Fact("Cards suited true"));
        rules.add(new Rule(new Fact("Card Group A"),new Antecedent(conditions)));
    }
}
