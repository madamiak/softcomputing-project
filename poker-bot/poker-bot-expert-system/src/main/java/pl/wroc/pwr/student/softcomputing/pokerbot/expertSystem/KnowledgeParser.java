package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Border;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;

import java.util.List;

import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.*;

/**
 * Created by RaV on 08.04.14.
 */
public class KnowledgeParser {
    private static final int RECORD_NAME_COMMAND_POSITION = 0;
    private static final int PARAMETER_TYPE_COMMAND_POSITION = 1;
    private static final int PARAMETER_NAME_COMMAND_POSITION = 2;
    private static final int COMPARATOR_NAME_COMMAND_POSITION = 3;
    private static final int VALUE_1_COMMAND_POSITION = 4;
    private static final int VALUE_2_COMMAND_POSITION = 5;
    public static final String SPLIT_CHAR = ";";
    private List<KnowledgeCommandData> knowledgeScheme;

    public KnowledgeParser(List<KnowledgeCommandData> knowledgeScheme) {
        this.knowledgeScheme = knowledgeScheme;
    }

    public static KnowledgeCommandData generateCommandRecord(String recordName, ParameterType type, IntParameter parameter, Comparator comparator, String... values) {
        StringBuilder command = new StringBuilder();
        command.append(recordName);
        command.append(SPLIT_CHAR);
        command.append(type);
        command.append(SPLIT_CHAR);
        command.append(parameter);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        for (String value : values) {
            command.append(SPLIT_CHAR);
            command.append(value);
        }

        return parseCommandRecord(command.toString());
    }

    public static KnowledgeCommandData generateCommandRecord(String recordName, ParameterType type, BooleanParameter parameter, Comparator comparator) {
        StringBuilder command = new StringBuilder();
        command.append(recordName);
        command.append(SPLIT_CHAR);
        command.append(type);
        command.append(SPLIT_CHAR);
        command.append(parameter);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        return parseCommandRecord(command.toString());
    }

    public static KnowledgeCommandData generateCommandRecord(String recordName, ParameterType type, Border parameter, Comparator comparator) {
        StringBuilder command = new StringBuilder();
        command.append(recordName);
        command.append(SPLIT_CHAR);
        command.append(type);
        command.append(SPLIT_CHAR);
        command.append(parameter);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        return parseCommandRecord(command.toString());
    }

    public static KnowledgeCommandData generateCommandRecord(String recordName, ParameterType type, StringParameter parameter, Comparator comparator, String... values) {
        StringBuilder command = new StringBuilder();
        command.append(recordName);
        command.append(SPLIT_CHAR);
        command.append(type);
        command.append(SPLIT_CHAR);
        command.append(parameter);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        for (String value : values) {
            command.append(SPLIT_CHAR);
            command.append(value);
        }

        return parseCommandRecord(command.toString());
    }

    public static KnowledgeCommandData generateCommandRecord(String recordName, ParameterType type, StringParameter parameter, Comparator comparator, StringParameter parameter2) {
        StringBuilder command = new StringBuilder();
        command.append(recordName);
        command.append(SPLIT_CHAR);
        command.append(type);
        command.append(SPLIT_CHAR);
        command.append(parameter);
        command.append(SPLIT_CHAR);
        command.append(comparator);
        command.append(SPLIT_CHAR);
        command.append(parameter2);

        return parseCommandRecord(command.toString());
    }

    public KnowledgeBase createKnowledgeBase(ConvertedData data) {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        for (KnowledgeCommandData instruction : knowledgeScheme) {
            String[] commands = instruction.toString().split(SPLIT_CHAR);
            switch (ParameterType.getType(commands[PARAMETER_TYPE_COMMAND_POSITION])) {
                case INT:
                    parseIntCommand(commands, data, knowledgeBase);
                    break;
                case STRING:
                    parseStringCommand(commands, data, knowledgeBase);
                    break;
                case BOOLEAN:
                    parseBooleanCommand(commands, data, knowledgeBase);
                    break;
                case BORDER:
                    parseBorderCommand(commands, data, knowledgeBase);
                    break;
            }
        }
        return knowledgeBase;
    }

    public static KnowledgeCommandData parseCommandRecord(String commandRecord){
        KnowledgeCommandData commandData = new KnowledgeCommandData();
        String[] command = commandRecord.split(SPLIT_CHAR);
        commandData.setName(command[RECORD_NAME_COMMAND_POSITION]);
        commandData.setParameterType(command[PARAMETER_TYPE_COMMAND_POSITION]);
        commandData.setParameterName(command[PARAMETER_NAME_COMMAND_POSITION]);
        commandData.setComparator(command[COMPARATOR_NAME_COMMAND_POSITION]);
        for(int i=VALUE_1_COMMAND_POSITION;i<command.length;i++){
            commandData.getValues().add(command[i]);
        }

        return commandData;
    }

