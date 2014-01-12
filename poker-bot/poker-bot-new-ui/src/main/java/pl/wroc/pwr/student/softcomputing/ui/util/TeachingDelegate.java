package pl.wroc.pwr.student.softcomputing.ui.util;

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
import pl.wroc.pwr.student.softcomputing.teacher.training.figures.FigureImagesBuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 11.01.14.
 */
public class TeachingDelegate {
    public static void teach(List<File> listOfImages, String teacherType, String outputFile, TeachingParams teachingParams) throws InstantiationException {
        TeacherFactory teacherFactory = new PokerBotTeacherFactory();
        Teacher figureTeacher = teacherFactory.create(teacherType);
        ImageProcessor imageProcessor = new ImageProcessorImpl();
        TableParser tableParser = new TableParserImpl();
        ImagesBuilder<BufferedImage, List<File>> imagesBuilder = new FigureImagesBuilder(tableParser , imageProcessor);
        ImageConfig imageConfig = new TrainingImageConfig(teachingParams.getScale(), teachingParams.isBlackAndWhite(), teachingParams.isGrayscale());
        Images images = imagesBuilder.buildFrom(listOfImages, imageConfig);
        LearningConfig learningConfig = new NeuralNetworkConfig(teachingParams.getMaxIterations(), teachingParams.getLearningRate(), teachingParams.getErrorRate(), teachingParams.getMomentum(), outputFile);
        figureTeacher.setLearningConfig(learningConfig);
        figureTeacher.teach(images);
    }
}
