package pl.wroc.pwr.student.softcomputing.ui.util;

import pl.wroc.pwr.student.softcomputing.teacher.api.TeacherFacade;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

import java.io.File;
import java.util.List;

/**
 * Created by RaV on 17.01.14.
 */
public class RecognizingDelegate {
    public static Table recognize(File selectedFile, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource) {

        TeacherFacade facade = TeacherFacade.getInstance();

        String figureNNFile = figuresDatasource.getFile().toString();
        double scale = TeachingParams.getScaleFromFilename(figureNNFile);
        boolean bnw = TeachingParams.getBNWFromFilename(figureNNFile);
        boolean grayscale = TeachingParams.getGrayscaleFromFilename(figureNNFile);
        ImageConfig figureImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        String suitNNFile = suitsDatasource.getFile().toString();
        scale = TeachingParams.getScaleFromFilename(suitNNFile);
        bnw = TeachingParams.getBNWFromFilename(suitNNFile);
        grayscale = TeachingParams.getGrayscaleFromFilename(suitNNFile);
        ImageConfig suitImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        String dealerNNFile = dealerDatasource.getFile().toString();
        scale = TeachingParams.getScaleFromFilename(dealerNNFile);
        bnw = TeachingParams.getBNWFromFilename(dealerNNFile);
        grayscale = TeachingParams.getGrayscaleFromFilename(dealerNNFile);
        ImageConfig dealerImageConfig = new TrainingImageConfig(scale, bnw, grayscale);


        Table table = facade.recognizeTable(selectedFile, figureNNFile, figureImageConfig, suitNNFile, suitImageConfig, dealerNNFile, dealerImageConfig);
        System.out.println(table.smallReport());

        return table;
    }

	public static String recognizeAll(List<File> allFiles,
			FileHolder figuresDatasource, FileHolder suitsDatasource,
			FileHolder dealerDatasource) {
		StringBuilder sb = new StringBuilder();
        int count=0;
        int correct=0;
        long millis=System.currentTimeMillis();
		for (File file : allFiles) {
            Table t = recognize(file, figuresDatasource, suitsDatasource, dealerDatasource);
            count+=2;

            String firstFigure = String.valueOf(t.getFirstCard().toString().split(" ")[0].charAt(0)).toUpperCase();
            String secondFigure = String.valueOf(t.getSecondCard().toString().split(" ")[0].charAt(0)).toUpperCase();

            String firstCard = (firstFigure.equals("1") ? "T" : firstFigure)+String.valueOf(t.getFirstCard().toString().split(" ")[2].charAt(0)).toLowerCase();
            String secondCard = (secondFigure.equals("1") ? "T" : secondFigure)+String.valueOf(t.getSecondCard().toString().split(" ")[2].charAt(0)).toLowerCase();

            String firstRead=file.getName().substring(0,2);
            String secondRead=file.getName().substring(2,4);

            if(firstCard.equals(firstRead))correct++;
            else sb.append("\nFile: ").append(file.getName()).append(" 1st card, file: ").append(firstRead).append(", table: ").append(firstCard);
            if(secondCard.equals(secondRead))correct++;
            else sb.append("\nFile: ").append(file.getName()).append(" 2nd card, file: ").append(secondRead).append(", table: ").append(secondCard);

            System.out.println("First card: "+firstCard+", second card: "+secondCard+", first read: "+firstRead+", second read: "+secondRead);
            //sb.append(t);
		}

        return String.format("Correctly recognized %d out of %d which is %d%% in average of %d ms per table.", correct, count, (correct*100)/count, (System.currentTimeMillis()-millis)/(count/2))+sb.toString();
	}
}
