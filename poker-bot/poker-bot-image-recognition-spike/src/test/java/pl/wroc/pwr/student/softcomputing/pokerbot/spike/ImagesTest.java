package pl.wroc.pwr.student.softcomputing.pokerbot.spike;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;

public class ImagesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ImageLoader loader = new ImageFromFileLoader();
		BufferedImage red = loader.load("src/test/resources/red.png");
		BufferedImage green = loader.load("src/test/resources/green.png");
		BufferedImage black = loader.load("src/test/resources/black.png");
		BufferedImage white = loader.load("src/test/resources/white.png");
		ImageConverter converter = new ImageToArrayConverter();
		ImageProcessor processor = new ImageProcessorImpl();
		double[] flattenedArrayRed = converter.convert(processor.scale(red, 0.1));
		double[] flattenedArrayGreen = converter.convert(processor.scale(green, 0.1));
		double[] flattenedArrayBlack = converter.convert(processor.scale(black, 0.1));
		double[] flattenedArrayWhite = converter.convert(processor.scale(white, 0.1));
		DataSet trainingSet = new DataSet(flattenedArrayRed.length, 2);
		trainingSet.addRow(flattenedArrayWhite, new double[] { 0, 0 });
		trainingSet.addRow(flattenedArrayRed, new double[] { 0, 1 });
		trainingSet.addRow(flattenedArrayGreen, new double[] { 1, 0 });
		trainingSet.addRow(flattenedArrayBlack, new double[] { 1, 1 });

		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(
				TransferFunctionType.TANH, flattenedArrayRed.length,
				(int) Math.sqrt(flattenedArrayRed.length
						* flattenedArrayRed.length - 4), 2);
		// learn the training set
		myMlPerceptron.learn(trainingSet);

		// test perceptron
		System.out.println("Testing trained neural network");
		testNeuralNetwork(myMlPerceptron, trainingSet);

		// save trained neural network
		myMlPerceptron.save("target/images.nnet");

		// load saved neural network
		NeuralNetwork loadedMlPerceptron = NeuralNetwork
				.load("target/images.nnet");

		// test loaded neural network
		System.out.println("Testing loaded neural network");
		testNeuralNetwork(loadedMlPerceptron, trainingSet);
	}

	private void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {
		for (DataSetRow dataRow : tset.getRows()) {
			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[] networkOutput = nnet.getOutput();
			System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
			System.out.println(" Output: " + Arrays.toString(networkOutput));
		}
	}

}
