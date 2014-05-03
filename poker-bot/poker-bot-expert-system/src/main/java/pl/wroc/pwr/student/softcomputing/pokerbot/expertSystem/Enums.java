package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

/**
 * Created by RaV on 19.04.14.
 */
public class Enums {

    private Enums() {}

    public enum IntParameter {
        PLAYER_POSITION, FIRST_RAISE_POSITION, PLAYER_STACK_IN_BB, EFFECTIVE_STACK_IN_BB, NUMBER_OF_PLAYERS, NUMBER_OF_RAISERS, NUMBER_OF_LIMPERS;
        private static final String PLAYER_POSITION_STRING = "playerPosition";
        private static final String FIRST_RAISE_POSITION_STRING = "firstRaisePosition";
        private static final String PLAYER_STACK_IN_BB_STRING = "playerStackInBb";
        private static final String EFFECTIVE_STACK_IN_BB_STRING = "effectiveStackInBb";
        private static final String NUMBER_OF_PLAYERS_STRING = "numberOfPlayers";
        private static final String NUMBER_OF_RAISERS_STRING = "numberOfRaisers";
        private static final String NUMBER_OF_LIMPERS_STRING = "numberOfLimpers";

        public static IntParameter getParameter(String parameter) {
            if (parameter.equals(PLAYER_POSITION_STRING)) return PLAYER_POSITION;
            if (parameter.equals(FIRST_RAISE_POSITION_STRING)) return FIRST_RAISE_POSITION;
            if (parameter.equals(PLAYER_STACK_IN_BB_STRING)) return PLAYER_STACK_IN_BB;
            if (parameter.equals(EFFECTIVE_STACK_IN_BB_STRING)) return EFFECTIVE_STACK_IN_BB;
            if (parameter.equals(NUMBER_OF_PLAYERS_STRING)) return NUMBER_OF_PLAYERS;
            if (parameter.equals(NUMBER_OF_RAISERS_STRING)) return NUMBER_OF_RAISERS;
            if (parameter.equals(NUMBER_OF_LIMPERS_STRING)) return NUMBER_OF_LIMPERS;
            throw new NoSuchParameterException(parameter);
        }

        @Override
        public String toString() {
            switch (this) {
                case PLAYER_POSITION:
                    return PLAYER_POSITION_STRING;
                case FIRST_RAISE_POSITION:
                    return FIRST_RAISE_POSITION_STRING;
                case PLAYER_STACK_IN_BB:
                    return PLAYER_STACK_IN_BB_STRING;
                case EFFECTIVE_STACK_IN_BB:
                    return EFFECTIVE_STACK_IN_BB_STRING;
                case NUMBER_OF_PLAYERS:
                    return NUMBER_OF_PLAYERS_STRING;
                case NUMBER_OF_RAISERS:
                    return NUMBER_OF_RAISERS_STRING;
                case NUMBER_OF_LIMPERS:
                    return NUMBER_OF_LIMPERS_STRING;
            }
            throw new UnsupportedOperationException();
        }
    }

    public enum Comparator {
        EQUAL, GREATER, LESS, BETWEEN, NOT_EQUAL, TRUE, FALSE;
        private static final String EQUAL_STRING = "Equal";
        private static final String GREATER_STRING = "Greater than";
        private static final String LESS_STRING = "Less";
        private static final String BETWEEN_STRING = "Between";
        private static final String NOT_EQUAL_STRING = "Not Equal";
        private static final String TRUE_STRING = "True";
        private static final String FALSE_STRING = "False";

        public static Comparator getComparator(String comparator) {
            if (comparator.equals(EQUAL_STRING)) return EQUAL;
            if (comparator.equals(GREATER_STRING)) return GREATER;
            if (comparator.equals(LESS_STRING)) return LESS;
            if (comparator.equals(BETWEEN_STRING)) return BETWEEN;
            if (comparator.equals(NOT_EQUAL_STRING)) return NOT_EQUAL;
            if (comparator.equals(TRUE_STRING)) return TRUE;
            if (comparator.equals(FALSE_STRING)) return FALSE;
            throw new NoSuchComparatorException(comparator);
        }

        @Override
        public String toString() {
            switch (this) {
                case EQUAL:
                    return EQUAL_STRING;
                case GREATER:
                    return GREATER_STRING;
                case LESS:
                    return LESS_STRING;
                case BETWEEN:
                    return BETWEEN_STRING;
                case NOT_EQUAL:
                    return NOT_EQUAL_STRING;
                case TRUE:
                    return TRUE_STRING;
                case FALSE:
                    return FALSE_STRING;
            }
            throw new UnsupportedOperationException();
        }
    }

    public enum ParameterType {
        INT, STRING, BOOLEAN, BORDER;
        private static final String STRING_STRING = "Text";
        private static final String INT_STRING = "Numeric";
        private static final String BOOLEAN_STRING = "True/False";
        private static final String BORDER_STRING = "Border";

        public static ParameterType getType(String type) {
            if (type.equals(STRING_STRING)) return STRING;
            if (type.equals(INT_STRING)) return INT;
            if (type.equals(BOOLEAN_STRING)) return BOOLEAN;
            if (type.equals(BORDER_STRING)) return BORDER;
            throw new NoSuchTypeException(type);
        }

        @Override
        public String toString() {
            switch (this) {
                case INT:
                    return INT_STRING;
                case STRING:
                    return STRING_STRING;
                case BOOLEAN:
                    return BOOLEAN_STRING;
                case BORDER:
                    return BORDER_STRING;
            }
            throw new UnsupportedOperationException();
        }
    }

    public enum StringParameter {
        HIGHER_CARD_FIGURE, LOWER_CARD_FIGURE;

        private static final String HIGHER_CARD_FIGURE_STRING = "higherCardFigure";
        private static final String LOWER_CARD_FIGURE_STRING = "lowerCardFigure";

        public static StringParameter getParameter(String parameter) {
            if (parameter.equals(HIGHER_CARD_FIGURE_STRING)) return HIGHER_CARD_FIGURE;
            if (parameter.equals(LOWER_CARD_FIGURE_STRING)) return LOWER_CARD_FIGURE;
            throw new NoSuchParameterException(parameter);
        }

        @Override
        public String toString() {
            switch (this) {
                case HIGHER_CARD_FIGURE:
                    return HIGHER_CARD_FIGURE_STRING;
                case LOWER_CARD_FIGURE:
                    return LOWER_CARD_FIGURE_STRING;
            }
            throw new UnsupportedOperationException();
        }
    }

    public enum BooleanParameter {
        SUITED;

        private static final String SUITED_STRING = "suited";

        public static BooleanParameter getParameter(String parameter) {
            if (parameter.equals(SUITED_STRING)) return SUITED;
            throw new NoSuchParameterException(parameter);
        }

        @Override
        public String toString() {
            switch (this) {
                case SUITED:
                    return SUITED_STRING;
            }
            throw new UnsupportedOperationException();
        }
    }

    public static class NoSuchParameterException extends RuntimeException {
        public NoSuchParameterException(String message) {
            super(message);
        }
    }

    public static class NoSuchComparatorException extends RuntimeException {
        public NoSuchComparatorException(String message) {
            super(message);
        }
    }

    public static class NoSuchTypeException extends RuntimeException {
        public NoSuchTypeException(String message) {
            super(message);
        }
    }
}
