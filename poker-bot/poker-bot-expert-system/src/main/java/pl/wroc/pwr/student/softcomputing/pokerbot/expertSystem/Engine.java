package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.01.14.
 */
public class Engine {
    private List<Rule> rules;
    private KnowledgeBase knowledgeBase;
    private String outPut;

    public Engine() {
        rules = new ArrayList<Rule>();
        knowledgeBase = new KnowledgeBase();
    }

    public Engine(List<Rule> rules, KnowledgeBase knowledgeBase) {
        this.rules = rules;
        this.knowledgeBase = knowledgeBase;
    }

    public Engine(List<Rule> rules) {
        this.rules = rules;
        knowledgeBase = new KnowledgeBase();
    }

    public Engine(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
        rules = new ArrayList<Rule>();
    }

    public boolean performBackwardChaining(Fact hypothesis){
        outPut=new String("Performing backward chaining for hypothesis: "+hypothesis.getName()+"\n");
        //System.out.println("Performing backward chaining for hypothesis: "+hypothesis.getName());
        if(backwardChaining(hypothesis,1)){
            //System.out.println("Hypothesis proven true.");
            appendToOutputln("Hypothesis proven true.");
            return true;
        }else{
            //System.out.println("Hypothesis couldn't be proven true.");
            appendToOutputln("Hypothesis couldn't be proven true.");
            return false;
        }
    }
    private boolean backwardChaining(Fact hypothesis, int depth){
        for(int i=0; i< knowledgeBase.size(); i++){
            if(knowledgeBase.getFactByIndex(i).equals(hypothesis))
                return true;
        }

        for(Rule rule : rules){
            if(rule.getConsequent().equals(hypothesis)){
                boolean isConditionTrue=true;
                for(Fact subHypothesis : rule.getAntecedent().getFactList()){
                    if(!backwardChaining(subHypothesis, depth + 1)){
                        isConditionTrue = false;
                    }
                }
                if(isConditionTrue){
                    for(int i=0;i<depth;i++){
                        System.out.print("-");
                        appendToOutput("-");
                    }
                    //System.out.print(hypothesis+" proven true by proving "+rule.getAntecedent()+".\n");
                    appendToOutputln(hypothesis+" proven true by proving "+rule.getAntecedent());
                    return true;
                }
            }
        }

        return false;
    }

    public void addRule(Rule rule){
        rules.add(rule);
    }

    public void removeRule(Consequent consequent){
        rules.remove(consequent);
    }

    public void addToKnowledgeBase(Fact fact){
        knowledgeBase.addFact(fact);
    }

    public void removeFromKnowledgeBase(Fact fact){
        knowledgeBase.removeFact(fact);
    }

    private void appendToOutputln(String text){
        outPut=outPut+text+"\n";
    }

    private void appendToOutput(String text){
        outPut=outPut+text;
    }

    public String getOutPut(){
        return outPut;
    }
}
