package pl.wroc.pwr.student.softcomputing.pokerbot.expert;

import org.junit.Test;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.Fact;
import pl.wroc.pwr.student.softcomputing.pokerbot.expertSystem.KnowledgeBase;

import static org.junit.Assert.assertTrue;

/**
 * Created by RaV on 21.01.14.
 */
public class KnowledgeBaseTest {
    @Test
    public void whenFactInKnowledgeBaseThenHasFactShouldReturnTrue() throws Exception {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        Fact fact = new Fact("Fact");
        knowledgeBase.addFact(fact);
        assertTrue(knowledgeBase.hasFact(fact));
        assertTrue(knowledgeBase.hasFact("Fact"));

    }


}
