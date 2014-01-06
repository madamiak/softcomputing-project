package pl.wroc.pwr.student.softcomputing.teacher.training;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.training.PokerBotTeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.training.dealers.DealerTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.figures.FigureTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.suits.SuitTeacher;

public class PokerBotTeacherFactoryTest {
	private TeacherFactory testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new PokerBotTeacherFactory();
	}

	@Test
	public void shouldCreateProperTeacherInstance()
			throws InstantiationException {
		assertTeacherType(FigureTeacher.class, "figure");
		assertTeacherType(SuitTeacher.class, "suit");
		assertTeacherType(DealerTeacher.class, "dealer");
	}

	@Test(expected = InstantiationException.class)
	public void shouldThrowExceptionWhenGivenTypeIsNotCorrect()
			throws Exception {
		testObject.create("");
	}

	private void assertTeacherType(Class<?> expectedClass, String type)
			throws InstantiationException {
		Teacher teacher = testObject.create(type);
		assertEquals(expectedClass, teacher.getClass());
	}

}
