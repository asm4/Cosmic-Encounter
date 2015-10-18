package org.cosmicencounter.game;

import org.cosmicencounter.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class PlanetTest {
    private Player _planetOwner = new Player("Player 1", GameColor.BLACK);
    private Player _enemyPlayer = new Player("Player 2", GameColor.BLUE);

    @Test
    public void testHasShips() {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");

        Assert.assertTrue(planet.hasShips(_planetOwner));
        Assert.assertFalse(planet.hasShips(_enemyPlayer));
    }

    @Test
    public void testWhoIsHere() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");

        Assert.assertEquals(Arrays.asList(new Player[]{_planetOwner}), planet.whoIsHere());
        planet.addShips(_enemyPlayer, 1);

        Assert.assertTrue(planet.whoIsHere().contains(_planetOwner));
        Assert.assertTrue(planet.whoIsHere().contains(_enemyPlayer));

        planet.removeShips(_planetOwner);
        planet.removeShips(_enemyPlayer);

        Assert.assertEquals(Collections.emptyList(), planet.whoIsHere());
    }

    @Test
    public void testNumberOfShips() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");

        Assert.assertEquals(Planet.MAX_SHIPS, planet.numberOfShips(_planetOwner));
        Assert.assertEquals(0, planet.numberOfShips(_enemyPlayer));

        int addedShips = 1;
        planet.addShips(_enemyPlayer, addedShips);
        Assert.assertEquals(addedShips, planet.numberOfShips(_enemyPlayer));
    }

    @Test
    public void testTotalNumberOfShips() throws InvalidMoveException {
        int startShips = 1;
        int addedShips = 1;
        Planet planet = new Planet(_planetOwner, startShips, _planetOwner.getPlayerColor() + "1");

        Assert.assertEquals(startShips, planet.totalNumberOfShips());
        planet.addShips(_enemyPlayer, addedShips);
        Assert.assertEquals(startShips + addedShips, planet.totalNumberOfShips());
        planet.removeShips(_planetOwner);
        Assert.assertEquals(addedShips, planet.totalNumberOfShips());
    }

    @Test(expected = InvalidMoveException.class)
    public void testRemoveShipsNotOnPlanet() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        planet.removeShips(_enemyPlayer);
    }

    @Test(expected = InvalidMoveException.class)
    public void testRemoveShipsNotOnPlanetSpecifiedAmount() throws InvalidMoveException {
        int startShips = 1;
        int removeShips = 2;
        Planet planet = new Planet(_planetOwner, startShips, _planetOwner.getPlayerColor() + "1");
        planet.removeShips(_enemyPlayer, removeShips);
    }


    @Test(expected = InvalidMoveException.class)
    public void testRemoveShipsTooManyShips() throws InvalidMoveException {
        int startShips = 1;
        int removeShips = 2;
        Planet planet = new Planet(_planetOwner, startShips, _planetOwner.getPlayerColor() + "1");
        planet.removeShips(_planetOwner, removeShips);
    }

    @Test
    public void testRemoveShipsSpecifiedAmount() throws InvalidMoveException {
        int startShips = 2;
        int removeShips = 1;
        Planet planet = new Planet(_planetOwner, startShips, _planetOwner.getPlayerColor() + "1");
        planet.removeShips(_planetOwner, removeShips);

        Assert.assertEquals(startShips - removeShips, planet.numberOfShips(_planetOwner));
    }

    @Test(expected = InvalidMoveException.class)
    public void testAddShipsTooManyShips() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        planet.addShips(_enemyPlayer, Planet.MAX_SHIPS + 1);
    }

    @Test
    public void testAddShips() throws InvalidMoveException {
        int startShips = 1;
        int addedShips = 1;
        Planet planet = new Planet(_planetOwner, startShips, _planetOwner.getPlayerColor() + "1");
        planet.addShips(_planetOwner, addedShips);

        Assert.assertEquals(startShips + addedShips, planet.numberOfShips(_planetOwner));
    }

    @Test
    public void testName() {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        Assert.assertNotNull(planet.getName());
    }

    @Test
    public void testCanSendShips() throws InvalidMoveException{
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        Assert.assertFalse(planet.canSendShips(_planetOwner));
        Assert.assertFalse(planet.canSendShips(_enemyPlayer));

        planet.removeShips(_planetOwner, 1);
        Assert.assertTrue(planet.canSendShips(_planetOwner));

        planet.addShips(_enemyPlayer, 1);
        Assert.assertTrue(planet.canSendShips(_enemyPlayer));
    }

    @Test
    public void testIsEnemyOnPlanet() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        Assert.assertFalse(planet.isEnemyOnPlanet());

        planet.addShips(_enemyPlayer, 1);
        Assert.assertTrue(planet.isEnemyOnPlanet());
    }

    @Test
    public void testCanAttackPlanet() throws InvalidMoveException {
        Planet planet = new Planet(_planetOwner, _planetOwner.getPlayerColor() + "1");
        Assert.assertTrue(planet.canAttackPlanet(_enemyPlayer));
        Assert.assertFalse(planet.canAttackPlanet(_planetOwner));

        planet.addShips(_enemyPlayer, 1);
        Assert.assertTrue(planet.canAttackPlanet(_planetOwner));
        Assert.assertFalse(planet.canAttackPlanet(_enemyPlayer));
    }
}
