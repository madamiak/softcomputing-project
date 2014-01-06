package pl.wroc.pwr.student.softcomputing.teacher.api;

/**
 * A <code>TeacherFactory</code> interface is used to create proper instances of
 * <code>Teacher</code> objects.
 * 
 * @see Teacher
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface TeacherFactory {

	/**
	 * Creates instance of <code>Teacher</code> according to given type.
	 * Types:
	 * "figure"
	 * "suit"
	 * "dealer"
	 * 
	 * @param type
	 *            implementation of <code>Teacher</code> to be created.
	 * @return instance implementing <code>Teacher</code> interface.
	 * @throws InstantiationException
	 *             when could not create instance.
	 */
	Teacher create(String type) throws InstantiationException;
}
