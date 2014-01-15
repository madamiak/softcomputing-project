package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.FoldParser;

public class FoldParserImpl implements FoldParser {

	@Override
	public boolean parseFoldButton(BufferedImage image) {
		if(image.getWidth()!=50 || image.getHeight()!=25)throw new WrongImageSizeException();
		int sum = 0;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				sum += image.getRGB(i, j)%10;
			}
		}
		return -3 == sum/(image.getHeight() * image.getWidth());
	}
	
	public class WrongImageSizeException extends RuntimeException {
		
	}

}
