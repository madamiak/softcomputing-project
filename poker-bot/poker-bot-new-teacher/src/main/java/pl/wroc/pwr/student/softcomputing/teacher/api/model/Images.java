package pl.wroc.pwr.student.softcomputing.teacher.api.model;

import java.util.List;

/**
 * An <code>Images</code> interface is a wrapped collection of
 * <code>Image</code>s.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface Images<Representation> {
	
	/**
	 * Adds image to collection.
	 * 
	 * @param image
	 */
	void add(Image<Representation> image);

	/**
	 * Lists all images.
	 * 
	 * @return list of images.
	 */
	List<Image<Representation>> list();

}
