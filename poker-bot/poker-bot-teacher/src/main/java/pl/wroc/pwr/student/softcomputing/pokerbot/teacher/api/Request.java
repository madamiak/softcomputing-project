package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import org.neuroph.core.NeuralNetwork;

public interface Request {

	NeuralNetwork neuralNetwork();

	String outputFile();

}
