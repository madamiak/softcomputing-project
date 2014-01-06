package pl.wroc.pwr.student.softcomputing.teacher.api;

/**
 * A <code>RecognizerFactory</code> interface is used to create proper instances of
 * <code>Recognizer</code> objects.
 * 
 * @see Recognizer
 * 
 * @author Mateusz Adamiak
 *
 */
public interface RecognizerFactory {
	
	/**
	 * Creates instance of <code>Recognizer</code> according to given type.
	 * 
	 * @param type
	 *            implementation of <code>Recognizer</code> to be created.
	 * @return instance implementing <code>Recognizer</code> interface.
	 */
	Recognizer create(String type);

}
