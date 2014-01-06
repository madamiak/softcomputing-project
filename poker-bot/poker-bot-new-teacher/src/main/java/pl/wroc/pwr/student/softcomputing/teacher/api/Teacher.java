package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;

/**
 * A <code>Teacher</code> interface is used to teach neural network.
 * 
 * It can be used to teach figures, suits etc.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface Teacher {

	/**
	 * Teaches neural network using provided images.
	 * 
	 * @param images
	 *            learning set for neural network.
	 */
	@SuppressWarnings("rawtypes")
	void teach(Images images);

	/**
	 * Sets learning configuration that will be used to teach neural network.
	 * 
	 * @param learningConfig
	 */
	void setLearningConfig(LearningConfig learningConfig);
}
