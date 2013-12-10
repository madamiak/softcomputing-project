package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.nnet.learning.MomentumBackpropagation;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Request;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Teacher;

public class TeachingInteractor implements Teacher {

	private final Request params;

	public TeachingInteractor(Request request) {
		this.params = request;
	}

	@Override
	public void teach(DataSet toLearn, double[] networkParams) {
		NeuralNetwork neuralNetwork = params.neuralNetwork();
		MomentumBackpropagation momentumBackpropagation = new MomentumBackpropagation();
		if(networkParams[0] > 0 )
			momentumBackpropagation.setMaxIterations((int) networkParams[0]);
		if(networkParams[1] > 0 )
			momentumBackpropagation.setLearningRate(networkParams[1]);
		if(networkParams[2] > 0 )
			momentumBackpropagation.setMaxError(networkParams[2]);
		if(networkParams[3] > 0 )
			momentumBackpropagation.setMomentum(networkParams[3]);
		
		neuralNetwork.setLearningRule(momentumBackpropagation);
		
		System.out.println("Learning rate: " + ((MomentumBackpropagation)neuralNetwork.getLearningRule()).getLearningRate());
		System.out.println("Max error: " + ((MomentumBackpropagation)neuralNetwork.getLearningRule()).getMaxError());
		System.out.println("Momentum: " + ((MomentumBackpropagation)neuralNetwork.getLearningRule()).getMomentum());
		
		neuralNetwork.learn(toLearn);
		
		System.out.println("Iterations: " + ((MomentumBackpropagation)neuralNetwork.getLearningRule()).getCurrentIteration());
		neuralNetwork.save(params.outputFile());
	}

}
