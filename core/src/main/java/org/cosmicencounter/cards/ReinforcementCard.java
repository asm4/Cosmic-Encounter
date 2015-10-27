package org.cosmicencounter.cards;

import org.cosmicencounter.game.GamePhase;
import org.cosmicencounter.game.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * A Reinforcement Card can be played after encounter cards have been played to increase a sides overall total score.
 * Reinforcement Cards can be played by both main player and allies
 */
public class ReinforcementCard extends CosmicCard {
    private static final String REINFORCEMENT_CARD_DESCRIPTION = "Adds to either side's total. Player after encounter" +
            "cards are revealed.";
    public static final List<GamePhase> REINFORCEMENT_CARD_GAME_PHASES = new ArrayList<>();
    public static final List<PlayerStatus> REINFORCEMENT_CARD_PLAYER_STATUSES = new ArrayList<>();
    static {
        REINFORCEMENT_CARD_GAME_PHASES.add(GamePhase.REVEAL);
        REINFORCEMENT_CARD_PLAYER_STATUSES.add(PlayerStatus.OFFENSIVE);
        REINFORCEMENT_CARD_PLAYER_STATUSES.add(PlayerStatus.DEFENSIVE);
        REINFORCEMENT_CARD_PLAYER_STATUSES.add(PlayerStatus.ALLY);
    }
    private int _value;

    /**
     * Create a new Reinforcement Card with the given value
     */
    public ReinforcementCard(int value) {
        super(REINFORCEMENT_CARD_DESCRIPTION);
        _value = value;
    }

    /**
     * @return The value of the reinforcement
     */
    public int getValue() {
        return _value;
    }

    @Override
    public List<PlayerStatus> getPlayableStates() {
        return REINFORCEMENT_CARD_PLAYER_STATUSES;
    }

    @Override
    public List<GamePhase> getPlayableGamePhases() {
        return REINFORCEMENT_CARD_GAME_PHASES;
    }

    @Override
    public String toString() {
        return "Reinforcement: " + _value;
    }
}
