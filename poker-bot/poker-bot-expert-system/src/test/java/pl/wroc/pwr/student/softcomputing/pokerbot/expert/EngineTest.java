package pl.wroc.pwr.student.softcomputing.pokerbot.expert;

import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.ConvertedData;
import pl.wroc.pwr.student.softcomputing.pokerbot.converter.Converter;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by RaV on 21.01.14.
 */
public class EngineTest {
    @Test
    public void whenHypothesisInKnowledgeBaseThenBackwardChainingShouldReturnTrue() throws Exception {
        Engine engine = new Engine();
        Fact hypothesis = new Fact("Raise");
        Fact record = new Fact("Raise");
        engine.addToKnowledgeBase(record);
        boolean isProven = engine.performBackwardChaining(hypothesis);
        System.out.println(engine.getOutPut());
        assertTrue(isProven);
    }
    @Test
    public void whenHypothesisIsTrueThenBackwardChainingShouldReturnTrue() throws Exception {

        List<Fact> conditions;

        Engine engine = new Engine();
        Fact hypothesis = new Fact("Raise");
        Fact highCard = new Fact("High card A");
        Fact lowCard = new Fact("Low card Q");
        Fact position = new Fact("Position 5");
        engine.addToKnowledgeBase(highCard);
        engine.addToKnowledgeBase(lowCard);
        engine.addToKnowledgeBase(position);

        Fact groupAFact = new Fact("Group A");
        Fact highCardFact = new Fact("High card A");
        Fact lowCardFact = new Fact("Low card Q");
        Antecedent groupAAntecedent = new Antecedent();
        groupAAntecedent.addFact(highCardFact);
        groupAAntecedent.addFact(lowCardFact);
        Rule groupARule = new Rule(groupAFact,groupAAntecedent);
        engine.addRule(groupARule);


        Fact positionFact = new Fact("Position 5");
        Fact groupAFact2 = new Fact("Group A");
        Antecedent smallBlindStrongHandAntecedent = new Antecedent();
        smallBlindStrongHandAntecedent.addFact(positionFact);
        smallBlindStrongHandAntecedent.addFact(groupAFact2);
        Fact raiseFact = new Fact("Raise");
        Rule raiseSmallBlindGroupAHandRule = new Rule(raiseFact,smallBlindStrongHandAntecedent);
        engine.addRule(raiseSmallBlindGroupAHandRule);
        boolean isProven = engine.performBackwardChaining(hypothesis);
        System.out.println(engine.getOutPut());
        assertTrue(isProven);
    }


}
