package pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem;

import org.junit.Before;
import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Border;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.BooleanParameter.*;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.Comparator.*;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.IntParameter.*;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.ParameterType.*;
import static pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Enums.StringParameter.*;

/**
 * Created by RaV on 08.04.14.
 */
public class KnowledgeParserTest {

    private ConvertedData data;

    @Before
    public void setUp(){
        data = new ConvertedData();
        data.setPosition(5);
        data.setFirstRaisePosition(3);
        data.setPlayerStackInBb(10);
        data.setEffectiveStackInBb(9);
        data.setHigherFigure("7");
        data.setLowerFigure("2");
        data.setCardsSuited(true);
        data.setBorder(Border.Random);
        data.setNumberOfPlayers(6);
        data.setNumberOfRaisers(1);
        data.setNumberOfLimpers(0);
    }

    @Test
    public void testIntCommandParsing(){
        List<KnowledgeCommandData> instructions = new ArrayList<KnowledgeCommandData>();

        instructions.add(KnowledgeParser.parseCommandRecord("No Limpers;Numeric;numberOfLimpers;Equal;0"));
        instructions.add(KnowledgeParser.parseCommandRecord("No Raisers;Numeric;numberOfRaisers;Equal;0"));
        instructions.add(KnowledgeParser.parseCommandRecord("Some Limpers;Numeric;numberOfLimpers;Greater than;0"));
        instructions.add(KnowledgeParser.parseCommandRecord("Some Raisers;Numeric;numberOfRaisers;Greater than;0"));
        instructions.add(KnowledgeParser.parseCommandRecord("Effective stack > 5;Numeric;effectiveStackInBb;Greater than;5"));
        instructions.add(KnowledgeParser.parseCommandRecord("Effective stack <= 5;Numeric;effectiveStackInBb;Less;6"));
        instructions.add(KnowledgeParser.parseCommandRecord("5 < Player stack < 12;Numeric;playerStackInBb;Between;5;12"));
        instructions.add(KnowledgeParser.parseCommandRecord("9 < Player stack < 11;Numeric;playerStackInBb;Between;11;9"));
        instructions.add(KnowledgeParser.parseCommandRecord("Player stack != 10;Numeric;playerStackInBb;Not Equal;9"));

        KnowledgeParser parser = new KnowledgeParser(instructions);

        KnowledgeBase knowledgeBase = parser.createKnowledgeBase(data);
        System.out.println(knowledgeBase);
        assertEquals(6,knowledgeBase.factCount());
    }

    @Test
    public void testGenerateCommandRecord(){
        KnowledgeCommandData command1 = KnowledgeParser.generateCommandRecord("No Limpers", INT, NUMBER_OF_LIMPERS, EQUAL, "0");
        KnowledgeCommandData command2 = KnowledgeParser.generateCommandRecord("Effective stack > 5", INT, EFFECTIVE_STACK_IN_BB, GREATER, "5");
        List<KnowledgeCommandData> instructions = new ArrayList<KnowledgeCommandData>();
        instructions.add(command1);
        instructions.add(command2);
        KnowledgeParser parser = new KnowledgeParser(instructions);
        KnowledgeBase knowledgeBase = parser.createKnowledgeBase(data);
        System.out.println(knowledgeBase);
        assertEquals(2, knowledgeBase.factCount());
    }

    @Test
    public void testStringCommands(){
        KnowledgeCommandData command1 = KnowledgeParser.generateCommandRecord("Higher figure = 7", STRING, HIGHER_CARD_FIGURE, EQUAL, "7");
        KnowledgeCommandData command2 = KnowledgeParser.generateCommandRecord("Higher figure = 8", STRING, HIGHER_CARD_FIGURE, EQUAL, "8");
        KnowledgeCommandData command3 = KnowledgeParser.generateCommandRecord("Lower figure = 8", STRING, LOWER_CARD_FIGURE, EQUAL, "8");
        KnowledgeCommandData command4 = KnowledgeParser.generateCommandRecord("Lower figure = 2", STRING, LOWER_CARD_FIGURE, EQUAL, "2");
        KnowledgeCommandData command5 = KnowledgeParser.generateCommandRecord("Pair", STRING, LOWER_CARD_FIGURE, EQUAL, HIGHER_CARD_FIGURE);
        KnowledgeCommandData command6 = KnowledgeParser.generateCommandRecord("No pair", STRING, LOWER_CARD_FIGURE, NOT_EQUAL, HIGHER_CARD_FIGURE);
        List<KnowledgeCommandData> instructions = new ArrayList<KnowledgeCommandData>();
        instructions.add(command1);
        instructions.add(command2);
        instructions.add(command3);
        instructions.add(command4);
        instructions.add(command5);
        instructions.add(command6);
        KnowledgeParser parser = new KnowledgeParser(instructions);
        KnowledgeBase knowledgeBase = parser.createKnowledgeBase(data);
        System.out.println(knowledgeBase);
        assertEquals(3, knowledgeBase.factCount());
    }

    @Test
    public void testBooleanCommands(){
        KnowledgeCommandData command1 = KnowledgeParser.generateCommandRecord("Suited cards", BOOLEAN, SUITED, TRUE);
        KnowledgeCommandData command2 = KnowledgeParser.generateCommandRecord("Not suited cards", BOOLEAN, SUITED, FALSE);
        List<KnowledgeCommandData> instructions = new ArrayList<KnowledgeCommandData>();
        instructions.add(command1);
        instructions.add(command2);
        KnowledgeParser parser = new KnowledgeParser(instructions);
        KnowledgeBase knowledgeBase = parser.createKnowledgeBase(data);
        System.out.println(knowledgeBase);
        assertEquals(1, knowledgeBase.factCount());
    }

    @Test
    public void testBorderCommands(){
        KnowledgeCommandData command1 = KnowledgeParser.generateCommandRecord("Random border", BORDER, Border.Random, EQUAL);
        KnowledgeCommandData command2 = KnowledgeParser.generateCommandRecord("Not random border", BORDER, Border.Random, NOT_EQUAL);
        KnowledgeCommandData command3 = KnowledgeParser.generateCommandRecord("Pro border", BORDER, Border.Regular, EQUAL);
        KnowledgeCommandData command4 = KnowledgeParser.generateCommandRecord("Not pro border", BORDER, Border.Regular, NOT_EQUAL);
        List<KnowledgeCommandData> instructions = new ArrayList<KnowledgeCommandData>();
        instructions.add(command1);
        instructions.add(command2);
        instructions.add(command3);
        instructions.add(command4);
        KnowledgeParser parser = new KnowledgeParser(instructions);
        KnowledgeBase knowledgeBase = parser.createKnowledgeBase(data);
        System.out.println(knowledgeBase);
        assertEquals(2, knowledgeBase.factCount());

    }

    @Test
    public void testParseCommandRecord(){
        KnowledgeCommandData command1 = KnowledgeParser.generateCommandRecord("Random border", BORDER, Border.Random, EQUAL);
        KnowledgeCommandData command2 = KnowledgeParser.generateCommandRecord("Not suited cards", BOOLEAN, SUITED, FALSE);
        KnowledgeCommandData command3 = KnowledgeParser.generateCommandRecord("Lower figure = 8", STRING, LOWER_CARD_FIGURE, EQUAL, "8");
        KnowledgeCommandData command4 = KnowledgeParser.generateCommandRecord("Effective stack > 5", INT, EFFECTIVE_STACK_IN_BB, GREATER, "5");

        System.out.println(command1);
        System.out.println(command2);
        System.out.println(command3);
        System.out.println(command4);
    }
}
