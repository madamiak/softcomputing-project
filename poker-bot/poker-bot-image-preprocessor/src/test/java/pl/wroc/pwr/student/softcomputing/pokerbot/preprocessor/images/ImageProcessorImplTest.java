package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;

public class ImageProcessorImplTest {
	
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

}
