package pl.wroc.pwr.student.softcomputing.pokerbot.expert;

import junit.framework.TestCase;
import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Engine;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertSystem;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by RaV on 22.01.14.
 */
public class ExpertSystemTest extends TestCase {

    @Test
    public void testBasicStrategy1(){
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(490);
        totalChips.add(490);
        totalChips.add(490);
        totalChips.add(490);
        totalChips.add(490);
        totalChips.add(490);
        chipsAtTable.add(25);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("green");
        border.add("noLabel");
        border.add("gray");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 6, "5 of Diamonds", "K of Hearts", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        ExpertSystem expertSystem = new ExpertSystem();
        Engine engine = expertSystem.getBasicStrategyEngine(data);
        boolean isProven = engine.performBackwardChaining(new Fact("Raise"));
        System.out.println(engine.getOutPut());
        assertTrue(isProven);
    }

    @Test
    public void testBasicStrategy2(){
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(445);
        totalChips.add(1145);
        totalChips.add(420);
        totalChips.add(0);
        totalChips.add(470);
        totalChips.add(470);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(25);
        chipsAtTable.add(0);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("green");
        border.add("noLabel");
        border.add("gray");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 2, "J of Spades", "2 of Hearts", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        ExpertSystem expertSystem = new ExpertSystem();
        Engine engine = expertSystem.getBasicStrategyEngine(data);
        boolean isProven = engine.performBackwardChaining(new Fact("Raise"));
        System.out.println(engine.getOutPut());
        assertFalse(isProven);
    }

}
