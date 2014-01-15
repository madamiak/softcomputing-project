package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.BorderParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;

public class BorderRecognizerIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Recognizer borderRecognizer;
	private Images images;

	@Before
	public void setUp() throws Exception {
		BorderParser borderParser = new BorderParserImpl();
		borderRecognizer = new BorderRecognizer(borderParser);
		TableParser tableParser = new TableParserImpl();
		ImagesBuilder<BufferedImage, File> imagesBuilder = new BorderImagesBuilder(tableParser);
		File file = new File(IMAGE_DIR+"2d7c1.png");
		images = imagesBuilder.buildFrom(file , null);
	}

	@Test
	public void test() {
		System.out.println(borderRecognizer.recognize(images).getResultAsString());
	}

}
