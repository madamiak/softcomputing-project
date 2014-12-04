package pl.wroc.pwr.student.softcomputing.ui.util;

import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Engine;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.ExpertSystem;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Fold;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaV on 23.02.14.
 */
public class ReasoningDelegate {
    public static String reason(Table table){
        List<Integer> totalChips = new ArrayList<Integer>();
        List<Integer> chipsAtTable = new ArrayList<Integer>();
        List<String> borders = new ArrayList<String>();
        borders.add(null);
        for(int i=0; i<6; i++){
            totalChips.add(table.getTotalChipsOf(i));
            chipsAtTable.add(table.getChipsOnTableOf(i));
            if(i>0)
                borders.add(table.getBorderOf(i).toString());
        }

        Converter converter = new Converter(table.getFoldButtonStatus()== Fold.ACTIVE,table.getDealerPosition(),table.getFirstCard().toString(),table.getSecondCard().toString(),totalChips,chipsAtTable, borders);
        ConvertedData convertedData = converter.convertData();
        Engine engine = new ExpertSystem().getBasicStrategyEngine(convertedData);
        String answer="";
        if(engine.performBackwardChaining(new Fact("Raise")).isPositive()){
            answer="Player should RAISE in this situation!";
        }else{
            answer="Player should FOLD in this situation!";
        }
        String engineOutput = engine.getOutPut();
        return convertedData+"\n\n"+engineOutput+"\n"+answer;
    }
}
