package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;

/**
 * An <code>ImageBuilder</code> interface is used to create <code>Image</code>
 * instances of given type (generalized by <code>Representation</code>) from
 * object generalized by <code>Origin</code>.
 * 
 * @see Image
 * 
 * @author Mateusz Adamiak
 * 
 * @param <Representation>
 *            representation of the image.
 * @param <Origin>
 *            origin type of the image.
 * 
 */
public interface ImageBuilder<Representation, Origin> {

	/**
	 * Create image based on origin object.
	 * 
	 * @param object
	 *            image in origin type.
	 * @param imageConfig
	 *            configuration of the image that will be used to convert given
	 *            image.
	 * @return image instance of given representation.
	 */
	Image<Representation> buildFrom(Origin object, ImageConfig imageConfig);
}
