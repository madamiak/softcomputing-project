package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;

public class ImageLoaderTest
{
	private ImageLoader testObject;

	@Before
	public void setUp()
	{
		testObject = new ImageFromFileLoader();
	}

	@Test
	public void shouldBeAbleToCreateInstance()
	{
		assertNotNull(testObject);
	}

	@Test
	public void whenLoadingImageFromNotExistingFile_ShouldReturnNull() throws Exception
	{
		assertNull(testObject.load(null));
	}

	@Test
	public void whenLoadingImageFromFile_ShouldReturnBufferedImageObject() throws Exception
	{
		BufferedImage bufferedImage = testObject.load("src/test/resources/black.png");
		assertTrue(bufferedImage.getHeight()==32);
		assertTrue(bufferedImage.getWidth()==32);
	}
}
