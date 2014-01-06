package pl.wroc.pwr.student.softcomputing.teacher.api.model;

/**
 * A <code>ImageConfig</code> interface provides information about image
 * configuration.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageConfig {

	/**
	 * Returns scale parameter.
	 * 
	 * @return scale parameter.
	 */
	double getScale();
	
	/**
	 * Returns true when image is in black and white.
	 * 
	 * @return true when image is in black and white.
	 */
	boolean isBlackAndWhite();

	/**
	 * Returns true when image is in grayscale.
	 * 
	 * @return true when image is in grayscale.
	 */
	boolean isGrayscale();

	/**
	 * Returns name of the image.
	 * 
	 * @return name of the image.
	 */
	String getName();
}
