package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSaver;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToFileSaver;

public class ImageSaverTest
{
	private ImageSaver testObject;

	@Before
	public void setUp() throws Exception
	{
		testObject = new ImageToFileSaver();
	}

	@Test
	public void whenSavingTheImage_ShouldCreateNewImageInTheSameDirectory()
	{
		testObject.save(new ImageFromFileLoader().load("src/test/resources/black.png"), "target/newImage.png");
		assertTrue(new File("target/newImage.png").exists());
		new File("target/newImage.png").delete();
	}

}
