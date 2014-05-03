package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

/**
 * Created by RaV on 21.01.14.
 */
public class Rule {
    private Consequent consequent;
    private Antecedent antecedent;

    public Rule(Consequent consequent, Antecedent antecedent) {
        this.consequent = consequent;
        this.antecedent = antecedent;
    }

    public Consequent getConsequent() {
        return consequent;
    }

    public Antecedent getAntecedent() {
        return antecedent;
    }


    public static Rule getFromString(String fromString) {
        Fact f = Fact.getFromString(fromString.split(";")[0]);
        Antecedent a = Antecedent.getFromString(fromString.split(";")[1]);
        return new Rule(f,a);
    }

    @Override
    public String toString() {
        return consequent + ";" + antecedent;
    }
}
