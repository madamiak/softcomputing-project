package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageFromFileLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Teacher;

public class PokerbotTeacher implements Teacher {
	private final List<TrainingUnit> units;
	private final String outputFileName;

	public PokerbotTeacher(List<TrainingUnit> images, String outputFileName) {
		this.units = images;
		this.outputFileName = outputFileName;
	}

	public void teach(TransferFunctionType type, TableElement tableElement,
			boolean blackAndWhite, boolean grayed, double scalingRatio) {
		final List<DataRow> toLearn = new ArrayList<DataRow>();
		final TableParser parser = new TableParserImpl();
		int inSize = 0;
		int outSize = 0;

		System.out.println("Start preprocessing...");

		for (TrainingUnit unit : units) {
			BufferedImage bufferedImage = unit.getImage();
			parser.loadTable(bufferedImage);
			switch (tableElement) {
			case FIGURE:
				prepareFigureData(blackAndWhite, grayed, scalingRatio, toLearn,
						parser, unit);
				break;
			case SUIT:
				prepareSuitData(blackAndWhite, grayed, scalingRatio, toLearn,
						parser, unit);
				break;
			default:
				break;
			}
		}
		inSize = toLearn.get(0).getInput().length;
		outSize = toLearn.get(0).getOutput().length;

		System.out.println("Setting knowledge...");

		DataSet trainingSet = new DataSet(inSize, outSize);
		for (DataRow dataRow : toLearn) {
			trainingSet.addRow(dataRow.getInput(), dataRow.getOutput());
		}

		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(type,
				inSize, calculateNeurons(inSize, outSize), outSize);
		System.out.println("Learning...");

		// learn the training set
		myMlPerceptron.learn(trainingSet);

		// test perceptron
		System.out.println("Testing trained neural network:");
		testNeuralNetwork(myMlPerceptron, trainingSet);

		// save trained neural network
		myMlPerceptron.save(outputFileName);

		// load saved neural network
		NeuralNetwork loadedMlPerceptron = NeuralNetwork.load(outputFileName);

		// test loaded neural network
		System.out.println("Testing loaded neural network:");
		testNeuralNetwork(loadedMlPerceptron, trainingSet);
	}

	@Override
	public void recognize(String table, TableElement figure, String learnedDataFile, boolean blackAndWhite, boolean grayed,
			double scalingRatio) {
		// load saved neural network
		NeuralNetwork loadedMlPerceptron = NeuralNetwork.load(learnedDataFile);
		
		TableParser parser = new TableParserImpl();
		parser.loadTable(table);
		
		double[] input = null;
		
		switch (figure) {
		case FIGURE:
			BufferedImage card1 = parser.parseFirstCard();
			input = prepareInput(card1, blackAndWhite, grayed, scalingRatio);
			recognize(loadedMlPerceptron, input);
			BufferedImage card2 = parser.parseSecondCard();
			input = prepareInput(card2, blackAndWhite, grayed, scalingRatio);
			recognize(loadedMlPerceptron, input);
			break;
		case SUIT:
			BufferedImage suit1 = parser.parseFirstSuit();
			input = prepareInput(suit1, blackAndWhite, grayed, scalingRatio);
			recognize(loadedMlPerceptron, input);
			BufferedImage suit2 = parser.parseSecondSuit();
			input = prepareInput(suit2, blackAndWhite, grayed, scalingRatio);
			recognize(loadedMlPerceptron, input);
			break;
		default:
			break;
		}
		
	}

	private double[] prepareInput(BufferedImage image, boolean blackAndWhite,
			boolean grayed, double scalingRatio) {
		final ImageProcessor processor = new ImageProcessorImpl();
		image = processor.scale(image, scalingRatio);
		if (blackAndWhite) {
			image = processor.convertToBlackAndWhite(image);
		}
		if (grayed) {
			image = processor.convertToGrayscale(image);
		}
		final ImageConverter converter = new ImageToArrayConverter();
		return converter.convert(image);
	}

	private void recognize(NeuralNetwork loadedMlPerceptron, double[] input) {
		loadedMlPerceptron.setInput(input);
		loadedMlPerceptron.calculate();
		double[] networkOutput = loadedMlPerceptron.getOutput();
		System.out.println(String.format("Output: %s\tInput: %s",
				convertOutput(networkOutput),
				Arrays.toString(input)));
	}

