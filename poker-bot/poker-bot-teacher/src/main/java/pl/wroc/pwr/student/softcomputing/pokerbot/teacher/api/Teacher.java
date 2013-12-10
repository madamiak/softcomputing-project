package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import org.neuroph.core.learning.DataSet;

/**
 * Interface to teaching given Neural Network.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface Teacher {

	/**
	 * Starts learning process of neural network
	 * 
	 * @param toLearn
	 *            data to learn
	 * @param params
	 *            array of parameters in order: max iterations, learning rate,
	 *            error rate, momentum
	 */
	void teach(DataSet toLearn, double[] params);

}
