package org.cosmicencounter.game;

import org.cosmicencounter.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

/**
 * Every encounter happens on the Hyperspace Gate. The encounter is between an offensive player and a defensive player.
 * The offensive player must select a planet from the defenders home world that the offensive player currently has
 * no ships on. An attacker must send at minimum 1 ship to the Hyperspace Gate and the defensive player must defend with
 * the ships that are already on the planet. Both sides may ask for allies.
 */
public class HyperspaceGate extends ShipLocation {
    public static final String NAME = "Hyperspace Gate";
    public static final int MAX_SHIPS = 4;

    private Player _attacker;
    private Player _defender;
    private Planet _target;
    private List<Player> _offensiveAllies;
    private List<Player> _defensiveAllies;

    /**
     * Create a Hyperspace Gate where the battle will take place
     * @param attacker The attacking player
     * @param defender The defending player
     */
    public HyperspaceGate(Player attacker, Player defender) {
        super(NAME);
        _attacker = attacker;
        _defender = defender;
        _offensiveAllies = new ArrayList<>();
        _defensiveAllies = new ArrayList<>();
    }

    /**
     * The attacking player is always allowed to send ships to the Hyperspace Gate but so are any allies
     * @param player The player
     * @return True if the player is the attacking player or an ally of either side
     */
    @Override
    public boolean canSendShips(Player player) {
        return player.equals(_attacker) || _offensiveAllies.contains(player) || _defensiveAllies.contains(player);
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
        if (!canSendShips(player)) {
            throw new InvalidMoveException(player + " cannot send ships to the Hyperspace Gate.");
        }
        if (numberOfShips(player) + ships > MAX_SHIPS) {
            throw new InvalidMoveException(toString() + " cannot have more than 4 ships.");
        }
        super.addShips(player, ships);
    }

    /**
     * @return A List of all the defenders planet's that you are not already on
     */
    public List<Planet> getPossibleTargets() {
        ArrayList<Planet> planets = new ArrayList<>();
        for (Planet planet : _defender.getHomePlanets()) {
            if (!planet.hasShips(_attacker)) {
                planets.add(planet);
            }
        }
        return planets;
    }

    /**
     * Set which planet the attacker is attacking. Whatever ships the defender has on that planet will automatically
     * be moved to the Hyperspace Gate
     * @param planet The planet that is selected to be attacked
     * @throws InvalidMoveException If the target has already been set or if the selected planet is not a valid planet
     * to attack
     */
    public void setTarget(Planet planet) throws InvalidMoveException {
        if (_target != null) {
            throw new InvalidMoveException("Target planet is already set. You are attacking " + _target.toString());
        }
        List<Planet> planets = getPossibleTargets();
        if (!planets.contains(planet)) {
            throw new InvalidMoveException(planet.toString() + " is not a valid planet to attack");
        }
        _target = planet;
        super.addShips(_defender, _target.removeShips(_defender));
    }

    /**
     * @return The target planet that is being attacked
     */
    public Planet getTarget() {
        return _target;
    }

    /**
     * @return True once the target has been set and the attacker as sent at least one ship
     */
    public boolean isReady() {
        return _target != null && numberOfShips(_attacker) > 0;
    }

    /**
     * Adds an offensive ally
     * @param player The player who is allying
     * @return True if the player was added as an ally
     * @throws InvalidMoveException If the other player is already a defensive ally or the defender
     */
    public boolean addOffensiveAlly(Player player) throws InvalidMoveException {
        if (_offensiveAllies.contains(player)) {
            return false;
        }
        if (_defensiveAllies.contains(player)) {
            throw new InvalidMoveException(player.toString() + " is already a defensive ally.");
        }
        if (player.equals(_defender)) {
            throw new InvalidMoveException(player.toString() + " is the defender");
        }
        return _offensiveAllies.add(player);
    }

    /**
     * Adds a defensive ally
     * @param player The player who is allying
     * @return True if the player was added as an ally
     * @throws InvalidMoveException If the other player is already a defensive ally or the defender
     */
    public boolean addDefensiveAlly(Player player) throws InvalidMoveException {
        if (_defensiveAllies.contains(player)) {
            return false;
        }
        if (_offensiveAllies.contains(player)) {
            throw new InvalidMoveException(player.toString() + " is already an offensive ally.");
        }
        if (player.equals(_defender)) {
            throw new InvalidMoveException(player.toString() + " is the defender");
        }
        return _defensiveAllies.add(player);
    }

    /**
     * @return The total bonus for the offensive side
     */
    public int getOffensiveBonus() {
        int total = numberOfShips(_attacker);
        for (Player ally : _offensiveAllies) {
            total += numberOfShips(ally);
        }
        return total;
    }

    /**
     * @return The total bonus for the defensive side
     */
    public int getDefensiveBonus() {
        int total = numberOfShips(_defender);
        for (Player ally : _defensiveAllies) {
            total += numberOfShips(ally);
        }
        return total;
    }
}
