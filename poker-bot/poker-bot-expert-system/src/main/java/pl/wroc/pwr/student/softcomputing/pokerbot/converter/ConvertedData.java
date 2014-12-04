package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

/**
 * Created by RaV on 15.01.14.
 */
public class ConvertedData {
    private int position;
    private int firstRaisePosition;
    private int playerStackInBb;
    private int effectiveStackInBb; //Min(myStack,Max(opponentsStacs));
    private String higherFigure;
    private String lowerFigure;
    private boolean cardsSuited;
    private Border border;
    private int numberOfPlayers;
    private int numberOfRaisers;
    private int numberOfLimpers;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getFirstRaisePosition() {
        return firstRaisePosition;
    }

    public void setFirstRaisePosition(int firstRaisePosition) {
        this.firstRaisePosition = firstRaisePosition;
    }

    public int getPlayerStackInBb() {
        return playerStackInBb;
    }

    public void setPlayerStackInBb(int playerStackInBb) {
        this.playerStackInBb = playerStackInBb;
    }

    public int getEffectiveStackInBb() {
        return effectiveStackInBb;
    }

    public void setEffectiveStackInBb(int effectiveStackInBb) {
        this.effectiveStackInBb = effectiveStackInBb;
    }

    public String getHigherFigure() {
        return higherFigure;
    }

    public void setHigherFigure(String higherFigure) {
        this.higherFigure = higherFigure;
    }

    public String getLowerFigure() {
        return lowerFigure;
    }

    public void setLowerFigure(String lowerFigure) {
        this.lowerFigure = lowerFigure;
    }

    public boolean areCardsSuited() {
        return cardsSuited;
    }

    public void setCardsSuited(boolean cardsSuited) {
        this.cardsSuited = cardsSuited;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfRaisers() {
        return numberOfRaisers;
    }

    public void setNumberOfRaisers(int numberOfRaisers) {
        this.numberOfRaisers = numberOfRaisers;
    }

    public int getNumberOfLimpers() {
        return numberOfLimpers;
    }

    public void setNumberOfLimpers(int numberOfLimpers) {
        this.numberOfLimpers = numberOfLimpers;
    }

    @Override
    public String toString() {
        return "ConvertedData:" +
                "\n" +
                "-position=" + position +
                "\n" +
                "-firstRaisePosition=" + firstRaisePosition +
                "\n" +
                "-playerStackInBb=" + playerStackInBb +
                "\n" +
                "-effectiveStackInBb=" + effectiveStackInBb +
                "\n" +
                "-higherFigure='" + higherFigure + '\'' +
                "\n" +
                "-lowerFigure='" + lowerFigure + '\'' +
                "\n" +
                "-areCardsSuited=" + cardsSuited +
                "\n" +
                "-border=" + border +
                "\n" +
                "-numberOfPlayers=" + numberOfPlayers +
                "\n" +
                "-numberOfRaisers=" + numberOfRaisers +
                "\n" +
                "-numberOfLimpers=" + numberOfLimpers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConvertedData that = (ConvertedData) o;

        if (cardsSuited != that.cardsSuited) return false;
        if (effectiveStackInBb != that.effectiveStackInBb) return false;
        if (firstRaisePosition != that.firstRaisePosition) return false;
        if (numberOfLimpers != that.numberOfLimpers) return false;
        if (numberOfPlayers != that.numberOfPlayers) return false;
        if (numberOfRaisers != that.numberOfRaisers) return false;
        if (playerStackInBb != that.playerStackInBb) return false;
        if (position != that.position) return false;
        if (border != that.border) return false;
        if (higherFigure != null ? !higherFigure.equals(that.higherFigure) : that.higherFigure != null) return false;
        if (lowerFigure != null ? !lowerFigure.equals(that.lowerFigure) : that.lowerFigure != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + firstRaisePosition;
        result = 31 * result + playerStackInBb;
        result = 31 * result + effectiveStackInBb;
        result = 31 * result + (higherFigure != null ? higherFigure.hashCode() : 0);
        result = 31 * result + (lowerFigure != null ? lowerFigure.hashCode() : 0);
        result = 31 * result + (cardsSuited ? 1 : 0);
        result = 31 * result + (border != null ? border.hashCode() : 0);
        result = 31 * result + numberOfPlayers;
        result = 31 * result + numberOfRaisers;
        result = 31 * result + numberOfLimpers;
        return result;
    }
}
