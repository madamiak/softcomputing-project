package pl.wroc.pwr.student.softcomputing.pokerbot.utils;

/**
 * Created by RaV on 14.09.14.
 */
public class ChainingData {
    private boolean positive;
    private int provenCount;
    private int unprovenCount;

    public ChainingData() {
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public int getProvenCount() {
        return provenCount;
    }

    public void setProvenCount(int provenCount) {
        this.provenCount = provenCount;
    }

    public int getUnprovenCount() {
        return unprovenCount;
    }

    public void setUnprovenCount(int unprovenCount) {
        this.unprovenCount = unprovenCount;
    }
}
