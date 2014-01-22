package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.01.14.
 */
public class Antecedent {
    private List<Fact> andFacts;

    public Antecedent() {
        andFacts = new ArrayList<Fact>();
    }

    public Antecedent(List<Fact> andFacts) {
        this.andFacts = andFacts;
    }

    public void addFact(Fact Fact){
        andFacts.add(Fact);
    }

    public Fact getFactByIndex(int index){
        return andFacts.get(index);
    }

    public Fact getFactByName(String name){
        for(Fact Fact : andFacts){
            if(Fact.getName().equals(name))
                return Fact;
        }
        return null;
    }

    public void removeFactByIndex(int index){
        andFacts.remove(index);
    }

    public void removeFactByName(String name){
        for(Fact Fact : andFacts){
            if(Fact.getName().equals(name))
                andFacts.remove(Fact);
        }
    }

    public List<Fact> getFactList() {
        return andFacts;
    }

    @Override
    public String toString() {
        return "Antecedent{"+andFacts +'}';
    }
}
