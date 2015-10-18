package org.cosmicencounter.game;

/**
 * The Warp is where all lost ships go.
 */
public class Warp extends ShipLocation {
    public static final String NAME = "Warp";

    /**
     * Create a new Warp
     */
    public Warp() {
        super(NAME);
    }

    /**
     * A player can never just send their ships to the warp they have to die
     * @param player The player
     * @return Always returns false
     */
    @Override
    public boolean canSendShips(Player player) {
        return false;
    }
}
