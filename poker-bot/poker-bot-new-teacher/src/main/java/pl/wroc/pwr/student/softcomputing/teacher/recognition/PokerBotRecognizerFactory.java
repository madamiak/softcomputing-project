package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureRecognizer;

public class PokerBotRecognizerFactory implements RecognizerFactory {
	private final Map<String, Class<?>> recognizers = new HashMap<>();
	
	public PokerBotRecognizerFactory() {
		recognizers.put("figure", FigureRecognizer.class);
	}

	@Override
	public Recognizer create(String type) throws InstantiationException {
		Recognizer recognizer = null;
		try {
			recognizer = createProperInstanceOf(type);
		} catch (Exception e) {
			throw new InstantiationException("Unable to create responder \""
					+ type + "\"");
		}
		return recognizer;
	}

	private Recognizer createProperInstanceOf(String key)
			throws Exception {
		if (key == null || !recognizers.containsKey(key)) 
			throw new InstantiationException("No responder for \"" + key + "\"");
		Class<?> teacherClass = recognizers.get(key);
		Constructor<?> constructor = teacherClass.getConstructor(ImageConverter.class);
		return (Recognizer) constructor.newInstance(new ImageToArrayConverter());
	}

}
