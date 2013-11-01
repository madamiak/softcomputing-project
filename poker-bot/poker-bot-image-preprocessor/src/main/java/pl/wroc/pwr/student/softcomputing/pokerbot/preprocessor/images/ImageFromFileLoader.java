package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;

public class ImageFromFileLoader implements ImageLoader
{

	public double[] load(String pathToFile)
	{
		if (pathToFile == null)
		{
			return new double[0];
		}
		double[] flattenedRgbArray = null;

		try
		{
			File input = new File(pathToFile);
			BufferedImage image = ImageIO.read(input);
			int width = image.getWidth();
			int height = image.getHeight();
			int pixels = width * height;
			flattenedRgbArray = new double[pixels * 3];
			int[] dataBuffInt = image.getRGB(0, 0, width, height, null, 0, width);
			for (int i = 0; i < dataBuffInt.length; i++)
			{
				Color color = new Color(dataBuffInt[i]);
				flattenedRgbArray[i] = color.getRed();
				flattenedRgbArray[pixels + i] = color.getGreen();
				flattenedRgbArray[2 * pixels + i] = color.getBlue();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return flattenedRgbArray;
	}

}
