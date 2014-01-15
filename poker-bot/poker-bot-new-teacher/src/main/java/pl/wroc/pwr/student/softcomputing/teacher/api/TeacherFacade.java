package pl.wroc.pwr.student.softcomputing.teacher.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Tables;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.PokerBotRecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.PokerTable;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.border.BorderImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.chips.ChipsImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips.TableChipsImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.training.PokerBotTeacherFactory;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.dealers.DealerImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.training.figures.FigureImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.training.suits.SuitImagesBuilder;

public final class TeacherFacade {
	private final static TeacherFacade instance = new TeacherFacade();
	private TeacherFactory teacherFactory = new PokerBotTeacherFactory();
	private RecognizerFactory recognizerFactory = new PokerBotRecognizerFactory();

	private TeacherFacade() {
	}

	public static TeacherFacade getInstance() {
		return instance;
	}

	public void teachFigures(List<File> imageFiles, ImageConfig imageConfig,
			LearningConfig learningConfig) {
		try {
			Teacher teacher = teacherFactory.create("figure");
			ImagesBuilder<BufferedImage, List<File>> builder = new FigureImagesBuilder(
					new TableParserImpl(), new ImageProcessorImpl());
			Images<BufferedImage> images = builder.buildFrom(imageFiles,
					imageConfig);
			teacher.setLearningConfig(learningConfig);
			teacher.teach(images);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	public void teachSuits(List<File> imageFiles, ImageConfig imageConfig,
			LearningConfig learningConfig) {
		try {
			Teacher teacher = teacherFactory.create("suit");
			ImagesBuilder<BufferedImage, List<File>> builder = new SuitImagesBuilder(
					new TableParserImpl(), new ImageProcessorImpl());
			Images<BufferedImage> images = builder.buildFrom(imageFiles,
					imageConfig);
			teacher.setLearningConfig(learningConfig);
			teacher.teach(images);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	public void teachDealerPositions(List<File> imageFiles,
			ImageConfig imageConfig, LearningConfig learningConfig) {
		try {
			Teacher teacher = teacherFactory.create("dealer");
			ImagesBuilder<BufferedImage, List<File>> builder = new DealerImagesBuilder(
					new TableParserImpl(), new ImageProcessorImpl());
			Images<BufferedImage> images = builder.buildFrom(imageFiles,
					imageConfig);
			teacher.setLearningConfig(learningConfig);
			teacher.teach(images);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	public Table recognizeTable(File imageFile, String figureNNFile, ImageConfig figureImageConfig,
			String suitNNFile, ImageConfig suitImageConfig, String dealerNNFile, ImageConfig dealerImageConfig) {
		try {
			Table table = new PokerTable();
			Images<BufferedImage> images;
			ImagesBuilder<BufferedImage, File> imageBuilder;
			
			Recognizer figureRecognizer = recognizerFactory.create("figure");
			figureRecognizer.setNeuralNetwork(figureNNFile);
			Recognizer suitRecognizer = recognizerFactory.create("suit");
			suitRecognizer.setNeuralNetwork(suitNNFile);
			Recognizer dealerRecognizer = recognizerFactory.create("dealer");
			dealerRecognizer.setNeuralNetwork(dealerNNFile);
			Recognizer chipsRecognizer = recognizerFactory.create("chips");
			Recognizer tableChipsRecognizer = recognizerFactory.create("tablechips");
			Recognizer borderRecognizer = recognizerFactory.create("border");
			
			imageBuilder = new pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureImagesBuilder(new TableParserImpl(), new ImageProcessorImpl());
			images = imageBuilder.buildFrom(imageFile, figureImageConfig);
			Result figures = figureRecognizer.recognize(images);
			
			imageBuilder = new pl.wroc.pwr.student.softcomputing.teacher.recognition.suits.SuitImagesBuilder(new TableParserImpl(), new ImageProcessorImpl());
			images = imageBuilder.buildFrom(imageFile, suitImageConfig);
			Result suits = suitRecognizer.recognize(images);
			
			imageBuilder = new pl.wroc.pwr.student.softcomputing.teacher.recognition.dealers.DealerImagesBuilder(new TableParserImpl(), new ImageProcessorImpl());
			images = imageBuilder.buildFrom(imageFile, dealerImageConfig);
			Result dealerPosition = dealerRecognizer.recognize(images);
			
			table.setFigures(figures);
			table.setSuits(suits);
			table.setDealerPosition(dealerPosition);
			
			imageBuilder = new ChipsImagesBuilder(new TableParserImpl());
			images = imageBuilder.buildFrom(imageFile, new TrainingImageConfig(1.0, true, false));
			Result chips = chipsRecognizer.recognize(images);
			
			imageBuilder = new TableChipsImagesBuilder(new TableParserImpl());
			images = imageBuilder.buildFrom(imageFile, new TrainingImageConfig(1.0, true, false));
			Result tableChips = tableChipsRecognizer.recognize(images);

			imageBuilder = new BorderImagesBuilder(new TableParserImpl());
			images = imageBuilder.buildFrom(imageFile, null);
			Result borders = borderRecognizer.recognize(images);
			
			table.setChips(chips);
			table.setTableChips(tableChips);
			table.setBorders(borders);
			
			return table;
			
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	public Tables recognizeTables(List<File> imageFiles, String figureNNFile,
			String suitNNFile, String dealerNNFile) {
		throw new UnsupportedOperationException();
	}
}
