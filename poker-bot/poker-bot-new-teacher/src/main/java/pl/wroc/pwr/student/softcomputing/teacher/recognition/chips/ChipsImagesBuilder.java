package pl.wroc.pwr.student.softcomputing.teacher.recognition.chips;

import java.awt.image.BufferedImage;
import java.io.File;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;

public class ChipsImagesBuilder implements ImagesBuilder<BufferedImage, File> {

	private final TableParser tableParser;

	public ChipsImagesBuilder(TableParser tableParser) {
		this.tableParser = tableParser;
	}

	@Override
	public Images<BufferedImage> buildFrom(File object, ImageConfig imageConfig) {
		Images<BufferedImage> images = new ChipsImages();
		tableParser.loadTable(object.getAbsolutePath());
		images.add(new TrainingImage(tableParser.parsePlayerChips(), ""));
		for (int i = 1; i < 6; i++) {
			images.add(new TrainingImage(tableParser.parseOpponentChips(i), ""));
		}
		return images;
	}

}
