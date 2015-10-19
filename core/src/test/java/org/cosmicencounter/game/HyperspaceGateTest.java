package org.cosmicencounter.game;

import org.cosmicencounter.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Test;

public class HyperspaceGateTest {
    private Player _attacker = new Player("Attacker", GameColor.BLACK);
    private Player _defender = new Player("Defender", GameColor.BLUE);
    private Player _offensiveAlly = new Player("Offensive Ally", GameColor.GREEN);
    private Player _defensiveAlly = new Player("Defensive Ally", GameColor.ORANGE);
    private Planet _target = _defender.getHomePlanets().get(0);

    @Test
    public void testName() {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertEquals(HyperspaceGate.NAME, hyperspaceGate.getName());
    }

    @Test
    public void testCanSendShips() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertTrue(hyperspaceGate.canSendShips(_attacker));
        Assert.assertFalse(hyperspaceGate.canSendShips(_defender));
        Assert.assertFalse(hyperspaceGate.canSendShips(_offensiveAlly));
        Assert.assertFalse(hyperspaceGate.canSendShips(_defensiveAlly));

        hyperspaceGate.addOffensiveAlly(_offensiveAlly);
        Assert.assertTrue(hyperspaceGate.canSendShips(_offensiveAlly));

        hyperspaceGate.addDefensiveAlly(_defensiveAlly);
        Assert.assertTrue(hyperspaceGate.canSendShips(_defensiveAlly));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddShipsCannotSend() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addShips(_defender, 1);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddShipsTooManySent() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addShips(_attacker, HyperspaceGate.MAX_SHIPS + 1);
    }

    @Test
    public void testAddShips() throws InvalidMoveException {
        int shipsAdded = 1;
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addShips(_attacker, shipsAdded);

        Assert.assertEquals(shipsAdded, hyperspaceGate.numberOfShips(_attacker));
    }

    @Test
    public void testGetPossibleTargets() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertEquals(Player.DEFAULT_NUMBER_OF_PLANETS, hyperspaceGate.getPossibleTargets().size());

        for (Planet planet : _defender.getHomePlanets()) {
            planet.addShips(_attacker, 1);
        }

        Assert.assertEquals(0, hyperspaceGate.getPossibleTargets().size());
    }

    @Test(expected = InvalidMoveException.class)
    public void testSetTargetAlreadySet() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.setTarget(_target);
        hyperspaceGate.setTarget(_target);
    }

    @Test(expected = InvalidMoveException.class)
    public void testSetTargetInvalidTarget() throws InvalidMoveException {
        Planet invalidTarget = new Planet(_attacker, "Invalid Planet");
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.setTarget(invalidTarget);
    }

    @Test
    public void testSetTarget() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertNull(hyperspaceGate.getTarget());

        hyperspaceGate.setTarget(_target);
        Assert.assertEquals(_target, hyperspaceGate.getTarget());
    }

    @Test
    public void testIsReady() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertFalse(hyperspaceGate.isReady());

        hyperspaceGate.addShips(_attacker, 1);
        Assert.assertFalse(hyperspaceGate.isReady());

        hyperspaceGate.removeShips(_attacker);
        hyperspaceGate.setTarget(_target);
        Assert.assertFalse(hyperspaceGate.isReady());

        hyperspaceGate.addShips(_attacker, 1);
        Assert.assertTrue(hyperspaceGate.isReady());
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddOffensiveAllyDefender() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addOffensiveAlly(_defender);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddOffensiveAllyAlreadyDefensiveAlly() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addDefensiveAlly(_defensiveAlly);
        hyperspaceGate.addOffensiveAlly(_defensiveAlly);
    }

    @Test
    public void testAddOffensiveAlly() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertFalse(hyperspaceGate.canSendShips(_offensiveAlly));

        Assert.assertTrue(hyperspaceGate.addOffensiveAlly(_offensiveAlly));
        Assert.assertTrue(hyperspaceGate.canSendShips(_offensiveAlly));
        Assert.assertFalse(hyperspaceGate.addOffensiveAlly(_offensiveAlly));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddDefensiveAllyDefender() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addDefensiveAlly(_defender);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddDefensiveAllyAlreadyOffensiveAlly() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        hyperspaceGate.addOffensiveAlly(_offensiveAlly);
        hyperspaceGate.addDefensiveAlly(_offensiveAlly);
    }

    @Test
    public void testAddDefensiveAlly() throws InvalidMoveException {
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertFalse(hyperspaceGate.canSendShips(_defensiveAlly));

        Assert.assertTrue(hyperspaceGate.addDefensiveAlly(_defensiveAlly));
        Assert.assertTrue(hyperspaceGate.canSendShips(_defensiveAlly));
        Assert.assertFalse(hyperspaceGate.addDefensiveAlly(_defensiveAlly));
    }

    @Test
    public void testGetOffensiveBonus() throws InvalidMoveException {
        int attackerShips = 1;
        int allyShips = 1;
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertEquals(0, hyperspaceGate.getOffensiveBonus());

        hyperspaceGate.addShips(_attacker, attackerShips);
        Assert.assertEquals(attackerShips, hyperspaceGate.getOffensiveBonus());

        hyperspaceGate.addOffensiveAlly(_offensiveAlly);
        hyperspaceGate.addShips(_offensiveAlly, allyShips);

        Assert.assertEquals(attackerShips + allyShips, hyperspaceGate.getOffensiveBonus());
    }

    @Test
    public void testGetDefensiveBonus() throws InvalidMoveException {
        int allyShips = 1;
        HyperspaceGate hyperspaceGate = new HyperspaceGate(_attacker, _defender);
        Assert.assertEquals(0, hyperspaceGate.getDefensiveBonus());

        hyperspaceGate.setTarget(_target);
        Assert.assertEquals(Planet.MAX_SHIPS, hyperspaceGate.getDefensiveBonus());

        hyperspaceGate.addDefensiveAlly(_defensiveAlly);
        hyperspaceGate.addShips(_defensiveAlly, allyShips);

        Assert.assertEquals(Planet.MAX_SHIPS + allyShips, hyperspaceGate.getDefensiveBonus());
    }
}
