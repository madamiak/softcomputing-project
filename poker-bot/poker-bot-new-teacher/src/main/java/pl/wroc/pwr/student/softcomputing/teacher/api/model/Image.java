package pl.wroc.pwr.student.softcomputing.teacher.api.model;

/**
 * An <code>Image</code> interface provides data (generalized by
 * <code>Representation</code>) about the image. This is the input and output
 * information for neural network.
 * 
 * @author Mateusz Adamiak
 * 
 * @param <Representation>
 *            the image data will be provided as this type. It could be e.g.
 *            <code>BufferedImage</code>.
 * 
 * @see BufferedImage
 * 
 */
public interface Image<Representation> {

	/**
	 * Returns image data. It describes the input of neural network.
	 * 
	 * @return image data of given type.
	 */
	Representation getData();
	
	/**
	 * Returns name of the image. It describes the output of neural network.
	 * 
	 * @return name of the image.
	 */
	String getName();

}
