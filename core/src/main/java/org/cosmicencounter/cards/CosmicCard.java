package org.cosmicencounter.cards;

import org.cosmicencounter.game.GamePhase;
import org.cosmicencounter.game.Player;
import org.cosmicencounter.game.PlayerStatus;

import java.util.List;

/**
 * The basic cards in the main deck and in your hand. There are three different kinds.
 * <ul>
 *     <li>{@link ReinforcementCard}</li>
 *     <li>{@link ArtifactCard}</li>
 *     <li>{@link EncounterCard}</li>
 *     <ul>
 *         <li>{@link AttackCard}</li>
 *         <li>{@link NegotiateCard}</li>
 *         <li>{@link MorphCard}</li>
 *     </ul>
 * </ul>
 */
public abstract class CosmicCard implements Card {
    private String _description;

    /**
     * A Cosmic Card with the given description
     * @param description The description on the card
     */
    public CosmicCard(String description) {
        _description = description;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    /**
     * @return A list of states that the card can be used in. i.e. [offense, defense, ally]
     */
    public abstract List<PlayerStatus> getPlayableStates();

    /**
     * Checks whether the given player can use this card at this time
     * @param player The player
     * @param currentPhase The current phase of play
     * @return True if the player can use this card. i.e. If the card can only be used by a main player and player
     * is currently the offense and it is the reveal phase.
     */
    public boolean canUseCard(Player player, GamePhase currentPhase) {
        return getPlayableStates().contains(player.getPlayerStatus()) && getPlayableGamePhases().contains(currentPhase);
    }
}
