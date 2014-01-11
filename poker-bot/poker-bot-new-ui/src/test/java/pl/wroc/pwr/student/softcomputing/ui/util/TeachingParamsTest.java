package pl.wroc.pwr.student.softcomputing.ui.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by RaV on 05.01.14.
 */
public class TeachingParamsTest {
    TeachingParams teachingParams;

    @Before
    public void setUp(){
        teachingParams = new TeachingParams();
    }
    @Test
    public void testToStringWhenEmpty() throws Exception {
        assertTrue(teachingParams.toNamePostfix().equals("-1,0--"));
    }
    @Test
    public void testToStringWhenPartiallyEmpty() throws Exception {
        teachingParams.setBlackAndWhite(true);
        teachingParams.setMaxIterations(10);
        teachingParams.setErrorRate(0.32);
        assertTrue(teachingParams.toNamePostfix().equals("-1,0-t-"));
    }
    public void testToStringWhenPartiallyEmpty2() throws Exception {
        teachingParams.setGrayscale(true);
        teachingParams.setScale(0.32);
        assertTrue(teachingParams.toNamePostfix().equals("-0,32--t"));
    }
    public void testToStringWhenFilled() throws Exception {
        teachingParams.setScale(0.5);
        teachingParams.setBlackAndWhite(false);
        teachingParams.setGrayscale(true);
        teachingParams.setMaxIterations(1000);
        teachingParams.setLearningRate(0.8);
        teachingParams.setErrorRate(0.2);
        teachingParams.setMomentum(0.6);
        assertTrue(teachingParams.toNamePostfix().equals("-0,5--t"));
    }
}
