package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageToArray;

public class ImageToArrayConverter implements ImageToArray {

	@Override
	public double[] convert(BufferedImage image) {
		double[] flattenedRgbArray = new double[0];
		if(image==null)return flattenedRgbArray;
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
		return flattenedRgbArray;
	}

}
