package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.border.BorderRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.chips.ChipsRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.dealers.DealerRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.fold.FoldButtonRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.suits.SuitRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips.TableChipsRecognizer;

public class PokerBotRecognizerFactoryTest {
	private RecognizerFactory testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new PokerBotRecognizerFactory();
	}

	@Test
	public void shouldCreateProperRecognizerInstance()
			throws InstantiationException {
		assertRecognizerType(FigureRecognizer.class, "figure");
		assertRecognizerType(SuitRecognizer.class, "suit");
		assertRecognizerType(DealerRecognizer.class, "dealer");
		assertRecognizerType(ChipsRecognizer.class, "chips");
		assertRecognizerType(TableChipsRecognizer.class, "tablechips");
		assertRecognizerType(BorderRecognizer.class, "border");
		assertRecognizerType(FoldButtonRecognizer.class, "fold");
	}

	@Test(expected = InstantiationException.class)
	public void shouldThrowExceptionWhenGivenTypeIsNotCorrect()
			throws Exception {
		testObject.create("");
	}

	private void assertRecognizerType(Class<?> expectedClass, String type)
			throws InstantiationException {
		assertEquals(expectedClass, testObject.create(type).getClass());
	}

}
