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
	 * @param toLearn data to learn
	 */
	void teach(DataSet toLearn);


}
