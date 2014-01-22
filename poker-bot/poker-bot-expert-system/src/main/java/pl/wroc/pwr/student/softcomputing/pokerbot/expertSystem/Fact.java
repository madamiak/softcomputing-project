package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

/**
 * Created by RaV on 21.01.14.
 */
public class Fact implements Consequent {
    private String name;

    public Fact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fact fact = (Fact) o;

        if (!name.equals(fact.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Fact: "+name;
    }
}
