package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSaver;

public class ImageToFileSaver implements ImageSaver
{

	public void save(BufferedImage image, String pathToNewImage)
	{
		File f = new File(pathToNewImage);
		try
		{
			ImageIO.write(image, "png", f);
			f.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
