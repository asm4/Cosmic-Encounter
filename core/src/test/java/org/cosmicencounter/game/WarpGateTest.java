package org.cosmicencounter.game;

import org.cosmicencounter.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Test;

public class WarpGateTest {
    private Player _attacker = new Player("Attacker", GameColor.BLACK);
    private Player _defender = new Player("Defender", GameColor.BLUE);
    private Player _offensiveAlly = new Player("Offensive Ally", GameColor.GREEN);
    private Player _defensiveAlly = new Player("Defensive Ally", GameColor.ORANGE);
    private Planet _target = _defender.getHomePlanets().get(0);

    public static void main(String[] args) throws InvalidMoveException {
        WarpGateTest warpGateTest = new WarpGateTest();
        warpGateTest.testGetDefensiveBonus();
    }

    @Test
    public void testName() {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertEquals(WarpGate.NAME, warpGate.getName());
    }

    @Test
    public void testCanSendShips() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertTrue(warpGate.canSendShips(_attacker));
        Assert.assertFalse(warpGate.canSendShips(_defender));
        Assert.assertFalse(warpGate.canSendShips(_offensiveAlly));
        Assert.assertFalse(warpGate.canSendShips(_defensiveAlly));

        warpGate.addOffensiveAlly(_offensiveAlly);
        Assert.assertTrue(warpGate.canSendShips(_offensiveAlly));

        warpGate.addDefensiveAlly(_defensiveAlly);
        Assert.assertTrue(warpGate.canSendShips(_defensiveAlly));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddShipsCannotSend() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addShips(_defender, 1);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddShipsTooManySent() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addShips(_attacker, WarpGate.MAX_SHIPS + 1);
    }

    @Test
    public void testAddShips() throws InvalidMoveException {
        int shipsAdded = 1;
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addShips(_attacker, shipsAdded);

        Assert.assertEquals(shipsAdded, warpGate.numberOfShips(_attacker));
    }

    @Test
    public void testGetPossibleTargets() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertEquals(Player.DEFAULT_NUMBER_OF_PLANETS, warpGate.getPossibleTargets().size());

        for (Planet planet : _defender.getHomePlanets()) {
            planet.addShips(_attacker, 1);
        }

        Assert.assertEquals(0, warpGate.getPossibleTargets().size());
    }

    @Test(expected = InvalidMoveException.class)
    public void testSetTargetAlreadySet() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.setTarget(_target);
        warpGate.setTarget(_target);
    }

    @Test(expected = InvalidMoveException.class)
    public void testSetTargetInvalidTarget() throws InvalidMoveException {
        Planet invalidTarget = new Planet(_attacker, "Invalid Planet");
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.setTarget(invalidTarget);
    }

    @Test
    public void testSetTarget() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertNull(warpGate.getTarget());

        warpGate.setTarget(_target);
        Assert.assertEquals(_target, warpGate.getTarget());
    }

    @Test
    public void testIsReady() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertFalse(warpGate.isReady());

        warpGate.addShips(_attacker, 1);
        Assert.assertFalse(warpGate.isReady());

        warpGate.removeShips(_attacker);
        warpGate.setTarget(_target);
        Assert.assertFalse(warpGate.isReady());

        warpGate.addShips(_attacker, 1);
        Assert.assertTrue(warpGate.isReady());
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddOffensiveAllyDefender() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addOffensiveAlly(_defender);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddOffensiveAllyAlreadyDefensiveAlly() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addDefensiveAlly(_defensiveAlly);
        warpGate.addOffensiveAlly(_defensiveAlly);
    }

    @Test
    public void testAddOffensiveAlly() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertFalse(warpGate.canSendShips(_offensiveAlly));

        Assert.assertTrue(warpGate.addOffensiveAlly(_offensiveAlly));
        Assert.assertTrue(warpGate.canSendShips(_offensiveAlly));
        Assert.assertFalse(warpGate.addOffensiveAlly(_offensiveAlly));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddDefensiveAllyDefender() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addDefensiveAlly(_defender);
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddDefensiveAllyAlreadyOffensiveAlly() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        warpGate.addOffensiveAlly(_offensiveAlly);
        warpGate.addDefensiveAlly(_offensiveAlly);
    }

    @Test
    public void testAddDefensiveAlly() throws InvalidMoveException {
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertFalse(warpGate.canSendShips(_defensiveAlly));

        Assert.assertTrue(warpGate.addDefensiveAlly(_defensiveAlly));
        Assert.assertTrue(warpGate.canSendShips(_defensiveAlly));
        Assert.assertFalse(warpGate.addDefensiveAlly(_defensiveAlly));
    }

    @Test
    public void testGetOffensiveBonus() throws InvalidMoveException {
        int attackerShips = 1;
        int allyShips = 1;
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertEquals(0, warpGate.getOffensiveBonus());

        warpGate.addShips(_attacker, attackerShips);
        Assert.assertEquals(attackerShips, warpGate.getOffensiveBonus());

        warpGate.addOffensiveAlly(_offensiveAlly);
        warpGate.addShips(_offensiveAlly, allyShips);

        Assert.assertEquals(attackerShips + allyShips, warpGate.getOffensiveBonus());
    }

    @Test
    public void testGetDefensiveBonus() throws InvalidMoveException {
        int allyShips = 1;
        WarpGate warpGate = new WarpGate(_attacker, _defender);
        Assert.assertEquals(0, warpGate.getDefensiveBonus());

        warpGate.setTarget(_target);
        Assert.assertEquals(Planet.MAX_SHIPS, warpGate.getDefensiveBonus());

        warpGate.addDefensiveAlly(_defensiveAlly);
        warpGate.addShips(_defensiveAlly, allyShips);

        Assert.assertEquals(Planet.MAX_SHIPS + allyShips, warpGate.getDefensiveBonus());
    }
}
