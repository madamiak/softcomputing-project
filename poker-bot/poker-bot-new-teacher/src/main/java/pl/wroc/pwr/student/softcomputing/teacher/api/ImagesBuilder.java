package pl.wroc.pwr.student.softcomputing.teacher.api;

import java.util.List;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;

/**
 * An <code>ImageBuilder</code> interface is used to create <code>Images</code>
 * instances of given type (generalized by <code>Representation</code>) from
 * object generalized by <code>Origin</code>.
 * 
 * @see Images
 * 
 * @author Mateusz Adamiak
 * 
 * @param <Representation>
 *            representation of images.
 * @param <Origin>
 *            origin collection of images.
 */
public interface ImagesBuilder<Representation, Origin extends List<?>> {

	/**
	 * Creates <code>Images</code> instance that holds information about images.
	 * 
	 * @param object
	 *            collection of images.
	 * @param imageConfig
	 *            image comfiguration.
	 * @return wrapped collection of images.
	 */
	Images<Representation> buildFrom(Origin object, ImageConfig imageConfig);

}
