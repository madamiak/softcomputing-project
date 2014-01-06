package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
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
	 * @param image
	 *            image whose content is to be recognized.
	 * @return result of the recognition.
	 */
	@SuppressWarnings("rawtypes")
	Result recognize(Image image);

}
