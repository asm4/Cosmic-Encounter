package org.cosmicencounter.game;

import org.cosmicencounter.exceptions.InvalidMoveException;

import java.util.*;

/**
 * A location that can have ships on it
 */
public abstract class ShipLocation {
    private Map<Player, Integer> _ships;
    private String _name;

    /**
     * @param name The name of this location
     */
    public ShipLocation(String name) {
        _ships = new HashMap<>();
        _name = name;
    }

    /**
     * Determines if the given player has any ships located at this location
     * @param player The player in question
     * @return True if at least one ship of this player is located here
     */
    public boolean hasShips(Player player) {
        return _ships.containsKey(player) && _ships.get(player) > 0;
    }

    /**
     * Determine if a player can send ships to this location
     * @param player The player
     * @return True if the player can send ships to this location
     */
    public abstract boolean canSendShips(Player player);

    /**
     * @return A list containing all the players at this location
     */
    public List<Player> whoIsHere() {
        return new ArrayList<>(_ships.keySet());
    }

    /**
     * @param player The player
     * @return The number of ships this player has at this location
     */
    public int numberOfShips(Player player) {
        if (hasShips(player)) {
            return _ships.get(player);
        }
        return 0;
    }

    /**
     * @return The total number of all ships at this location
     */
    public int totalNumberOfShips() {
        int total = 0;
        for (Map.Entry<Player, Integer> ship : _ships.entrySet()) {
            total += ship.getValue();
        }
        return total;
    }

    /**
     * Remove all the ships of this player
     * @param player The player to remove
     * @return The number of ships that was removed
     * @throws InvalidMoveException If the player was not on this location
     */
    public int removeShips(Player player) throws InvalidMoveException {
        if (!hasShips(player)) {
            throw new InvalidMoveException(player.toString() + " has no ships on " + toString() + ".");
        }
        return _ships.remove(player);
    }

    /**
     * Remove the specified number of ships from this location
     * @param player The player who's ships are being removed
     * @param ships The ships to be removed
     * @return The number of ships that was removed (Always same value as ships)
     * @throws InvalidMoveException If the player is not on this location
     */
    public int removeShips(Player player, int ships) throws InvalidMoveException {
        if (!hasShips(player)) {
            throw new InvalidMoveException(player.toString() + " has no ships on " + toString() + ".");
        }
        int currentShips = _ships.get(player);
        if (currentShips < ships) {
            throw new InvalidMoveException(player.toString() + " only has " + currentShips + " ships on " + toString() +
                    " cannot remove " + ships + " ships.");
        }
        currentShips -= ships;
        _ships.remove(player);
        if (currentShips != 0) {
            _ships.put(player, currentShips);
        }
        return ships;
    }

    /**
     * Add the specified number of ships for the player to this location
     * @param player The player landing on the planet
     * @param ships The number of ships to land
     * @throws InvalidMoveException
     */
    public void addShips(Player player, int ships) throws InvalidMoveException {
        if (_ships.containsKey(player)) {
            ships += _ships.get(player);
            _ships.remove(player);
        }
        _ships.put(player, ships);
    }

    /**
     * @return The name of the location
     */
    public String getName() {
        return _name;
    }

    @Override
    public String toString() {
        return _name;
    }
}
