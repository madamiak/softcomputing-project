package pl.wroc.pwr.student.softcomputing.teacher.training.figures;

import java.awt.image.BufferedImage;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.events.NeuralNetworkEvent;
import org.neuroph.core.events.NeuralNetworkEventListener;
import org.neuroph.core.learning.DataSet;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.AbstractTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

public class FigureTeacher extends AbstractTeacher {
	
	private final OutputConverter outputConverter;

	public FigureTeacher(ImageConverter imageConverter) {
		super(imageConverter);
		this.outputConverter = new FigureOutputConverter();
	}

	@Override
	public void teach(Images images) {
		DataSet trainingSet = createTrainingSet(images, 4);
		NeuralNetwork neuralNetwork = createNeuralNetwork(trainingSet);
		neuralNetwork.learn(trainingSet);
		neuralNetwork.save(outputName);
	}
	
	@Override
	protected DataSet createTrainingSet(Images images, int outputSize) {
		int inputSize = imageConverter.convert(((FigureImages) images).list()
				.get(0).getData()).length;
		System.out.println("Training set parameters: input -> " + inputSize + ", output -> " + outputSize);
		System.out.println("Creating training set...");
		DataSet trainingSet = new DataSet(inputSize, outputSize);
		for (Image<BufferedImage> i : ((FigureImages) images).list()) {
			trainingSet.addRow(imageConverter.convert(i.getData()),
					outputConverter.convert(i.getName()));
		}
		System.out.println("Created training set size " + ((FigureImages) images).list().size());
		return trainingSet;
	}
}
