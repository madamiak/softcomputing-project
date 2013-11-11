package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;

public class ImageProcessorIntegrationTest {
	
	private ImageProcessor testObject;

	@Before
	public void setUp()
	{
		testObject = new ImageProcessorImpl();
	}

	@Test
	public void shouldConvertToGrayscale() {
		new ImageToFileSaver().save(testObject.convertToGrayscale(new ImageFromFileLoader().load("src/test/resources/Tournament/1.png")),"target/gray.png");
	}

	@Test
	public void shouldConvertToBlackAndWhite() {
		new ImageToFileSaver().save(testObject.convertToBlackAndWhite(new ImageFromFileLoader().load("src/test/resources/Tournament/1.png")),"target/bnw.png");
	}

	@Test
	public void shouldConvertChipsToBlackAndWhite() {
		ImageToFileSaver saver = new ImageToFileSaver();
		TableParserImpl parser = new TableParserImpl();
		parser.loadTable("src/test/resources/Tournament/5.png");
		
		for (int i = 1; i <= 5; i++){
			saver.save(testObject.convertToBlackAndWhite(parser.parseOpponentChips(i)),"target/Opponent"+i+"ChipsBnW.png");
		}
		saver.save(testObject.convertToBlackAndWhite(parser.parsePlayerChips()),"target/PlayerChipsBnW.png");
	}

}
