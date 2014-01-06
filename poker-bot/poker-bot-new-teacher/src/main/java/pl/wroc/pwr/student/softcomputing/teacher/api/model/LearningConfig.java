package pl.wroc.pwr.student.softcomputing.teacher.api.model;

/**
 * A <code>LearningConfig</code> interface provides information about learning
 * configuration.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface LearningConfig {

	/**
	 * Returns maximum iterations of learning unit.
	 * 
	 * @return maximum iterations of learning unit.
	 */
	int maxIterations();

	/**
	 * Returns value of learning rate.
	 * 
	 * @return value of learning rate.
	 */
	double learningRate();

	/**
	 * Returns value of error rate.
	 * 
	 * @return value of error rate.
	 */
	double errorRate();

	/**
	 * Returns value of momentum.
	 * 
	 * @return value of momentum.
	 */
	double momentum();

	/**
	 * Returns path to the file where the output will be saved.
	 * 
	 * @return path to the file where the output will be saved.
	 */
	String outputName();

}
