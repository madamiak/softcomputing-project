package pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.ListResult;

public class TableChipsRecognizer implements Recognizer {

	private final ChipsParser chipsParser;
	private final ImageProcessor imageProcessor;

	public TableChipsRecognizer(ChipsParser chipsParser,
			ImageProcessor imageProcessor) {
				this.chipsParser = chipsParser;
				this.imageProcessor = imageProcessor;
	}

	@Override
	public Result recognize(Images images) {
		List<Integer> chips = new ArrayList<>();
		for (Object object : images.list()) {
			Image<BufferedImage> image = (Image<BufferedImage>) object;
			chips.add(chipsParser.parseTableChips(imageProcessor.convertToBlackAndWhite(image.getData())));
		}
		return new ListResult(chips);
	}

	@Override
	public void setNeuralNetwork(String file) {
	}

}
