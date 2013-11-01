package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.*;

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
	public void whenLoadingImageFromNotExistingFile_ShouldReturnEmptyArray() throws Exception
	{
		final double[] imageAsFlattenedArray = testObject.load(null);
		assertEquals(0, imageAsFlattenedArray.length);
	}

	@Test
	public void whenLoadingImageFromFile_ShouldReturnFlattenedRGBArray() throws Exception
	{
		final double[] imageAsFlattenedArray = testObject.load("src/test/resources/black.png");
		assertEquals(32 * 32 * 3, imageAsFlattenedArray.length);
	}

	@Test
	public void whenLoadingBlackPicture_ShouldReturnOnlyZeroes() throws Exception
	{
		final double[] givenZeroesArray = new double[32 * 32 * 3]; // it's by default array of zeroes
		final double[] imageAsFlattenedArray = testObject.load("src/test/resources/black.png");
		assertArrayEquals(givenZeroesArray, imageAsFlattenedArray, 0.001);
	}
}
