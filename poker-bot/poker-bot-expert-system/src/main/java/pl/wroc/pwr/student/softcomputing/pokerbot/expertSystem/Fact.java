package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

/**
 * Created by RaV on 21.01.14.
 */
public class Fact implements Consequent {
    private static final String FACT_PREFIX = "Fact: ";
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

        if (!name.toLowerCase().equals(fact.name.toLowerCase())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return FACT_PREFIX+name;
    }

    public static Fact getFromString(String fromString) {
        if(fromString.startsWith(FACT_PREFIX))
        {
            return new Fact(fromString.substring(FACT_PREFIX.length()));
        }
        throw new gettingFromStringException("Exception occured while trying to get fact from string. Probably wrong string was provided: \""+fromString+"\".");
    }

    public static class gettingFromStringException extends RuntimeException{
        public gettingFromStringException(String message) {
            super(message);
        }
    }

}
