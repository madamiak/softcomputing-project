package pl.wroc.pwr.student.softcomputing.teacher.recognition.dealers;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.PokerBotRecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.suits.SuitImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

public class DealerRecognizerIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Recognizer figureRecognizer;
	private Images images;

	@Before
	public void setUp() throws Exception {
		RecognizerFactory recognizerFactory = new PokerBotRecognizerFactory();
		figureRecognizer = recognizerFactory.create("dealer");
		figureRecognizer.setNeuralNetwork("src/test/resources/d.nnet");
		TableParser tableParser = new TableParserImpl();
		ImageProcessor imageProcessor = new ImageProcessorImpl();
		ImagesBuilder<BufferedImage, File> imagesBuilder = new DealerImagesBuilder(tableParser, imageProcessor);
		ImageConfig imageConfig = new TrainingImageConfig(0.2, true, false);
		File file = new File(IMAGE_DIR+"AhTc4.png");
		images = imagesBuilder.buildFrom(file, imageConfig);
	}

	@Test
	public void test() {
		assertEquals("4", figureRecognizer.recognize(images).getResultAsString());
	}

}
