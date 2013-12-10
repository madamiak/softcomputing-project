package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Request;

public class TeachingRequest implements Request {

	private String value;
	private List<Integer> neuronsInLayers;
	private TransferFunctionType type;

	public TeachingRequest(String value, List<Integer> neuronsInLayers,
			TransferFunctionType type) {
		this.value = value;
		this.neuronsInLayers = neuronsInLayers;
		this.type = type;
	}

	@Override
	public NeuralNetwork neuralNetwork() {
		return new MultiLayerPerceptron(neuronsInLayers, type);
	}

	@Override
	public String outputFile() {
		return value;
	}

}
