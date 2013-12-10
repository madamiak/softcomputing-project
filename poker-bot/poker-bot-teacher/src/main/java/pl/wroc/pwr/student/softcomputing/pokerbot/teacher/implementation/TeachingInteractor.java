package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Request;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Teacher;

public class TeachingInteractor implements Teacher {

	private final Request params;

	public TeachingInteractor(Request request) {
		this.params = request;
	}

	@Override
	public void teach(DataSet toLearn) {
		NeuralNetwork neuralNetwork = params.neuralNetwork();
		neuralNetwork.learn(toLearn);
		neuralNetwork.save(params.outputFile());
	}

}
