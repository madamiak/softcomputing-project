package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 21.01.14.
 */
public class KnowledgeBase {
    private List<Fact> facts;

    public KnowledgeBase() {
        facts = new ArrayList<Fact>();
    }

    public KnowledgeBase(List<Fact> facts) {
        this.facts = facts;
    }

    public void addFact(Fact fact){
        facts.add(fact);
    }

    public Fact getFactByIndex(int index){
        return facts.get(index);
    }

    public Fact getFactByName(String name){
        for(Fact fact : facts){
            if(fact.getName().equals(name))
                return fact;
        }
        return null;
    }

    public void removeFactByIndex(int index){
        facts.remove(index);
    }

    public void removeFactByName(String name){
        for(Fact fact : facts){
            if(fact.getName().equals(name))
                facts.remove(fact);
        }
    }

    public void removeFact(Fact fact){
        facts.remove(fact);
    }

    public int size(){
        return facts.size();
    }

    public boolean hasFact(Fact fact){
        for(Fact f : facts){
            if(f==fact)
                return true;
        }
        return false;
    }

    public boolean hasFact(String factname){
        for(Fact f : facts){
            if(f.getName().equals(factname))
                return true;
        }
        return false;
    }

    public int factCount(){
        return facts.size();
    }

    @Override
    public String toString() {
        return "KnowledgeBase{" + facts +
                '}';
    }
}
