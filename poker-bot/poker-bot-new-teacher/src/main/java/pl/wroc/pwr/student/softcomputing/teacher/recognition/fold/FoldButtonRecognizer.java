package pl.wroc.pwr.student.softcomputing.teacher.recognition.fold;

import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.FoldParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.StringResult;

public class FoldButtonRecognizer implements Recognizer {

	private final FoldParser foldParser;

	public FoldButtonRecognizer(FoldParser foldParser) {
		this.foldParser = foldParser;
	}

	@Override
	public Result recognize(Images images) {
		Image<BufferedImage> img = ((Image<BufferedImage>)images.list().get(0));
		boolean isActive = foldParser.parseFoldButton(img.getData());
		return isActive ? new StringResult("active") : new StringResult("inactive");
	}

	@Override
	public void setNeuralNetwork(String file) {
		throw new UnsupportedOperationException();
	}

}
