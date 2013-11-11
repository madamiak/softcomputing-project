package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;

public class ImageProcessorImpl implements ImageProcessor {

	@Override
	public BufferedImage convertToGrayscale(BufferedImage image) {
		 BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(),
                 BufferedImage.TYPE_BYTE_GRAY);

         Graphics2D g = gray.createGraphics();
         g.drawImage(image, 0, 0, null);
         g.dispose();

		return gray;
	}

	@Override
	public BufferedImage convertToBlackAndWhite(BufferedImage image) {
		 BufferedImage bnw = new BufferedImage(image.getWidth(), image.getHeight(),
                 BufferedImage.TYPE_BYTE_BINARY);

         Graphics2D g = bnw.createGraphics();
         g.drawImage(image, 0, 0, null);
         g.dispose();
		return bnw;
	}

}
