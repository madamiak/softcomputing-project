package pl.wroc.pwr.student.softcomputing.teacher.training;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;

public class NeuralNetworkConfig implements LearningConfig {

	private final int maxIterations;
	private final double learningRate;
	private final double errorRate;
	private final double momentum;
	private final String outputName;

	public NeuralNetworkConfig(int maxIterations, double learningRate,
			double errorRate, double momentum, String outputName) {
		this.maxIterations = maxIterations;
		this.learningRate = learningRate;
		this.errorRate = errorRate;
		this.momentum = momentum;
		this.outputName = outputName;
	}

	@Override
	public int maxIterations() {
		return maxIterations;
	}

	@Override
	public double learningRate() {
		return learningRate;
	}

	@Override
	public double errorRate() {
		return errorRate;
	}

	@Override
	public double momentum() {
		return momentum;
	}

	@Override
	public String outputName() {
		return outputName;
	}

}
