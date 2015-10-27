package org.cosmicencounter.cards;

import org.cosmicencounter.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Test;

public class HandTest {
    CosmicCard card1 = new NegotiateCard();
    CosmicCard card2 = new ReinforcementCard(1);

    @Test
    public void testAddCard() {
        Hand hand = new Hand();
        Assert.assertTrue(hand.addCard(card1));
    }

    @Test
    public void testAddCards() {
        Hand hand = new Hand();
        Assert.assertTrue(hand.addCards(card1, card2));
    }

    @Test
    public void testGetCard() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.addCard(card1);
        Assert.assertEquals(card1, hand.getCard(0));
    }

    @Test(expected = InvalidMoveException.class)
    public void testGetCardInvalidIndex() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.getCard(-1);
    }

    @Test
    public void testGetDescription() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.addCard(card1);
        Assert.assertEquals(card1.getDescription(), hand.getDescription(0));
    }

    @Test(expected = InvalidMoveException.class)
    public void testGetDescriptionInvalidIndex() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.getDescription(-1);
    }

    @Test
    public void testGetNumberOfCards() {
        Hand hand = new Hand();
        Assert.assertEquals(0, hand.getNumberOfCards());
        hand.addCard(card1);
        Assert.assertEquals(1, hand.getNumberOfCards());
    }

    @Test
    public void testHasEncounterCard() throws InvalidMoveException {
        Hand hand = new Hand();
        Assert.assertFalse(hand.hasEncounterCard());
        hand.addCard(card2);
        Assert.assertFalse(hand.hasEncounterCard());
        hand.addCard(card1);
        Assert.assertTrue(hand.hasEncounterCard());
        hand.removeCard(card1);
        Assert.assertFalse(hand.hasEncounterCard());
    }

    @Test
    public void testRemoveCard() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.addCard(card1);
        Assert.assertTrue(hand.removeCard(card1));
    }

    @Test(expected = InvalidMoveException.class)
    public void testRemoveCardNotInHand() throws InvalidMoveException {
        Hand hand = new Hand();
        hand.removeCard(card1);
    }

    @Test
    public void testToString() {
        Hand hand = new Hand();
        Assert.assertNotNull(hand.toString());
        hand.addCard(card1);
        Assert.assertNotNull(hand.toString());
    }
}
