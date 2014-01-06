package pl.wroc.pwr.student.softcomputing.teacher.training;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.training.dealers.DealerTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.figures.FigureTeacher;
import pl.wroc.pwr.student.softcomputing.teacher.training.suits.SuitTeacher;

public class PokerBotTeacherFactory implements TeacherFactory {
	private final Map<String, Class<?>> teachers = new HashMap<>();
	private final Map<String, Class<?>> outputConverters = new HashMap<>();

	public PokerBotTeacherFactory() {
		teachers.put("figure", FigureTeacher.class);
		teachers.put("suit", SuitTeacher.class);
		teachers.put("dealer", DealerTeacher.class);
	}

	public Teacher create(String type) throws InstantiationException {
		Teacher teacher = null;
		try {
			teacher = createProperInstanceOf(type);
		} catch (Exception e) {
			throw new InstantiationException("Unable to create responder \""
					+ type + "\"");
		}
		return teacher;
	}

	private Teacher createProperInstanceOf(String key)
			throws Exception {
		if (key == null || !teachers.containsKey(key)) 
			throw new InstantiationException("No responder for \"" + key + "\"");
		Class<?> teacherClass = teachers.get(key);
		Constructor<?> constructor = teacherClass.getConstructor(ImageConverter.class);
		return (Teacher) constructor.newInstance(new ImageToArrayConverter());
	}
}
