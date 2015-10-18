package org.cosmicencounter.game;
import org.cosmicencounter.exceptions.InvalidMoveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Planet extends ShipLocation {
    public static final int MAX_SHIPS = 4;
    public static final Logger LOG = LoggerFactory.getLogger(Planet.class);

    private Player _player;

    /**
     * Create a Planet with 4 ships for the given player
     * @param player The player who owns the planet
     * @param name The name of this planet
     */
    public Planet(Player player, String name) {
        super(name);
        _player = player;
        try {
            addShips(player, MAX_SHIPS);
        } catch (InvalidMoveException e) {
            LOG.error("Critical error: Adding max ships should never result in an invalid move exception!");
        }
    }

    /**
     * Create a Planet with the specified number of ships
     * @param player The player who owns the planet
     * @param ships The number of ships for the planet
     * @param name The name of this planet
     * @throws InvalidMoveException If the ships are greater than the MAX_SHIPS
     */
    public Planet(Player player, int ships, String name) throws InvalidMoveException {
        super(name);
        _player = player;
        addShips(player, ships);
    }

    /**
     * Determines if a player can send reinforcements to a Planet
     * @param player The player who wants to send reinforcements
     * @return True if they are on the planet and have less ships than the max
     */
    @Override
    public boolean canSendShips(Player player) {
        return hasShips(player) && numberOfShips(player) < MAX_SHIPS;
    }

    /**
     * Add the specified number of ships for the player on the planet
     * @param player The player landing on the planet
     * @param ships The number of ships to land
     * @throws InvalidMoveException If the total number of ships to be moved would exceed the max number
     * of ships allowed on a planet
     */
    @Override
    public void addShips(Player player, int ships) throws InvalidMoveException {
        if (numberOfShips(player) + ships > MAX_SHIPS) {
            throw new InvalidMoveException(toString() + " cannot have more than 4 ships.");
        }
        super.addShips(player, ships);
    }

    @Override
    public String toString() {
        return "planet " + super.toString();
    }

    /**
     * Determines if any enemies are currently on the planet
     * @return True if at least one other player has at least one ship on the planet
     */
    public boolean isEnemyOnPlanet() {
        List<Player> players = whoIsHere();
        for (Player player : players) {
            if (!player.equals(_player) && numberOfShips(_player) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a given player can attack this planet
     * @param player The player who would attack
     * @return If this is one of the players home planets it checks if there are any enemies on the planet.
     * Other wise it checks that the player is not already on the planet
     */
    public boolean canAttackPlanet(Player player) {
        if (_player.equals(player)) {
            return isEnemyOnPlanet();
        } else {
            return !hasShips(player);
        }
    }
}
