package pl.wroc.pwr.student.softcomputing.teacher.recognition.fold;

import java.awt.image.BufferedImage;
import java.io.File;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

public class FoldButtonImagesBuilder implements
		ImagesBuilder<BufferedImage, File> {

	private final TableParser tableParser;

	public FoldButtonImagesBuilder(TableParser tableParser) {
		this.tableParser = tableParser;
	}

	@Override
	public Images<BufferedImage> buildFrom(File object, ImageConfig imageConfig) {
		Images<BufferedImage> images = new TrainingImages();
		tableParser.loadTable(object.getAbsolutePath());
		images.add(new TrainingImage(tableParser.parseFoldButton(), ""));
		return images;
	}

}
