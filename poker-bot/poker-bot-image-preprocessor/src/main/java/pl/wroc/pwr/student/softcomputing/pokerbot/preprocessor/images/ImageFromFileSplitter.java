package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;

public class ImageFromFileSplitter implements ImageSplitter
{

	public void crop(String pathToFile, String pathToNewImage, int coordinateX, int coordinateY, int height, int width)
	{
		File f = new File(pathToNewImage);
		try
		{
			final BufferedImage originalImage = ImageIO.read(new File(pathToFile));
			final BufferedImage newImage = originalImage.getSubimage(coordinateX, coordinateY, width, width);
			ImageIO.write(newImage, "png", f);
			f.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
