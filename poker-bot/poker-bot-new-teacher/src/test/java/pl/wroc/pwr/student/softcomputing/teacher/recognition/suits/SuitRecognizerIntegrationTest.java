package pl.wroc.pwr.student.softcomputing.teacher.recognition.suits;

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
import pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

public class SuitRecognizerIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Recognizer figureRecognizer;
	private Images images;

	@Before
	public void setUp() throws Exception {
		RecognizerFactory recognizerFactory = new PokerBotRecognizerFactory();
		figureRecognizer = recognizerFactory.create("suit");
		figureRecognizer.setNeuralNetwork("s.nnet");
		TableParser tableParser = new TableParserImpl();
		ImageProcessor imageProcessor = new ImageProcessorImpl();
		ImagesBuilder<BufferedImage, File> imagesBuilder = new SuitImagesBuilder(tableParser, imageProcessor);
		ImageConfig imageConfig = new TrainingImageConfig(0.4, false, false);
		File file = new File(IMAGE_DIR+"9c5h2.png");
		images = imagesBuilder.buildFrom(file, imageConfig);
	}

	@Test
	public void test() {
		assertEquals("ch", figureRecognizer.recognize(images).getResultAsString());
	}

}
