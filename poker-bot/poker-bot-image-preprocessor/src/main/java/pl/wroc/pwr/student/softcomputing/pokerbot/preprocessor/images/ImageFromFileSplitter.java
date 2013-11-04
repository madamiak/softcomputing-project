package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageSplitter;

public class ImageFromFileSplitter implements ImageSplitter
{

	public BufferedImage crop(BufferedImage image,  int coordinateX, int coordinateY, int height, int width)
	{
			return image.getSubimage(coordinateX, coordinateY, width, width);
	}

}
