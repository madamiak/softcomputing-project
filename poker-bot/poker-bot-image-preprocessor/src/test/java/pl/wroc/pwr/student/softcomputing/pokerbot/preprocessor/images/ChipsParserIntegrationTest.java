package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Ignore;
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

	@Ignore
	@Test
	public void shouldReadChips() {
		TableParserImpl parser = new TableParserImpl();
		ImageProcessorImpl processor = new ImageProcessorImpl();
		
		System.out.println("***Chips from under avatar***");
		
		System.out.println("Test on 5.png");
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

		System.out.println("Test on 6.png");
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

		System.out.println("Test on 7.png");
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

        System.out.println("Test on 11.png");
        parser.loadTable("src/test/resources/Tournament/11.png");
        int[] values4= {0,1240,435,0,745,350};
        for (int j = 1; j <= 5; j++){
            int chips4=testObject.parseChips(processor.convertToBlackAndWhite(parser.parseOpponentChips(j)));
            System.out.println("Opponent "+j+": "+chips4);
            assertEquals(values4[j-1],chips4);

        }
        int chips4=testObject.parseChips(processor.convertToBlackAndWhite(parser.parsePlayerChips()));
        System.out.println("Player: "+chips4);
        assertEquals(values4[5],chips4);

        System.out.println("Test on 14.png");
        parser.loadTable("src/test/resources/Tournament/14.png");
        int[] values5= {0,1030,Integer.MAX_VALUE,0,0,1260};
        for (int j = 1; j <= 5; j++){
            int chips5=testObject.parseChips(processor.convertToBlackAndWhite(parser.parseOpponentChips(j)));
            System.out.println("Opponent "+j+": "+chips5);
            assertEquals(values5[j-1],chips5);

        }
        int chips5=testObject.parseChips(processor.convertToBlackAndWhite(parser.parsePlayerChips()));
        System.out.println("Player: "+chips5);
        assertEquals(values5[5],chips5);
		
	}

	@Test
	public void shouldReadTableChips() {
		TableParserImpl parser = new TableParserImpl();
		ImageProcessorImpl processor = new ImageProcessorImpl();
		
		System.out.println("***Chips from table***");
		
		System.out.println("Test on 4.png");
		parser.loadTable("src/test/resources/Tournament/4.png");
		int[] values= {50,0,470,525,0,25};
		for (int j = 1; j <= 5; j++){
			int chips=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
			System.out.println("Opponent "+j+": "+chips);
			assertEquals(values[j-1],chips);
			
		}
		int chips=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
		System.out.println("Player: "+chips);
		assertEquals(values[5],chips);
		
		System.out.println("Test on 5.png");
		parser.loadTable("src/test/resources/Tournament/5.png");
		int[] values2= {25,50,0,45,50,0};
		for (int j = 1; j <= 5; j++){
			int chips2=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
			System.out.println("Opponent "+j+": "+chips2);
			assertEquals(values2[j-1],chips2);
			
		}
		int chips2=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
		System.out.println("Player: "+chips2);
		assertEquals(values2[5],chips2);
		
		System.out.println("Test on 6.png");
		parser.loadTable("src/test/resources/Tournament/6.png");
		int[] values3= {0,25,50,210,0,0};
		for (int j = 1; j <= 5; j++){
			int chips3=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
			System.out.println("Opponent "+j+": "+chips3);
			assertEquals(values3[j-1],chips3);
			
		}
		int chips3=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
		System.out.println("Player: "+chips3);
		assertEquals(values3[5],chips3);
		
		System.out.println("Test on 2.png");
		parser.loadTable("src/test/resources/Tournament/2.png");
		int[] values4= {0,0,0,0,430,50};
		for (int j = 1; j <= 5; j++){
			int chips4=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
			System.out.println("Opponent "+j+": "+chips4);
			assertEquals(values4[j-1],chips4);
			
		}
		int chips4=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
		System.out.println("Player: "+chips4);
		assertEquals(values4[5],chips4);
		
		System.out.println("Test on 12.png");
		parser.loadTable("src/test/resources/Tournament/12.png");
		int[] values5= {0,50,0,0,865,25};
		for (int j = 1; j <= 5; j++){
			int chips5=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
			System.out.println("Opponent "+j+": "+chips5);
			assertEquals(values5[j-1],chips5);
			
		}
		int chips5=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
		System.out.println("Player: "+chips5);
		assertEquals(values5[5],chips5);

        System.out.println("Test on 13.png");
        parser.loadTable("src/test/resources/Tournament/13.png");
        int[] values6= {50,0,0,935,0,25};
        for (int j = 1; j <= 5; j++){
            int chips6=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
            System.out.println("Opponent "+j+": "+chips6);
            assertEquals(values6[j-1],chips6);

        }
        int chips6=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
        System.out.println("Player: "+chips6);
        assertEquals(values6[5],chips6);

        System.out.println("Test on 14.png");
        parser.loadTable("src/test/resources/Tournament/14.png");
        int[] values7= {0,0,390,0,0,200};
        for (int j = 1; j <= 5; j++){
            int chips7=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
            System.out.println("Opponent "+j+": "+chips7);
            assertEquals(values7[j-1],chips7);

        }
        int chips7=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
        System.out.println("Player: "+chips7);
        assertEquals(values7[5],chips7);

        System.out.println("Test on Fold20.png");
        parser.loadTable("src/test/resources/Tournament/Fold20.png");
        int[] values8= {0,0,30,0,60,0};
        for (int j = 1; j <= 5; j++){
            int chips8=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
            System.out.println("Opponent "+j+": "+chips8);
            assertEquals(values8[j-1],chips8);

        }
        int chips8=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
        System.out.println("Player: "+chips8);
        assertEquals(values8[5],chips8);

        System.out.println("Test on 18.png");
        parser.loadTable("src/test/resources/Tournament/18.png");
        int[] values9= {0,0,500,1125,0, 50};
        for (int j = 1; j <= 5; j++){
            int chips9=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parseOpponentTableChips(j)));
            System.out.println("Opponent "+j+": "+chips9);
            assertEquals(values9[j-1],chips9);

        }
        int chips9=testObject.parseTableChips(processor.convertToBlackAndWhite(parser.parsePlayerTableChips()));
        System.out.println("Player: "+chips9);
        assertEquals(values9[5],chips9);
		
	}
	
	
	

}
