package pl.wroc.pwr.student.softcomputing.ui.util;

import java.io.File;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFacade;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.NeuralNetworkConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

/**
 * Created by RaV on 11.01.14.
 */
public class TeachingDelegate {
    public static void teach(List<File> listOfImages, String teacherType, String outputFile, TeachingParams teachingParams) throws InstantiationException {
        ImageConfig imageConfig = new TrainingImageConfig(teachingParams.getScale(), teachingParams.isBlackAndWhite(), teachingParams.isGrayscale());
        LearningConfig learningConfig = new NeuralNetworkConfig(teachingParams.getMaxIterations(), teachingParams.getLearningRate(), teachingParams.getErrorRate(), teachingParams.getMomentum(), outputFile);
        if(teacherType.equals("figure")) {
        	TeacherFacade.getInstance().teachFigures(listOfImages, imageConfig, learningConfig);
        } else if(teacherType.equals("suit")) {
        	TeacherFacade.getInstance().teachSuits(listOfImages, imageConfig, learningConfig);
        } else if(teacherType.equals("dealer")) {
        	TeacherFacade.getInstance().teachDealerPositions(listOfImages, imageConfig, learningConfig);
        }
    }
}
