package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by RaV on 08.04.14.
 */
public class BorderTest {

    @Test
    public void testGettingBorder(){
        assertEquals(Border.Random, Border.getBorder("Random"));
    }
}
