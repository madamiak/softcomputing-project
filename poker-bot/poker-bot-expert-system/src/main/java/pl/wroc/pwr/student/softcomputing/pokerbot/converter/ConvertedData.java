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
}
