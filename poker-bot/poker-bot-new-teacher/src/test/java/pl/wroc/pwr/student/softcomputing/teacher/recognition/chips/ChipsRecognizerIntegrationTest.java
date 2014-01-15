package pl.wroc.pwr.student.softcomputing.teacher.recognition.chips;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ChipsParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

public class ChipsRecognizerIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Recognizer chipsRecognizer;
	private Images images;

	@Before
	public void setUp() throws Exception {
		ChipsParser chipsParser = new ChipsParserImpl();
		ImageProcessor imageProcessor = new ImageProcessorImpl();
		chipsRecognizer = new ChipsRecognizer(chipsParser, imageProcessor );
		TableParser tableParser = new TableParserImpl();
		ImagesBuilder<BufferedImage, File> imagesBuilder = new ChipsImagesBuilder(
				tableParser);
		File file = new File(IMAGE_DIR+"2d7c1.png");
		ImageConfig config = new TrainingImageConfig(1.0, true, false);
		images = imagesBuilder.buildFrom(file , config );
	}

	@Test
	public void test() {
		System.out.println(chipsRecognizer.recognize(images).getResultAsString());
	}

}
