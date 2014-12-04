package pl.wroc.pwr.student.softcomputing.teacher.recognition.gamephase;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.GamePhaseProcessor;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.ListResult;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePhaseRecognizer implements Recognizer {

    private final GamePhaseProcessor gamePhaseProcessor;

    public GamePhaseRecognizer(GamePhaseProcessor gamePhaseProcessor) {
        this.gamePhaseProcessor = gamePhaseProcessor;
    }

    @Override
    public Result recognize(Images images) {
        List<Integer> gamePhase = new ArrayList<>();
        for (Object object : images.list()) {
            Image<BufferedImage> image = (Image<BufferedImage>) object;
            gamePhase.add(gamePhaseProcessor.processGamePhase(image.getData()));
        }
        return new ListResult(gamePhase);
    }

    @Override
    public void setNeuralNetwork(String file) {
        throw new UnsupportedOperationException();
    }

}
