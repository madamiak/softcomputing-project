package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import java.awt.image.BufferedImage;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.AbstractTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

public class SuitTeacher extends AbstractTeacher {

	private OutputConverter outputConverter;

	public SuitTeacher(ImageConverter imageConverter) {
		super(imageConverter);
		this.outputConverter = new SuitOutputConverter();
	}

	@Override
	public void teach(Images images) {
		DataSet trainingSet = createTrainingSet(images, 2);
		NeuralNetwork neuralNetwork = createNeuralNetwork(trainingSet);
		neuralNetwork.learn(trainingSet);
	}
	
	@Override
	protected DataSet createTrainingSet(Images images, int outputSize) {
		int inputSize = imageConverter.convert(((TrainingImages) images).list()
				.get(0).getData()).length;
		DataSet trainingSet = new DataSet(inputSize, outputSize);
		for (Image<BufferedImage> i : ((TrainingImages) images).list()) {
			trainingSet.addRow(imageConverter.convert(i.getData()),
					outputConverter.convert(i.getName()));
		}
		return trainingSet;
	}

}
