package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;

/**
 * A <code>LearningConfigBuilder</code> interface is used to create
 * <code>LearningConfig</code> instances.
 * 
 * @see LearningConfig
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface LearningConfigBuilder {

	/**
	 * Creates <code>LearningConfig</code> instance based on given parameters.
	 * 
	 * @param maxIterations
	 *            maximum number of learning iterations.
	 * @param learningRate
	 *            value of learning rate.
	 * @param errorRate
	 *            value of error rate.
	 * @param momentum
	 *            value of momentum
	 * @return configuration of learning.
	 */
	LearningConfig buildFrom(int maxIterations, double learningRate,
			double errorRate, double momentum);

}
