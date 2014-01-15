package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

/**
 * A <code>Recognizer</code> interface is used to recognize content of the
 * image.
 * 
 * It can be used to recognize figures, suits, dealer position as well as amount
 * of players' chips etc.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface Recognizer {

	/**
	 * Recognizes the content of provided image.
	 * 
	 * Given images object should contain 1 image, when recognizing simple
	 * pictures (like dealer button, chips), and 2 images when recognizing
	 * complex pictures (like figures or suits)
	 * 
	 * @param images
	 *            images whose content is to be recognized.
	 * @return result of the recognition.
	 */
	@SuppressWarnings("rawtypes")
	Result recognize(Images images);

	void setNeuralNetwork(String file);

}
