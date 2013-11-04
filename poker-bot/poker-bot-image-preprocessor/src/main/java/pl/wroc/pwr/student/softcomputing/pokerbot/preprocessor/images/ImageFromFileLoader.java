package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;

public class ImageFromFileLoader implements ImageLoader
{

	public BufferedImage load(String pathToFile)
	{
		if (pathToFile == null)
		{
			return null;
		}

		try
		{
			File input = new File(pathToFile);
			return ImageIO.read(input);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
