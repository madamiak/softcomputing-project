package pl.wroc.pwr.student.softcomputing.teacher.api;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;

/**
 * An <code>ImageConfigBuilder</code> interface is used to create
 * <code>ImageConfig</code> instances.
 * 
 * @see ImageConfig
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageConfigBuilder {

	/**
	 * Builds <code>ImageConfig</code> instance according to provided
	 * information.
	 * 
	 * @param scale
	 *            scaling parameter for the image (of range [0,1]).
	 * @param blackAndWhite
	 *            whether or not image will be converted to black and white.
	 * @param grayscale
	 *            whether or not image will be converted to grayscale.
	 * @return image configuration.
	 */
	ImageConfig buildFrom(double scale, boolean blackAndWhite, boolean grayscale);

}
