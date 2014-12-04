package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import org.junit.Ignore;
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

        Converter converter = new Converter(true, 6, "T of Clubs", "Q of Diamonds", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 5);
        assertEquals(data.getPlayerStackInBb(), 29);
        assertEquals(data.getEffectiveStackInBb(), 29);
        assertEquals(data.getFirstRaisePosition(), 0);
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
        assertEquals(data.getFirstRaisePosition(), 0);
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
        assertEquals(5, data.getFirstRaisePosition());
        assertTrue(data.getHigherFigure().equals("T"));
        assertTrue(data.getLowerFigure().equals("9"));
        assertTrue(data.areCardsSuited());
    }
    @Test
    public void testConvertData4() throws Exception {
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
        chipsAtTable.add(100);
        chipsAtTable.add(0);
        chipsAtTable.add(25);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("lime");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, "Ace of Clubs", "2 of Spades", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 6);
        assertEquals(data.getPlayerStackInBb(), 11);
        assertEquals(data.getEffectiveStackInBb(), 10);
        assertEquals(3, data.getFirstRaisePosition());
        assertTrue(data.getHigherFigure().equals("A"));
        assertTrue(data.getLowerFigure().equals("2"));
        assertFalse(data.areCardsSuited());
    }

    @Test
    public void testConvertData5() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(1460);
        totalChips.add(0);
        totalChips.add(1030);
        totalChips.add(390);
        totalChips.add(0);
        totalChips.add(0);
        chipsAtTable.add(200);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(390);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 3, "7 of Hearts", "10 of Diamonds", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 6);
        assertEquals(data.getFirstRaisePosition(), 5);
        assertEquals(data.getPlayerStackInBb(), 7);
        assertEquals(data.getEffectiveStackInBb(), 2);
        assertEquals(5, data.getFirstRaisePosition());
        assertTrue(data.getHigherFigure().equals("T"));
        assertTrue(data.getLowerFigure().equals("7"));
        assertFalse(data.areCardsSuited());
    }

    @Test
    public void testConvertData6() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(585);
        totalChips.add(100);
        totalChips.add(940);
        totalChips.add(870);
        totalChips.add(15);
        totalChips.add(405);
        chipsAtTable.add(50);
        chipsAtTable.add(100);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(25);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("lime");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, "J of Spades", "Q of Clubs", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 6);
        assertEquals(data.getFirstRaisePosition(), 1);
        assertEquals(data.getPlayerStackInBb(), 12);
        assertEquals(data.getEffectiveStackInBb(), 2);
        assertEquals(1, data.getFirstRaisePosition());
        assertTrue(data.getHigherFigure().equals("Q"));
        assertTrue(data.getLowerFigure().equals("J"));
        assertFalse(data.areCardsSuited());
    }

    @Test
    public void testConvertData7() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(1000);
        totalChips.add(500);
        totalChips.add(50);
        totalChips.add(2000);
        totalChips.add(0);
        totalChips.add(1500);
        chipsAtTable.add(0);
        chipsAtTable.add(50);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 1, "J of Spades", "Q of Clubs", totalChips, chipsAtTable, border, 100);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPlayerStackInBb(), 10);
        assertEquals(data.getEffectiveStackInBb(), 5);
    }

    @Test
    public void testConvertData8() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(800);
        totalChips.add(0);
        totalChips.add(2140);
        totalChips.add(0);
        totalChips.add(0);
        totalChips.add(1);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 1, "8 of Spades", "6 of Spades", totalChips, chipsAtTable, border, 100);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
    }

    @Test
    public void testConvertData9() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(1115);
        totalChips.add(0);
        totalChips.add(655);
        totalChips.add(0);
        totalChips.add(685);
        totalChips.add(465);
        chipsAtTable.add(100);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(465);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, "K of Hearts", "5 of Hearts", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
    }

    @Test
    public void testConvertData10() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(400);
        totalChips.add(425);
        totalChips.add(500);
        totalChips.add(500);
        totalChips.add(1125);
        totalChips.add(0);
        chipsAtTable.add(50);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(500);
        chipsAtTable.add(1125);
        chipsAtTable.add(0);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 4, "2 of Clubs", "5 of Diamonds", totalChips, chipsAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
    }

    @Test
    public void testConvertData11() throws Exception {
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> border = new ArrayList<String>();
        totalChips.add(496);
        totalChips.add(1524);
        totalChips.add(0);
        totalChips.add(0);
        totalChips.add(476);
        totalChips.add(476);
        chipsAtTable.add(20);
        chipsAtTable.add(20);
        chipsAtTable.add(0);
        chipsAtTable.add(0);
        chipsAtTable.add(20);
        chipsAtTable.add(20);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, "A of Clubs", "4 of Hearts", totalChips, chipsAtTable, border,20);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(25,data.getEffectiveStackInBb());
    }
}
