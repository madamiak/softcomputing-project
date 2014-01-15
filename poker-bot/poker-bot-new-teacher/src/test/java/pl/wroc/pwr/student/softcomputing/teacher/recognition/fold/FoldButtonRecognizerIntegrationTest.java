package pl.wroc.pwr.student.softcomputing.teacher.recognition.fold;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.FoldParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.FoldParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.PokerBotRecognizerFactory;

public class FoldButtonRecognizerIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Recognizer foldButtonRecognizer;
	private Images images;
	
	@Before
	public void setUp() throws Exception {
		RecognizerFactory factory = new PokerBotRecognizerFactory();
		foldButtonRecognizer = factory.create("fold");
		FoldParser foldParser = new FoldParserImpl();
		foldButtonRecognizer = new FoldButtonRecognizer(foldParser );
		TableParser tableParser = new TableParserImpl();
		ImagesBuilder<BufferedImage, File> imagesBuilder = new FoldButtonImagesBuilder(tableParser );
		File file = new File(IMAGE_DIR+"3s2d5.png");
		images = imagesBuilder.buildFrom(file , null);
	}

	@Test
	public void test() {
		System.out.println(foldButtonRecognizer.recognize(images).getResultAsString());
	}

}
