package pl.wroc.pwr.student.softcomputing.teacher.training.dealers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

public class DealerImagesBuilder implements
		ImagesBuilder<BufferedImage, List<File>> {

	private final TableParser tableParser;
	private final ImageProcessor imageProcessor;

	public DealerImagesBuilder(TableParser tableParser,
			ImageProcessor imageProcessor) {
		this.tableParser = tableParser;
		this.imageProcessor = imageProcessor;
	}

	@Override
	public Images<BufferedImage> buildFrom(List<File> object,
			ImageConfig imageConfig) {
		Images<BufferedImage> images = new TrainingImages();
		for (File file : object) {
			tableParser.loadTable(file.getAbsolutePath());

			BufferedImage dealer = tableParser.parseDealerButton();
			dealer = processImage(imageConfig, dealer);
			TrainingImage dealerImage = new TrainingImage(dealer, getDealerPosition(file.getAbsolutePath()));
			images.add(dealerImage);
		}
		return images;
	}

	private BufferedImage processImage(ImageConfig imageConfig,
			BufferedImage image) {
		if (imageConfig.getScale() < 1.0)
			image = imageProcessor.scale(image, imageConfig.getScale());
		if (imageConfig.isBlackAndWhite())
			image = imageProcessor.convertToBlackAndWhite(image);
		if (imageConfig.isGrayscale())
			image = imageProcessor.convertToGrayscale(image);
		return image;
	}

	private String getDealerPosition(String name) {
		return name.substring(name.lastIndexOf(File.separator)).substring(5, 6);
	}

}
