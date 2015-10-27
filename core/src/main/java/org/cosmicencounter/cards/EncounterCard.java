package org.cosmicencounter.cards;

import org.cosmicencounter.game.GamePhase;
import org.cosmicencounter.game.PlayerStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class EncounterCard extends CosmicCard {
    public static final List<GamePhase> ENCOUNTER_CARD_GAME_PHASES = new ArrayList<>();
    public static final List<PlayerStatus> ENCOUNTER_CARD_PLAYER_STATUSES = new ArrayList<>();
    static {
        ENCOUNTER_CARD_GAME_PHASES.add(GamePhase.PLANNING);
        ENCOUNTER_CARD_PLAYER_STATUSES.add(PlayerStatus.OFFENSIVE);
        ENCOUNTER_CARD_PLAYER_STATUSES.add(PlayerStatus.DEFENSIVE);
    }

    /**
     * A Cosmic Card with the given description
     *
     * @param description The description on the card
     */
    public EncounterCard(String description) {
        super(description);
    }

    /**
     * An Encounter card can only be used as either the offense or the defense
     * @return A List containing the offense and defense
     */
    @Override
    public List<PlayerStatus> getPlayableStates() {
        return ENCOUNTER_CARD_PLAYER_STATUSES;
    }

    /**
     * Encounter cards can only have be used during the reveal.
     * @return A List containing only the reveal game phase
     */
    @Override
    public List<GamePhase> getPlayableGamePhases() {
        return ENCOUNTER_CARD_GAME_PHASES;
    }
}
