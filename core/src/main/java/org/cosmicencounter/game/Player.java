package org.cosmicencounter.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    public static int DEFAULT_NUMBER_OF_PLANETS = 5;

    private String _name;
    private GameColor _color;
    private List<Planet> _planets;
    private List<Planet> _colonies;

    /**
     * Create a new player with the default 5 planets
     * @param name The name of the player
     * @param color The color of the player
     */
    public Player(String name, GameColor color) {
        this(name, color, DEFAULT_NUMBER_OF_PLANETS);
    }

    /**
     * Create a new player with the specified number of planets
     * @param name The name of the player
     * @param color The color of the player
     * @param numPlanets The number of home worlds this player has
     */
    public Player(String name, GameColor color, int numPlanets) {
        _name = name;
        _color = color;
        _planets = new ArrayList<>();
        _colonies = new ArrayList<>();
        for(int i = 0; i < numPlanets; i++) {
            _planets.add(new Planet(this, color.toString() + (i + 1)));
        }
    }

    /**
     * @return The name of the player
     */
    public String getName() {
        return _name;
    }

    /**
     * @return The color of the player
     */
    public GameColor getPlayerColor() {
        return _color;
    }

    /**
     * Add a new foreign colony
     * @param planet The planet that is now a colony
     * @return True if the colony was added
     */
    public boolean addColony(Planet planet) {
        if (!_colonies.contains(planet)) {
            return _colonies.add(planet);
        }
        return false;
    }

    /**
     * Remove a colony
     * @param planet The planet that is now no longer a colony
     * @return True if the colony was removed
     */
    public boolean removeColony(Planet planet) {
        return _colonies.remove(planet);
    }

    /**
     * @return The total number of foreign colonies controlled
     */
    public int numberOfForeignColonies() {
        updateColonies();
        return _colonies.size();
    }

    /**
     * @return The total number of home planet that have at least one of this player's ships on it
     */
    public int numberOfHomePlanets() {
        int counter = 0;
        for(Planet home: _planets) {
            if(home.hasShips(this))
                counter++;
        }
        return counter;
    }

    /**
     * @return A List of all this players home planets
     */
    public List<Planet> getHomePlanets() {
        return _planets;
    }

    /**
     * Update number of foreign colonies controlled
     */
    private void updateColonies() {
        for (Iterator<Planet> colonyIterator = _colonies.iterator(); colonyIterator.hasNext();) {
            if (!colonyIterator.next().hasShips(this)) {
                colonyIterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return _name;
    }
}
