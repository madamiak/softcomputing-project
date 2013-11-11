package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;

public class ChipsParserIntegrationTest {
	
	private ChipsParser testObject;

	@Before
	public void setUp()
	{
		testObject = new ChipsParserImpl();
	}

	@Test
	public void shouldReadChips() {
		TableParserImpl parser = new TableParserImpl();
		ImageProcessorImpl processor = new ImageProcessorImpl();
		
		parser.loadTable("src/test/resources/Tournament/5.png");
		int[] values= {385,410,1065,Integer.MAX_VALUE,470,440};
		for (int j = 1; j <= 5; j++){
			int chips=testObject.parseChips(processor.convertToBlackAndWhite(parser.parseOpponentChips(j)));
			System.out.println("Opponent "+j+": "+chips);
			assertEquals(values[j-1],chips);
			
		}
		int chips=testObject.parseChips(processor.convertToBlackAndWhite(parser.parsePlayerChips()));
		System.out.println("Player: "+chips);
		assertEquals(values[5],chips);
		
		parser.loadTable("src/test/resources/Tournament/6.png");
		int[] values2= {375,385,1005,Integer.MAX_VALUE,460,430};
		for (int j = 1; j <= 5; j++){
			int chips2=testObject.parseChips(processor.convertToBlackAndWhite(parser.parseOpponentChips(j)));
			System.out.println("Opponent "+j+": "+chips2);
			assertEquals(values2[j-1],chips2);
			
		}
		int chips2=testObject.parseChips(processor.convertToBlackAndWhite(parser.parsePlayerChips()));
		System.out.println("Player: "+chips2);
		assertEquals(values2[5],chips2);
		
		parser.loadTable("src/test/resources/Tournament/7.png");
		int[] values3= {355,365,935,225,440,410};
		for (int j = 1; j <= 5; j++){
			int chips3=testObject.parseChips(processor.convertToBlackAndWhite(parser.parseOpponentChips(j)));
			System.out.println("Opponent "+j+": "+chips3);
			assertEquals(values3[j-1],chips3);
			
		}
		int chips3=testObject.parseChips(processor.convertToBlackAndWhite(parser.parsePlayerChips()));
		System.out.println("Player: "+chips3);
		assertEquals(values3[5],chips3);
		
	}
	
	
	

}
