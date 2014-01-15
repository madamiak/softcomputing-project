package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.ListResult;

public class BorderRecognizer implements Recognizer {

	private final BorderParser borderParser;

	public BorderRecognizer(BorderParser borderParser) {
		this.borderParser = borderParser;
	}

	@Override
	public Result recognize(Images images) {
		List<String> colors = new ArrayList<>();
		for (Object object : images.list()) {
			Image<BufferedImage> image = (Image<BufferedImage>) object;
			colors.add(borderParser.parseBorder(image.getData()));
		}
		return new ListResult(colors);
	}

	@Override
	public void setNeuralNetwork(String file) {
	}

}
