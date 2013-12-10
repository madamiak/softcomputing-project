package pl.wroc.pwr.student.softcomputing.ui.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.neuroph.util.TransferFunctionType;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageToArrayConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.TableParserImpl;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.InputBuilder;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Request;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto.TeachingUnitDTO;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto.TeachingUnitsDTO;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TeachingInputBuilder;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TeachingInteractor;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TeachingRequest;

public class MainController {

	private final MainDisplay mainDisplay;

	public MainController(MainDisplay mainDisplay) {
		this.mainDisplay = mainDisplay;
	}

	public void onDirectoryChosen(File file) {
		mainDisplay.getInputDirectory().setValue(file.getAbsolutePath());
		mainDisplay.getFilesList().clearContent();

		for (File each : file.listFiles()) {
			if (isFileImageExtension(each)) {
				mainDisplay.getFilesList().addRow(each);
			}
		}
		mainDisplay.refreshList();
	}

	public void onFileChosen(File file) {
		mainDisplay.getOutputFile().setValue(file.getAbsolutePath());
	}

	private boolean isFileImageExtension(File each) {
		return each.isFile()
				&& each.getName().toLowerCase()
						.substring(each.getName().lastIndexOf("."))
						.equals(".png");
	}

	public void onImagePreview(File file) {
		mainDisplay.getImage().set(file);
		mainDisplay.refreshCanvas();
	}

	public void onTeachExecuted() {
		mainDisplay.getProgress().setValue(10);
		List<File> imageFiles = mainDisplay.getFilesList().getAll();
		TeachingUnitsDTO teachingUnits = prepareTeachingUnits(imageFiles);

		List<Integer> neuronsInLayers = new ArrayList<>();
		int inputSize = teachingUnits.list().get(0).image().length;
		int outputSize = mainDisplay.getTeachingType().isSuitSelected() ? 2 : 4;
		neuronsInLayers.add(inputSize);
		neuronsInLayers.add((int) Math.sqrt(Math.pow(inputSize, 2) + Math.pow(outputSize, 2)));
		neuronsInLayers.add(outputSize);
		Request request = new TeachingRequest(mainDisplay.getOutputFile()
				.getValue(), neuronsInLayers , TransferFunctionType.SIGMOID);
		InputBuilder builder = new TeachingInputBuilder(mainDisplay
				.getTeachingType().isFigureSelected(), mainDisplay
				.getTeachingType().isSuitSelected());
		Teacher t = new TeachingInteractor(request);
		t.teach(builder.prepareInput(teachingUnits));
		mainDisplay.getProgress().setValue(100);
	}

	private TeachingUnitsDTO prepareTeachingUnits(List<File> imageFiles) {
		TeachingUnitsDTO teachingUnits = new TeachingUnitsDTO();
		TableParser parser = new TableParserImpl();
		ImageConverter converter = new ImageToArrayConverter();
		ImageProcessor processor = new ImageProcessorImpl();
		for (File file : imageFiles) {
			parser.loadTable(file.getAbsolutePath());
			if (mainDisplay.getTeachingType().isFigureSelected()) {
				processFigures(teachingUnits, parser, converter, processor,
						file);
			} else {
				processSuits(teachingUnits, parser, converter, processor, file);
			}
		}
		return teachingUnits;
	}

	private void processSuits(TeachingUnitsDTO teachingUnits,
			TableParser parser, ImageConverter converter,
			ImageProcessor processor, File file) {
		BufferedImage firstSuit = parser.parseFirstSuit();
		firstSuit = convert(processor, firstSuit);
		teachingUnits.add(new TeachingUnitDTO(converter.convert(firstSuit),
				file.getName().substring(1, 2)));

		BufferedImage secondSuit = parser.parseSecondSuit();
		secondSuit = convert(processor, secondSuit);
		teachingUnits.add(new TeachingUnitDTO(converter.convert(secondSuit),
				file.getName().substring(3)));
	}

	private void processFigures(TeachingUnitsDTO teachingUnits,
			TableParser parser, ImageConverter converter,
			ImageProcessor processor, File file) {
		BufferedImage firstCard = parser.parseFirstCard();
		firstCard = convert(processor, firstCard);
		teachingUnits.add(new TeachingUnitDTO(converter.convert(firstCard),
				file.getName().substring(0, 1)));

		BufferedImage secondCard = parser.parseSecondCard();
		secondCard = convert(processor, secondCard);
		teachingUnits.add(new TeachingUnitDTO(converter.convert(secondCard),
				file.getName().substring(2, 3)));
	}

	private BufferedImage convert(ImageProcessor processor,
			BufferedImage firstSuit) {
		if (mainDisplay.getTeachingParameters().isScaled()) {
			firstSuit = processor.scale(firstSuit, mainDisplay
					.getTeachingParameters().getScale());
		}
		if (mainDisplay.getTeachingParameters().isGrayedScale()) {
			firstSuit = processor.convertToGrayscale(firstSuit);
		}
		if (mainDisplay.getTeachingParameters().isBlackAndWhite()) {
			firstSuit = processor.convertToBlackAndWhite(firstSuit);
		}
		return firstSuit;
	}
}
