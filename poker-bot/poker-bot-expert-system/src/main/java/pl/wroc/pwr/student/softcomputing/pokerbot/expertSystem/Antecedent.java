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

    public void addFact(Fact fact) {
        andFacts.add(fact);
    }

    public void addFact(String factName) {
        andFacts.add(new Fact(factName));
    }

    public Fact getFactByIndex(int index) {
        return andFacts.get(index);
    }

    public Fact getFactByName(String name) {
        for (Fact Fact : andFacts) {
            if (Fact.getName().equals(name))
                return Fact;
        }
        return null;
    }

    public void removeFactByIndex(int index) {
        andFacts.remove(index);
    }

    public void removeFactByName(String name) {
        for (Fact Fact : andFacts) {
            if (Fact.getName().equals(name))
                andFacts.remove(Fact);
        }
    }

    public List<Fact> getFactList() {
        return andFacts;
    }

    @Override
    public String toString() {
        if(andFacts.size()==0)return "";
        StringBuilder s = new StringBuilder();
        s.append(andFacts.get(0).getName());
        if(andFacts.size()>1){
            for(int i=1; i<andFacts.size(); i++){
                s.append(",");
                s.append(andFacts.get(i).getName());
            }
        }
        return s.toString();
    }

    public static Antecedent getFromString(String fromString) {
        Antecedent antecedent = new Antecedent();
        for(String s : fromString.split(",")){
            antecedent.getFactList().add(new Fact(s));
        }
        return antecedent;
    }
}
