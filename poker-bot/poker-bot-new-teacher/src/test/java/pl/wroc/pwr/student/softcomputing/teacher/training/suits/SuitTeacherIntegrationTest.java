package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.NeuralNetworkConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.PokerBotTeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

public class SuitTeacherIntegrationTest {
	private static final String IMAGE_DIR = ".\\src\\test\\resources\\FiguresAndSuits\\";
	private Teacher figureTeacher;
	private Images<BufferedImage> images;
	private LearningConfig learningConfig;

	@Before
	public void setUp() throws Exception {
		TeacherFactory teacherFactory = new PokerBotTeacherFactory();
		figureTeacher = teacherFactory.create("suit");
		ImageProcessor imageProcessor = new ImageProcessorImpl();
		TableParser tableParser = new TableParserImpl();
		ImagesBuilder<BufferedImage, List<File>> imagesBuilder = new SuitImagesBuilder(tableParser , imageProcessor);
		List<File> listOfImages = new ArrayList<>();
		File imageDir = new File(IMAGE_DIR);
        for (final File fileEntry : imageDir.listFiles()) {
			if(fileEntry.getName().contains("png"))
				listOfImages.add(fileEntry);
	    }
		ImageConfig imageConfig = new TrainingImageConfig(0.5, true, false);
		images = imagesBuilder.buildFrom(listOfImages, imageConfig);
		learningConfig = new NeuralNetworkConfig(10, 0.5, 0.5, 0.5, "target/test.nnet");
	}

	@Test
	public void test() {
		figureTeacher.setLearningConfig(learningConfig);
		figureTeacher.teach(images);
	}

}
