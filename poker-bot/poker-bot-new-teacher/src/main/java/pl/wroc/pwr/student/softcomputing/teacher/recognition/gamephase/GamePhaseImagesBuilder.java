package pl.wroc.pwr.student.softcomputing.teacher.recognition.gamephase;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.chips.ChipsImages;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;

import java.awt.image.BufferedImage;
import java.io.File;

public class GamePhaseImagesBuilder implements
		ImagesBuilder<BufferedImage, File> {

	private final TableParser tableParser;

	public GamePhaseImagesBuilder(TableParser tableParser) {
		this.tableParser = tableParser;
	}

	@Override
	public Images<BufferedImage> buildFrom(File object, ImageConfig imageConfig) {
		Images<BufferedImage> images = new ChipsImages();
		tableParser.loadTable(object.getAbsolutePath());
		images.add(new TrainingImage(tableParser.parseTableCards(), ""));
		return images;
	}

}
