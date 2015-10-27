package org.cosmicencounter.cards;

import org.cosmicencounter.exceptions.SetupError;
import org.cosmicencounter.game.GameColor;
import org.cosmicencounter.game.GamePhase;

import java.util.ArrayList;
import java.util.List;

public class DestinyCard implements Card {
    public static final String WILD_DESCRIPTION = "Have an encounter with any other player of your choice in his " +
            "or her home system.";
    public static final String SPECIAL_MOST_CARDS_DESCRIPTION = "Have an encounter with the player who has the " +
            "most cards in hand (other than you). In the event of a tie, break the tie to your left.\nThe encounter " +
            "takes place in the player's home system.";
    public static final String SPECIAL_MOST_COLINIES_DESCRIPTION = "Have an encounter with the player who has the " +
            "most foreign colonies (other than you). In the event of a tie, break the tie to your left.\nThe " +
            "encounter takes place in the player's home system.";
    public static final List<GamePhase> DESTINY_CARD_GAME_PHASE = new ArrayList<>();
    static {
        DESTINY_CARD_GAME_PHASE.add(GamePhase.DESTINY);
    }

    private DestinyCardType _type;
    private GameColor _color;
    private String _description;
    private boolean _hazard;

    /**
     * Create a new Destiny Card without a {@link GameColor}. DestinyCardType cannot be Player
     * @param type The type of Destiny Card this is
     * @throws SetupError If type is a Player
     */
    public DestinyCard(DestinyCardType type) throws SetupError {
        this(type, null, false);
    }

    /**
     * Create a new Destiny Card with a {@link GameColor} and sets hazard to false. If DestinyCardType is player
     * color must be set.
     * @param type The type of Destiny Card this is
     * @param color The color of the player
     * @throws SetupError If type is a Player
     */
    public DestinyCard(DestinyCardType type, GameColor color) throws SetupError {
        this(type, color, false);
    }

    /**
     * Create a new Destiny Card with a {@link GameColor}. If DestinyCardType is player color must be set
     * @param type The type of Destiny Card this is
     * @param color The color of the player
     * @param hazard If this card is a hazard
     * @throws SetupError If type is a Player
     */
    public DestinyCard(DestinyCardType type, GameColor color, boolean hazard) throws SetupError {
        if (type.equals(DestinyCardType.PLAYER) && color == null) {
            throw new SetupError("A Game Color has to be specified when creating a Player Destiny card.");
        }
        _type = type;
        _description = getDescription(type, color);
        _color = color;
        _hazard = hazard;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    /**
     * Gets the correct description for the card based on the type of destiny card
     * @param type The type of destiny card
     * @param color The player color
     * @return The correct description for the card
     * @throws SetupError
     */
    private String getDescription(DestinyCardType type, GameColor color) throws SetupError {
        switch (type) {
            case WILD:
                return WILD_DESCRIPTION;
            case SPECIAL_MOST_CARDS:
                return SPECIAL_MOST_CARDS_DESCRIPTION;
            case SPECIAL_MOST_COLONIES:
                return SPECIAL_MOST_COLINIES_DESCRIPTION;
            case PLAYER:
                return buildDescription(color);
        }
        throw new SetupError("Unknown destiny card type " + type);
    }

    /**
     * @return Always returns a list containing just the Destiny phase
     */
    @Override
    public List<GamePhase> getPlayableGamePhases() {
        return DESTINY_CARD_GAME_PHASE;
    }

    /**
     * @return The color of the player that was drawn from the destiny deck. Null if type is not Player
     */
    public GameColor getColor() {
        return _color;
    }

    /**
     * @return The type of Destiny card
     */
    public DestinyCardType getDestinyCardType() {
        return _type;
    }

    /**
     * @return Whether or not this is card is a hazard
     */
    public boolean isHazard() {
        return _hazard;
    }

    /**
     * Build the description for a player destiny card
     * @param color The color of the player
     * @return The description for this card
     */
    private static String buildDescription(GameColor color) {
        return "Have an encounter with the " + color.toString() + " player in his or her home system.\n However, if " +
                "you are the " + color.toString() + " player, either:\n  A) Have an encounter with any other player" +
                "in your home system or,\n  B) Discard this card and draw again.";
    }
}
