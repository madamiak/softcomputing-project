package pl.wroc.pwr.student.ui.utils;

import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums;

/**
 * Created by RaV on 21.04.14.
 */
public enum CardFigure {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("T"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A"), HIGHER_CARD_FIGURE(Enums.StringParameter.HIGHER_CARD_FIGURE.toString()), LOWER_CARD_FIGURE(Enums.StringParameter.LOWER_CARD_FIGURE.toString());

    private String value;

    CardFigure(String value) {
        this.value = value;
    }

    public static CardFigure getFigure(String value) {
        for (CardFigure figure : CardFigure.values()) {
            if (figure.value.equals(value)) return figure;
        }
        throw new NoSuchFigureException("No figure found for value: " + value);
    }

    public static class NoSuchFigureException extends RuntimeException {
        public NoSuchFigureException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
