package pl.wroc.pwr.student.softcomputing.teacher.recognition.suits;

import java.awt.image.BufferedImage;
import java.util.List;

import org.neuroph.core.NeuralNetwork;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.OutputConverter;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.StringResult;

public class SuitRecognizer implements Recognizer {
	private final ImageConverter imageConverter;
	private final OutputConverter outputConvert = new SuitOutputConverter();
	private NeuralNetwork neuralNetwork;

	public SuitRecognizer(ImageConverter imageConverter) {
		this.imageConverter = imageConverter;
	}

	@Override
	public Result recognize(Images images) {
		List<Image<BufferedImage>> list = images.list();
		String result = "";
		for (Image<BufferedImage> image : list) {
			neuralNetwork.setInput(imageConverter.convert(image.getData()));
			neuralNetwork.calculate();
			result += outputConvert.convert(neuralNetwork.getOutput());
		}
		
		return new StringResult(result);
	}

	@Override
	public void setNeuralNetwork(String file) {
		this.neuralNetwork = NeuralNetwork.load(file);
	}

}
