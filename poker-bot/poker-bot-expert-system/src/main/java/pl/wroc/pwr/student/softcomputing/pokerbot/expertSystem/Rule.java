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
}
