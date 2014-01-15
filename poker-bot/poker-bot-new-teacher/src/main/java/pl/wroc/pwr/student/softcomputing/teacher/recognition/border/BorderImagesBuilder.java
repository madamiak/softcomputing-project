package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

import java.awt.image.BufferedImage;
import java.io.File;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.chips.ChipsImages;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;

public class BorderImagesBuilder implements ImagesBuilder<BufferedImage, File> {

	private final TableParser tableParser;

	public BorderImagesBuilder(TableParser tableParser) {
		this.tableParser = tableParser;
	}

	@Override
	public Images<BufferedImage> buildFrom(File object, ImageConfig imageConfig) {
		Images<BufferedImage> images = new BorderImages();
		tableParser.loadTable(object.getAbsolutePath());
		for (int i = 1; i < 6; i++) {
			images.add(new TrainingImage(tableParser.parseOpponentBorder(i), ""));
		}
		return images;
	}

}
