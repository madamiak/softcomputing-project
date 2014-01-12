package pl.wroc.pwr.student.softcomputing.teacher.training;

import java.util.ArrayList;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;

public abstract class AbstractTeacher implements Teacher {

	protected final ImageConverter imageConverter;
	protected LearningRule learningRule;
	protected String outputName;

	public AbstractTeacher(ImageConverter imageConverter) {
		this.imageConverter = imageConverter;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public abstract void teach(Images images);

	protected abstract DataSet createTrainingSet(Images images, int outputSize);

	protected NeuralNetwork createNeuralNetwork(DataSet trainingSet) {
		List<Integer> neuronsInLayers = new ArrayList<>();
		int inputSize = trainingSet.getInputSize();
		int outputSize = trainingSet.getOutputSize();
		int hiddenLayerSize = (int) Math.sqrt(Math.pow(inputSize, 2)
				+ Math.pow(outputSize, 2));
		neuronsInLayers.add(inputSize);
		neuronsInLayers.add(hiddenLayerSize);
		neuronsInLayers.add(outputSize);
		System.out.println("Creating neural network...");
		NeuralNetwork neuralNetwork = new MultiLayerPerceptron(neuronsInLayers);
		System.out.println("Created neural network of size: input layer -> "
				+ inputSize + ", hidden layer ->" + hiddenLayerSize
				+ ", output layer -> " + outputSize);
		neuralNetwork.setLearningRule(learningRule);
		return neuralNetwork;
	}

	@Override
	public void setLearningConfig(LearningConfig learningConfig) {
		validate(learningConfig);
		setupLearnigRule(learningConfig);
		System.out.println("Configured neural network to: iterations -> " + learningConfig.maxIterations() 
				+ ", learning rate -> " + learningConfig.learningRate() + ", maximum error -> "
				+ learningConfig.errorRate() + ", momentum -> " + learningConfig.momentum());
		this.outputName = learningConfig.outputName();
	}

	private void setupLearnigRule(LearningConfig learningConfig) {
		MomentumBackpropagation rule = new MomentumBackpropagation();
		if(learningConfig.maxIterations() != 0) rule.setMaxIterations(learningConfig.maxIterations());
		if(learningConfig.learningRate() != 0) rule.setLearningRate(learningConfig.learningRate());
		if(learningConfig.errorRate() != 0) rule.setMaxError(learningConfig.errorRate());
		if(learningConfig.momentum() != 0) rule.setMomentum(learningConfig.momentum());
		this.learningRule = rule;
	}

	protected void validate(LearningConfig learningConfig) {
		if (learningConfig.maxIterations() < 1)
			throw new IllegalArgumentException();
		if (learningConfig.learningRate() < 0.0
				|| learningConfig.learningRate() > 1.0)
			throw new IllegalArgumentException();
		if (learningConfig.errorRate() < 0.0
				|| learningConfig.errorRate() > 1.0)
			throw new IllegalArgumentException();
		if (learningConfig.momentum() < 0.0 || learningConfig.momentum() > 1.0)
			throw new IllegalArgumentException();
		if (learningConfig.outputName() == null
				|| learningConfig.outputName().equals(""))
			throw new IllegalArgumentException();
	}

}