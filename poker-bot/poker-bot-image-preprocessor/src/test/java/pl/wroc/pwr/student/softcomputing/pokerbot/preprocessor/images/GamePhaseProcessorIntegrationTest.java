package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.GamePhaseProcessor;

import static org.junit.Assert.assertEquals;

/**
 * Created by RaV on 12.10.14.
 */
public class GamePhaseProcessorIntegrationTest {

    private GamePhaseProcessor testObject;

    @Before
    public void setUp()
    {
        testObject = new GamePhaseProcessorImpl();
    }
    @Test
    public void shouldRecognize0rdPhase() throws Exception {
        TableParserImpl parser = new TableParserImpl();
        parser.loadTable("src/test/resources/Tournament/19.png");
        assertEquals(0, testObject.processGamePhase(parser.parseTableCards()));
    }
    @Test
    public void shouldRecognize1rdPhase() throws Exception {
        TableParserImpl parser = new TableParserImpl();
        parser.loadTable("src/test/resources/Tournament/21.png");
        assertEquals(1, testObject.processGamePhase(parser.parseTableCards()));
    }
    @Test
    public void shouldRecognize2rdPhase() throws Exception {
        TableParserImpl parser = new TableParserImpl();
        parser.loadTable("src/test/resources/Tournament/22.png");
        assertEquals(2, testObject.processGamePhase(parser.parseTableCards()));
    }
    @Test
    public void shouldRecognize3rdPhase() throws Exception {
        TableParserImpl parser = new TableParserImpl();
        parser.loadTable("src/test/resources/Tournament/20.png");
        assertEquals(3, testObject.processGamePhase(parser.parseTableCards()));
    }
}
