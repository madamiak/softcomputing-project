package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromBufferedImageSplitter;

public class ImageSplitterTest
{
	private ImageSplitter testObject;

	@Before
	public void setUp() throws Exception
	{
		testObject = new ImageFromBufferedImageSplitter();
	}

	@Test
	public void whenCroppingTheImage_ShouldReturnSmallerImage()
	{
		BufferedImage preprocessedImage = new ImageFromFileLoader().load("src/test/resources/black.png");
		BufferedImage croppedImage = testObject.crop(preprocessedImage, 5, 5, 5, 3);
		assertTrue(preprocessedImage.getHeight()>croppedImage.getHeight());
		assertTrue(preprocessedImage.getWidth()>croppedImage.getWidth());
	}

}
