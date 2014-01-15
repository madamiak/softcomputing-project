package pl.wroc.pwr.student.softcomputing.teacher.api;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

public class TeacherFacadeIntegrationTest {
	private static final String RESOURCES = ".\\src\\test\\resources\\";
	private static final String IMAGE_DIR = "FiguresAndSuits\\";
	private TeacherFacade facade;

	@Before
	public void setUp() throws Exception {
		facade = TeacherFacade.getInstance();
	}

	@Test
	public void test() {
		File imageFile = new File(RESOURCES + IMAGE_DIR+"9dTh1.png");
		String figureNNFile = RESOURCES + "f.nnet";
		ImageConfig figureImageConfig = new TrainingImageConfig(0.3, true, false);
		String suitNNFile = RESOURCES + "s.nnet";
		ImageConfig suitImageConfig = new TrainingImageConfig(0.4, false, false);
		String dealerNNFile = RESOURCES + "d.nnet";
		ImageConfig dealerImageConfig = new TrainingImageConfig(0.2, true, false);
		Table table = facade.recognizeTable(imageFile, figureNNFile, figureImageConfig, suitNNFile, suitImageConfig, dealerNNFile, dealerImageConfig);
		System.out.println(table.report());
	}

}