	private void prepareSuitData(boolean blackAndWhite, boolean grayed,
			double scalingRatio, final List<DataRow> toLearn,
			final TableParser parser, TrainingUnit unit) {
		BufferedImage image1 = parser.parseFirstSuit();
		double[] in1 = getInput(image1, blackAndWhite, grayed, scalingRatio);
		String name1 = unit.getValue().getName().substring(1, 2);
		double[] out1 = getOutput(name1);
		DataRow row1 = new DataRow(in1, out1);
		toLearn.add(row1);
		BufferedImage image2 = parser.parseSecondSuit();
		double[] in2 = getInput(image2, blackAndWhite, grayed, scalingRatio);
		String name2 = unit.getValue().getName().substring(3, 4);
		double[] out2 = getOutput(name2);
		DataRow row2 = new DataRow(in2, out2);
		toLearn.add(row2);
	}

	private void prepareFigureData(boolean blackAndWhite, boolean grayed,
			double scalingRatio, final List<DataRow> toLearn,
			final TableParser parser, TrainingUnit unit) {
		BufferedImage image1 = parser.parseFirstCard();
		double[] in1 = getInput(image1, blackAndWhite, grayed, scalingRatio);
		String name1 = unit.getValue().getName().substring(0, 1);
		double[] out1 = getOutput(name1);
		DataRow row1 = new DataRow(in1, out1);
		toLearn.add(row1);
		BufferedImage image2 = parser.parseSecondCard();
		double[] in2 = getInput(image2, blackAndWhite, grayed, scalingRatio);
		String name2 = unit.getValue().getName().substring(2, 3);
		double[] out2 = getOutput(name2);
		DataRow row2 = new DataRow(in2, out2);
		toLearn.add(row2);
	}

	private int calculateNeurons(int inSize, int outSize) {
		return (int) Math.sqrt(inSize * inSize - outSize * outSize);
	}

	private void testNeuralNetwork(NeuralNetwork nnet, DataSet tset) {
		for (DataSetRow dataRow : tset.getRows()) {
			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[] networkOutput = nnet.getOutput();
			System.out.println(String.format("Output: %s\tInput: %s",
					convertOutput(networkOutput),
					Arrays.toString(dataRow.getInput())));
		}
	}

	private double[] getInput(BufferedImage image, boolean blackAndWhite,
			boolean grayed, double scalingRatio) {
		final ImageProcessor processor = new ImageProcessorImpl();
		image = processor.scale(image, scalingRatio);
		if (blackAndWhite) {
			image = processor.convertToBlackAndWhite(image);
		}
		if (grayed) {
			image = processor.convertToGrayscale(image);
		}
		final ImageConverter converter = new ImageToArrayConverter();
		return converter.convert(image);
	}

	private double[] getOutput(String name) {
		switch (name) {
		case "2":
			return new double[] { 0, 0, 0, 0 };
		case "3":
			return new double[] { 0, 0, 0, 1 };
		case "4":
			return new double[] { 0, 0, 1, 0 };
		case "5":
			return new double[] { 0, 0, 1, 1 };
		case "6":
			return new double[] { 0, 1, 0, 0 };
		case "7":
			return new double[] { 0, 1, 0, 1 };
		case "8":
			return new double[] { 0, 1, 1, 0 };
		case "9":
			return new double[] { 0, 1, 1, 1 };
		case "T":
			return new double[] { 1, 0, 0, 0 };
		case "J":
			return new double[] { 1, 0, 0, 1 };
		case "Q":
			return new double[] { 1, 0, 1, 0 };
		case "K":
			return new double[] { 1, 0, 1, 1 };
		case "A":
			return new double[] { 1, 1, 0, 0 };
		case "h":
			return new double[] { 0, 0 };
		case "s":
			return new double[] { 0, 1 };
		case "d":
			return new double[] { 1, 0 };
		case "c":
			return new double[] { 1, 1 };
		}
		return null;
	}
	
	private String convertOutput(double[] output) {
		String out = "";
		for (double d : output) {
			if(d > 0.5) {
				out += "1";
			} else {
				out += "0";
			}
		}
		switch(out) {
		case "0000":
			return "2";
		case "0001":
			return "3";
		case "0010":
			return "4";
		case "0011":
			return "5";
		case "0100":
			return "6";
		case "0101":
			return "7";
		case "0110":
			return "8";
		case "0111":
			return "9";
		case "1000":
			return "T";
		case "1001":
			return "J";
		case "1010":
			return "Q";
		case "1011":
			return "K";
		case "1100":
			return "A";
		case "00":
			return "h";
		case "01":
			return "s";
		case "10":
			return "d";
		case "11":
			return "c";
		}
		
		return null;
	}

}
