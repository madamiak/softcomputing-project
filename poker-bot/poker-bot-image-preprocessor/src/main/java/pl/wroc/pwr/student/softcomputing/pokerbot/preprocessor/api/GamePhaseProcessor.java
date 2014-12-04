package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * Created by RaV on 12.10.14.
 */
public interface GamePhaseProcessor {
    /**
     * @param image
     * 	Image of player/opponent chips on table cropped by TableParserImpl.
     * @return
     * 	Int value of chips number from image.
     */
    int processGamePhase(BufferedImage image);
}
