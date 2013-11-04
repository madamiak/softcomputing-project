package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;

public class ImageToArrayConverterTest
{
	private ImageToArrayConverter testObject;

	@Before
	public void setUp()
	{
		testObject = new ImageToArrayConverter();
	}

	@Test
	public void shouldBeAbleToCreateInstance()
	{
		assertNotNull(testObject);
	}

	@Test
	public void whenConvertingFromNull_ShouldReturnEmptyArray() throws Exception
	{
		final double[] imageAsFlattenedArray = testObject.convert(null);
		assertEquals(0, imageAsFlattenedArray.length);
	}

	@Test
	public void whenConvertingImage_ShouldReturnFlattenedRGBArray() throws Exception
	{
		final double[] imageAsFlattenedArray = testObject.convert(new ImageFromFileLoader().load("src/test/resources/black.png"));
		assertEquals(32 * 32 * 3, imageAsFlattenedArray.length);
	}

	@Test
	public void whenConverting_ShouldReturnOnlyZeroes() throws Exception
	{
		final double[] givenZeroesArray = new double[32 * 32 * 3]; // it's by default array of zeroes
		final double[] imageAsFlattenedArray = testObject.convert(new ImageFromFileLoader().load("src/test/resources/black.png"));
		assertArrayEquals(givenZeroesArray, imageAsFlattenedArray, 0.001);
	}
}
