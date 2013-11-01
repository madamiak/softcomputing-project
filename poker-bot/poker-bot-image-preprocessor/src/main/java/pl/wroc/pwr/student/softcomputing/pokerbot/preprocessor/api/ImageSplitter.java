package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

/**
 * Provides image splitting functionality.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageSplitter
{

	/**
	 * Crop image from existing one from specified point and specified height and
	 * width.
	 * 
	 * @param pathToFile
	 *          Path to the given image.
	 * @param pathToNewImage
	 *          Path to the cropped image.
	 * @param coordinateX
	 *          Coordinate X from which the image will be cropped.
	 * @param coordinateY
	 *          Coordinate Y from which the image will be cropped.
	 * @param height
	 *          Height of cropped image.
	 * @param width
	 *          Width of cropped image.
	 */
	void crop(String pathToFile, String pathToNewImage, int coordinateX, int coordinateY, int height, int width);

}
