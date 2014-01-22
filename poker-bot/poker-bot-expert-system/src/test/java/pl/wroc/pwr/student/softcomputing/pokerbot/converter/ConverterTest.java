package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by RaV on 17.01.14.
 */
public class ConverterTest {
    @Test
    public void testConvertData() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(1475);
        totalChips.add(1450);
        totalChips.add(1500);
        totalChips.add(1500);
        totalChips.add(1500);
        totalChips.add(0);
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

        Converter converter = new Converter(true, 5, "T of Clubs", "Q of Diamonds", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 5);
        assertEquals(data.getPlayerStackInBb(), 29);
        assertEquals(data.getEffectiveStackInBb(), 29);
        assertTrue(data.getHigherFigure().equals("Q"));
        assertTrue(data.getLowerFigure().equals("T"));
        assertFalse(data.areCardsSuited());
    }
    @Test
    public void testConvertData2() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(850);
        totalChips.add(0);
        totalChips.add(1595);
        totalChips.add(0);
        totalChips.add(495);
        totalChips.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(100);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("noLabel");
        border.add("lime");
        border.add("noLabel");
        border.add("red");
        border.add("noLabel");

        Converter converter = new Converter(true, 1, "2 of Diamonds", "7 of Clubs", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 4);
        assertEquals(data.getPlayerStackInBb(), 8);
        assertEquals(data.getEffectiveStackInBb(), 8);
        assertTrue(data.getHigherFigure().equals("7"));
        assertTrue(data.getLowerFigure().equals("2"));
        assertFalse(data.areCardsSuited());
    }
    @Test
    public void testConvertData3() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(535);
        totalChips.add(480);
        totalChips.add(480);
        totalChips.add(480);
        totalChips.add(535);
        totalChips.add(430);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(430);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("lime");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, "10 of Clubs", "9 of Clubs", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 6);
        assertEquals(data.getPlayerStackInBb(), 11);
        assertEquals(data.getEffectiveStackInBb(), 9);
        assertTrue(data.getHigherFigure().equals("10"));
        assertTrue(data.getLowerFigure().equals("9"));
        assertTrue(data.areCardsSuited());
    }
}