    private void parseBorderCommand(String[] commands, ConvertedData data, KnowledgeBase knowledgeBase) {
        Border border = Border.getBorder(commands[PARAMETER_NAME_COMMAND_POSITION]);
        switch (Comparator.getComparator(commands[COMPARATOR_NAME_COMMAND_POSITION])) {
            case EQUAL:
                addBorderParamEqual(commands[RECORD_NAME_COMMAND_POSITION], border,data,knowledgeBase);
                break;
            case GREATER:
                throw new UnsupportedOperationException("There is no \"greater\" operation defined for border parameter.");
            case LESS:
                throw new UnsupportedOperationException("There is no \"less\" operation defined for border parameter.");
            case BETWEEN:
                throw new UnsupportedOperationException("There is no \"between\" operation defined for border parameter.");
            case NOT_EQUAL:
                addBorderParamNotEqual(commands[RECORD_NAME_COMMAND_POSITION], border,data,knowledgeBase);
                break;
            case TRUE:
                throw new UnsupportedOperationException("There is no \"true\" operation defined for border parameter.");
            case FALSE:
                throw new UnsupportedOperationException("There is no \"false\" operation defined for border parameter.");
        }
    }

    private void parseBooleanCommand(String[] commands, ConvertedData data, KnowledgeBase knowledgeBase) {
        BooleanParameter parameter = BooleanParameter.getParameter(commands[PARAMETER_NAME_COMMAND_POSITION]);
        switch (Comparator.getComparator(commands[COMPARATOR_NAME_COMMAND_POSITION])) {
            case EQUAL:
                throw new UnsupportedOperationException("There is no \"equal\" operation defined for boolean parameter.");
            case GREATER:
                throw new UnsupportedOperationException("There is no \"greater\" operation defined for boolean parameter.");
            case LESS:
                throw new UnsupportedOperationException("There is no \"less\" operation defined for boolean parameter.");
            case BETWEEN:
                throw new UnsupportedOperationException("There is no \"between\" operation defined for boolean parameter.");
            case NOT_EQUAL:
                throw new UnsupportedOperationException("There is no \"notEqual\" operation defined for boolean parameter.");
            case TRUE:
                addBooleanParamTrue(commands[RECORD_NAME_COMMAND_POSITION], parameter,data,knowledgeBase);
                break;
            case FALSE:
                addBooleanParamFalse(commands[RECORD_NAME_COMMAND_POSITION], parameter,data,knowledgeBase);
                break;
        }
    }

