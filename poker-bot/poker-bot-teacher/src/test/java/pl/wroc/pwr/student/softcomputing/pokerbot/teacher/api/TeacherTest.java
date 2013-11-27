package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.PokerbotTeacher;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TableElement;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TrainingUnit;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TrainingUnitReader;

@RunWith(MockitoJUnitRunner.class)
public class TeacherTest {

	// 2 0000
	// 3 0001
	// 4 0010
	// 5 0011
	// 6 0100
	// 7 0101
	// 8 0110
	// 9 0111
	// T 1000
	// J 1001
	// Q 1010
	// K 1011
	// A 1100

	private Teacher testObject;

	private List<TrainingUnit> images;

	private String outputFileName = "target\\test.nnet";

	@Before
	public void setUp() throws Exception {
		images = TrainingUnitReader
				.readTrainingUnitsFromDir("src/test/resources/FiguresAndSuits");
		testObject = new PokerbotTeacher(images, outputFileName);
	}

	@Test
	public void test() {
		testObject.teach(TransferFunctionType.SIGMOID, TableElement.SUIT,
				false, false, 0.3);
	}
	
	@Test
	public void testName() throws Exception {
		String filename = "9dTh.png";
		testObject.recognize("src/test/resources/" + filename, TableElement.FIGURE, "target/figures.nnet", true, false, 0.3 );
	}
}
