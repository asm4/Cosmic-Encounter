package org.cosmicencounter.cards;

import org.cosmicencounter.game.GamePhase;

import java.util.List;

/**
 * Base Interface that all cards should implement
 */
public interface Card {
    /**
     * @return The description on the card
     */
    String getDescription();

    /**
     * @return A List of all GamePhases where this card can be played
     */
    List<GamePhase> getPlayableGamePhases();
}