    private void parseStringCommand(String[] commands, ConvertedData data, KnowledgeBase knowledgeBase) {
        StringParameter parameter = StringParameter.getParameter(commands[PARAMETER_NAME_COMMAND_POSITION]);
        switch (Comparator.getComparator(commands[COMPARATOR_NAME_COMMAND_POSITION])) {
            case EQUAL:
                try {
                    StringParameter parameter2 = StringParameter.getParameter(commands[VALUE_1_COMMAND_POSITION]);
                    addStringParamEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, parameter2,data,knowledgeBase);
                } catch (NoSuchParameterException e) {
                    addStringParamEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, commands[VALUE_1_COMMAND_POSITION],data,knowledgeBase);
                }
                break;
            case GREATER:
                throw new UnsupportedOperationException("There is no \"greater\" operation defined for string parameter.");
            case LESS:
                throw new UnsupportedOperationException("There is no \"less\" operation defined for string parameter.");
            case BETWEEN:
                throw new UnsupportedOperationException("There is no \"between\" operation defined for string parameter.");
            case NOT_EQUAL:
                try {
                    StringParameter parameter2 = StringParameter.getParameter(commands[VALUE_1_COMMAND_POSITION]);
                    addStringParamNotEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, parameter2,data,knowledgeBase);
                } catch (NoSuchParameterException e) {
                    addStringParamNotEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, commands[VALUE_1_COMMAND_POSITION],data,knowledgeBase);
                }
                break;
            case TRUE:
                throw new UnsupportedOperationException("There is no \"true\" operation defined for string parameter.");
            case FALSE:
                throw new UnsupportedOperationException("There is no \"false\" operation defined for string parameter.");
            default:
        }
    }

    private void parseIntCommand(String[] commands, ConvertedData data, KnowledgeBase knowledgeBase) {
        IntParameter parameter = IntParameter.getParameter(commands[PARAMETER_NAME_COMMAND_POSITION]);
        switch (Comparator.getComparator(commands[COMPARATOR_NAME_COMMAND_POSITION])) {
            case EQUAL:
                addIntParamEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, Integer.parseInt(commands[VALUE_1_COMMAND_POSITION]),data,knowledgeBase);
                break;
            case GREATER:
                addIntParamGreater(commands[RECORD_NAME_COMMAND_POSITION], parameter, Integer.parseInt(commands[VALUE_1_COMMAND_POSITION]),data,knowledgeBase);
                break;
            case LESS:
                addIntParamLess(commands[RECORD_NAME_COMMAND_POSITION], parameter, Integer.parseInt(commands[VALUE_1_COMMAND_POSITION]),data,knowledgeBase);
                break;
            case BETWEEN:
                addIntParamBetween(commands[RECORD_NAME_COMMAND_POSITION], parameter, Integer.parseInt(commands[VALUE_1_COMMAND_POSITION]), Integer.parseInt(commands[VALUE_2_COMMAND_POSITION]),data,knowledgeBase);
                break;
            case NOT_EQUAL:
                addIntParamNotEqual(commands[RECORD_NAME_COMMAND_POSITION], parameter, Integer.parseInt(commands[VALUE_1_COMMAND_POSITION]),data,knowledgeBase);
                break;
            case TRUE:
                throw new UnsupportedOperationException("There is no \"true\" operation defined for int parameter.");
            case FALSE:
                throw new UnsupportedOperationException("There is no \"false\" operation defined for int parameter.");
        }
    }

    private void addIntParamEqual(String factName, IntParameter parameter, int value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data) == value) knowledgeBase.addFact(new Fact(factName));
    }

    private void addIntParamNotEqual(String factName, IntParameter parameter, int value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data) != value) knowledgeBase.addFact(new Fact(factName));
    }

    private void addIntParamGreater(String factName, IntParameter parameter, int value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data) > value) knowledgeBase.addFact(new Fact(factName));
    }

    private void addIntParamLess(String factName, IntParameter parameter, int value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data) < value) knowledgeBase.addFact(new Fact(factName));
    }

    private void addIntParamBetween(String factName, IntParameter parameter, int value1, int value2, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data) < value1 && getParam(parameter,data) > value2) knowledgeBase.addFact(new Fact(factName));
        if (getParam(parameter,data) < value2 && getParam(parameter,data) > value1) knowledgeBase.addFact(new Fact(factName));
    }

    private void addStringParamEqual(String factName, StringParameter parameter, String value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data).equals(value)) knowledgeBase.addFact(new Fact(factName));
    }

    private void addStringParamEqual(String factName, StringParameter parameter, StringParameter parameter2, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter,data).equals(getParam(parameter2,data))) knowledgeBase.addFact(new Fact(factName));
    }

    private void addStringParamNotEqual(String factName, StringParameter parameter, String value, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (!getParam(parameter,data).equals(value)) knowledgeBase.addFact(new Fact(factName));
    }

    private void addStringParamNotEqual(String factName, StringParameter parameter, StringParameter parameter2, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (!getParam(parameter,data).equals(getParam(parameter2,data))) knowledgeBase.addFact(new Fact(factName));
    }

    private void addBooleanParamTrue(String factName, BooleanParameter parameter, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getParam(parameter, data)) knowledgeBase.addFact(new Fact(factName));
    }

    private void addBooleanParamFalse(String factName, BooleanParameter parameter, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (!getParam(parameter, data)) knowledgeBase.addFact(new Fact(factName));
    }

    private void addBorderParamEqual(String factName, Border border, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getBorder(data)==border) knowledgeBase.addFact(new Fact(factName));
    }

    private void addBorderParamNotEqual(String factName, Border border, ConvertedData data, KnowledgeBase knowledgeBase) {
        if (getBorder(data)!=border) knowledgeBase.addFact(new Fact(factName));
    }

    private Border getBorder(ConvertedData data){
        return data.getBorder();
    }

    private String getParam(StringParameter parameter, ConvertedData data) {
        switch (parameter) {
            case HIGHER_CARD_FIGURE:
                return data.getHigherFigure();
            case LOWER_CARD_FIGURE:
                return data.getLowerFigure();
        }
        throw new UnsupportedOperationException("There is no such parameter.");
    }

    private boolean getParam(BooleanParameter parameter, ConvertedData data) {
        switch (parameter) {
            case SUITED:
                return data.areCardsSuited();
        }
        throw new UnsupportedOperationException("There is no such parameter.");
    }


    private int getParam(IntParameter parameter, ConvertedData data) {
        switch (parameter) {
            case PLAYER_POSITION:
                return data.getPosition();
            case FIRST_RAISE_POSITION:
                return data.getFirstRaisePosition();
            case PLAYER_STACK_IN_BB:
                return data.getPlayerStackInBb();
            case EFFECTIVE_STACK_IN_BB:
                return data.getEffectiveStackInBb();
            case NUMBER_OF_PLAYERS:
                return data.getNumberOfPlayers();
            case NUMBER_OF_RAISERS:
                return data.getNumberOfRaisers();
            case NUMBER_OF_LIMPERS:
                return data.getNumberOfLimpers();
        }
        throw new UnsupportedOperationException("There is no such parameter.");
    }
}
