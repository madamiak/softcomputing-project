package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.GamePhaseProcessor;

import java.awt.image.BufferedImage;

/**
 * Created by RaV on 12.10.14.
 */
public class GamePhaseProcessorImpl implements GamePhaseProcessor {
    private static final int CARD_OFFSET = 64;
    @Override
    public int processGamePhase(BufferedImage image) {
        int cardCount = coutTableCards(image);
        switch (cardCount){
            case 0:
                return 0;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            default:
                throw new UnexpectedTableCardNumberException(cardCount);
        }
    }

    private int coutTableCards(BufferedImage image) {
        int count=0;
        for(int i=0; i<5; i++){
            if(image.getRGB(i*CARD_OFFSET, 0)==-1)count++;
        }
        return count;
    }

    private static class UnexpectedTableCardNumberException extends RuntimeException{
        private UnexpectedTableCardNumberException(int number) {
            super(String.format("Found %s cards on table. Onlu 0, 3, 4 and 5 cards are supported.", number));
        }
    }
}
