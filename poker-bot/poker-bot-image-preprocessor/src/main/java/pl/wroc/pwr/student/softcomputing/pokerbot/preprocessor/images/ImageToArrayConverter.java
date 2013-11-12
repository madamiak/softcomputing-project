package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;

public class ImageToArrayConverter implements ImageConverter
{

	@Override
	public double[] convert(BufferedImage image)
	{
		double[] flattenedRgbArray = new double[0];
		if (image == null)
			return flattenedRgbArray;
		int width = image.getWidth();
		int height = image.getHeight();
		int pixels = width * height;
		flattenedRgbArray = new double[pixels * 3];
		int[] dataBuffInt = image.getRGB(0, 0, width, height, null, 0, width);
		for (int i = 0; i < dataBuffInt.length; i++)
		{
			Color color = new Color(dataBuffInt[i]);
			flattenedRgbArray[i] = normalize(color.getRed());
			flattenedRgbArray[pixels + i] = normalize(color.getGreen());
			flattenedRgbArray[2 * pixels + i] = normalize(color.getBlue());
		}
		return flattenedRgbArray;
	}

	private double normalize(int rgbValue)
	{
		return rgbValue / 255.0;
	}

}
