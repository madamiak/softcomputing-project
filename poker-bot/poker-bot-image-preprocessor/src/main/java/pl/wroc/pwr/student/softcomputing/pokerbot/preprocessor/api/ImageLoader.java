package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

/**
 * Provides image loading functionality.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageLoader
{

	/**
	 * Loads image from given file path as flattened RGB array (one-dimensional
	 * array of size width * heights * 3)
	 * 
	 * @param pathToFile
	 *          path to the given image.
	 * @return image as flattened array of doubles.
	 */
	double[] load(String pathToFile);

}
