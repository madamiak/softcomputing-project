package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Boolean> presentAtTable = new ArrayList<Boolean>();
        List<String> border = new ArrayList<String>();
        totalChips.add(1475);
        totalChips.add(1450);
        totalChips.add(1500);
        totalChips.add(1500);
        totalChips.add(1500);
        totalChips.add(0);
        chipsAtTable.add(25);
        chipsAtTable.add(50);
        chipsAtTable.add(50);
        chipsAtTable.add(150);
        chipsAtTable.add(150);
        chipsAtTable.add(0);
        presentAtTable.add(true);
        presentAtTable.add(true);
        presentAtTable.add(true);
        presentAtTable.add(true);
        presentAtTable.add(true);
        presentAtTable.add(false);
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");
        border.add("noLabel");

        Converter converter = new Converter(true, 5, 5, "T", "h", "Q", "d",totalChips, chipsAtTable,presentAtTable, border);
        ConvertedData data = converter.convertData();
        System.out.println(data.toString());
        assertEquals(data.getPosition(), 5);
        assertEquals(data.getPlayerStackinBb(), 29);
        assertEquals(data.getEffectiveStack(), 1475);
        assertTrue(data.getHigherFigure().equals("Q"));
        assertTrue(data.getLowerFigure().equals("T"));
        assertFalse(data.areCardsSuited());
    }
}
