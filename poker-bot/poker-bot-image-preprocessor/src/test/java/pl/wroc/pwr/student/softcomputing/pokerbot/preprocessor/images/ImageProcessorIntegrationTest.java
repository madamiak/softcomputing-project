package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
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
		new ImageToFileSaver().save(testObject.convertToBlackAndWhite(new ImageFromFileLoader().load("src/test/resources/Tournament/1.png")),"target/bnw1.png");
		new ImageToFileSaver().save(testObject.convertToBlackAndWhite(new ImageFromFileLoader().load("src/test/resources/Tournament/9.png")),"target/bnw9.png");
		new ImageToFileSaver().save(testObject.convertToBlackAndWhite(new ImageFromFileLoader().load("src/test/resources/Tournament/11.png")),"target/bnw11.png");
	}

	@Test
	public void shouldConvertChipsToBlackAndWhite() {
		ImageToFileSaver saver = new ImageToFileSaver();
		TableParserImpl parser = new TableParserImpl();

		
		parser.loadTable("src/test/resources/Tournament/5.png");
		
		for (int i = 1; i <= 5; i++){
			saver.save(testObject.convertToBlackAndWhite(parser.parseOpponentChips(i)),"target/Opponent"+i+"ChipsBnW5.png");
		}
		saver.save(testObject.convertToBlackAndWhite(parser.parsePlayerChips()),"target/PlayerChipsBnW5.png");
		
		parser.loadTable("src/test/resources/Tournament/9.png");
		
		for (int i = 1; i <= 5; i++){
			saver.save(testObject.convertToBlackAndWhite(parser.parseOpponentChips(i)),"target/Opponent"+i+"ChipsBnW9.png");
		}
		saver.save(testObject.convertToBlackAndWhite(parser.parsePlayerChips()),"target/PlayerChipsBnW9.png");
		
		parser.loadTable("src/test/resources/Tournament/11.png");
		
		for (int i = 1; i <= 5; i++){
			saver.save(testObject.convertToBlackAndWhite(parser.parseOpponentChips(i)),"target/Opponent"+i+"ChipsBnW11.png");
		}
		saver.save(testObject.convertToBlackAndWhite(parser.parsePlayerChips()),"target/PlayerChipsBnW11.png");
	}

	@Test
	public void shouldScaleImage() {
		ImageToFileSaver saver = new ImageToFileSaver();
		ImageFromFileLoader loader = new ImageFromFileLoader();
		BufferedImage testInputImage = loader.load("src/test/resources/Tournament/5.png");
		BufferedImage testOutputImage = testObject.scale(testInputImage,0.5);
		
		saver.save(testOutputImage,"target/scalled05.png");
		assertEquals((int)(testInputImage.getWidth()*0.5),testOutputImage.getWidth());
		assertEquals((int)(testInputImage.getHeight()*0.5),testOutputImage.getHeight());

		testOutputImage = testObject.scale(testInputImage,2.0);
		
		saver.save(testOutputImage,"target/scalled20.png");
		assertEquals((int)(testInputImage.getWidth()*2.0),testOutputImage.getWidth());
		assertEquals((int)(testInputImage.getHeight()*2.0),testOutputImage.getHeight());
		
	}

}
