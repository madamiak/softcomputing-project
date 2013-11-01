package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileSplitter;

public class ImageSplitterTest
{
	private ImageSplitter testObject;

	@Before
	public void setUp() throws Exception
	{
		testObject = new ImageFromFileSplitter();
	}

	@Test
	public void whenCroppingTheImage_ShouldCreateNewImageInTheSameDirectory()
	{
		testObject.crop("src/test/resources/black.png", "target/newImage.png", 5, 5, 5, 3);
		assertTrue(new File("target/newImage.png").exists());
		new File("target/newImage.png").delete();
	}

}
