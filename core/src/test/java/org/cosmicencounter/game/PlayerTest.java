package org.cosmicencounter.game;

import org.cosmicencounter.cards.Hand;
import org.cosmicencounter.cards.NegotiateCard;
import org.cosmicencounter.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlayerTest {
    private Player _enemyPlayer = new Player("Player 2", GameColor.BLUE);
    private Planet _enemyPlanet = new Planet(_enemyPlayer, _enemyPlayer.getPlayerColor() + "1");

    @Test
    public void testGetName() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertNotNull(player1.getName());
    }

    @Test
    public void testGetPlayerColor() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertNotNull(player1.getPlayerColor());
    }

    @Test
    public void testAddColony() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertTrue(player1.addColony(_enemyPlanet));
        Assert.assertFalse(player1.addColony(_enemyPlanet));
    }

    @Test
    public void testRemoveColony() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertFalse(player1.removeColony(_enemyPlanet));

        player1.addColony(_enemyPlanet);
        Assert.assertTrue(player1.removeColony(_enemyPlanet));
    }

    @Test
    public void testNumberOfForeignColonies() throws InvalidMoveException {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertEquals(0, player1.numberOfForeignColonies());

        player1.addColony(_enemyPlanet);
        Assert.assertEquals(0, player1.numberOfForeignColonies());

        _enemyPlanet.addShips(player1, 1);
        Assert.assertEquals(0, player1.numberOfForeignColonies());

        player1.addColony(_enemyPlanet);
        Assert.assertEquals(1, player1.numberOfForeignColonies());
    }

    @Test
    public void testNumberOfHomePlanets() throws InvalidMoveException {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertEquals(Player.DEFAULT_NUMBER_OF_PLANETS, player1.numberOfHomePlanets());

        List<Planet> homePlanets = player1.getHomePlanets();
        for (Planet planet : homePlanets) {
            planet.removeShips(player1);
        }

        Assert.assertEquals(0, player1.numberOfHomePlanets());
    }

    @Test
    public void testGetHomePlanets() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertNotNull(player1.getHomePlanets());
        Assert.assertEquals(Player.DEFAULT_NUMBER_OF_PLANETS, player1.getHomePlanets().size());
    }

    @Test
    public void testGetHand() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Assert.assertNotNull(player1.getHand());
    }

    @Test
    public void testSetHand() {
        Player player1 = new Player("Player 1", GameColor.BLACK);
        Hand newHand = new Hand();
        newHand.addCard(new NegotiateCard());

        Assert.assertNotEquals(newHand, player1.getHand());
        player1.setHand(newHand);
        Assert.assertEquals(newHand, player1.getHand());
    }
}
