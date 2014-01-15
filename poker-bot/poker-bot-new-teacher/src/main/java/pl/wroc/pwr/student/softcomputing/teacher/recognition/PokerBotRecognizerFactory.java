package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.BorderParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ChipsParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.border.BorderRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.chips.ChipsRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.dealers.DealerRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.suits.SuitRecognizer;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips.TableChipsRecognizer;

public class PokerBotRecognizerFactory implements RecognizerFactory {
	private final Map<String, Class<?>> recognizers = new HashMap<>();
	
	public PokerBotRecognizerFactory() {
		recognizers.put("figure", FigureRecognizer.class);
		recognizers.put("suit", SuitRecognizer.class);
		recognizers.put("dealer", DealerRecognizer.class);
		recognizers.put("chips", ChipsRecognizer.class);
		recognizers.put("tablechips", TableChipsRecognizer.class);
		recognizers.put("border", BorderRecognizer.class);
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
		Recognizer newInstance = null;
		if (key == null || !recognizers.containsKey(key)) 
			throw new InstantiationException("No responder for \"" + key + "\"");
		Class<?> teacherClass = recognizers.get(key);
		try {
			Constructor<?> constructor = teacherClass.getConstructor(ImageConverter.class);
			newInstance = (Recognizer) constructor.newInstance(new ImageToArrayConverter());
		} catch (NoSuchMethodException e) {
			try {
				Constructor<?> constructor = teacherClass.getConstructor(BorderParser.class);
				newInstance = (Recognizer) constructor.newInstance(new BorderParserImpl());
			} catch (NoSuchMethodException e1) {
				try {
					Constructor<?> constructor = teacherClass.getConstructor(ChipsParser.class, ImageProcessor.class);
					newInstance = (Recognizer) constructor.newInstance(new ChipsParserImpl(), new ImageProcessorImpl());
				} catch (Exception e2) {
					e2.printStackTrace();
					throw(e2);
				}
			}
		}
		return newInstance;
	}

}
