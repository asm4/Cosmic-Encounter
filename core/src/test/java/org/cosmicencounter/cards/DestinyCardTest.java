package org.cosmicencounter.cards;

import org.cosmicencounter.exceptions.SetupError;
import org.cosmicencounter.game.GameColor;
import org.junit.Assert;
import org.junit.Test;

public class DestinyCardTest {

    @Test(expected = SetupError.class)
    public void testInvalidConstructor() throws SetupError {
        new DestinyCard(DestinyCardType.PLAYER);
    }

    @Test
    public void testGetDescription() {
        DestinyCard wild = new DestinyCard(DestinyCardType.WILD);
        DestinyCard mostCards = new DestinyCard(DestinyCardType.SPECIAL_MOST_CARDS);
        DestinyCard mostColonies = new DestinyCard(DestinyCardType.SPECIAL_MOST_COLONIES);
        DestinyCard player = new DestinyCard(DestinyCardType.PLAYER, GameColor.BLUE);

        Assert.assertEquals(DestinyCard.WILD_DESCRIPTION, wild.getDescription());
        Assert.assertEquals(DestinyCard.SPECIAL_MOST_CARDS_DESCRIPTION, mostCards.getDescription());
        Assert.assertEquals(DestinyCard.SPECIAL_MOST_COLINIES_DESCRIPTION, mostColonies.getDescription());
        Assert.assertNotNull(player.getDescription());
    }

    @Test
    public void testGetPlayableGamePhases() {
        DestinyCard card = new DestinyCard(DestinyCardType.WILD);

        Assert.assertEquals(DestinyCard.DESTINY_CARD_GAME_PHASE, card.getPlayableGamePhases());
    }

    @Test
    public void testGetColor() {
        DestinyCard wild = new DestinyCard(DestinyCardType.WILD);
        DestinyCard player = new DestinyCard(DestinyCardType.PLAYER, GameColor.BLUE);

        Assert.assertNull(wild.getColor());
        Assert.assertNotNull(player.getColor());
    }

    @Test
    public void testHazard() {
        DestinyCard wild = new DestinyCard(DestinyCardType.WILD);
        DestinyCard player = new DestinyCard(DestinyCardType.PLAYER, GameColor.BLUE);
        DestinyCard playerHazard = new DestinyCard(DestinyCardType.PLAYER, GameColor.BLUE, true);

        Assert.assertFalse(wild.isHazard());
        Assert.assertFalse(player.isHazard());
        Assert.assertTrue(playerHazard.isHazard());
    }

    @Test
    public void testGetDestinyCardType() {
        DestinyCard wild = new DestinyCard(DestinyCardType.WILD);
        DestinyCard mostCards = new DestinyCard(DestinyCardType.SPECIAL_MOST_CARDS);
        DestinyCard mostColonies = new DestinyCard(DestinyCardType.SPECIAL_MOST_COLONIES);
        DestinyCard player = new DestinyCard(DestinyCardType.PLAYER, GameColor.BLUE);

        Assert.assertEquals(DestinyCardType.WILD, wild.getDestinyCardType());
        Assert.assertEquals(DestinyCardType.SPECIAL_MOST_CARDS, mostCards.getDestinyCardType());
        Assert.assertEquals(DestinyCardType.SPECIAL_MOST_COLONIES, mostColonies.getDestinyCardType());
        Assert.assertEquals(DestinyCardType.PLAYER, player.getDestinyCardType());
    }
}
