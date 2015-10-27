package org.cosmicencounter.cards;

import org.cosmicencounter.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<CosmicCard> _cards = new ArrayList<>();

    /**
     * Adds a given CosmicCard to the Hand.
     * @param card The card to add
     * @return True if the card was added successfully
     */
    public boolean addCard(CosmicCard card) {
        return _cards.add(card);
    }

    /**
     * Adds an array of cards to the hand.
     * @param cards The array of cards to be added
     * @return True if the hand changes
     */
    public boolean addCards(CosmicCard... cards) {
        return Collections.addAll(_cards, cards);
    }

    /**
     * Returns a CosmicCard at the specified index.
     * @param index The index of the card in the hand
     * @return The CosmicCard at the specified index.
     * @throws InvalidMoveException If index is out of bounds
     */
    public CosmicCard getCard(int index) throws InvalidMoveException {
        try {
            return _cards.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidMoveException("There is no card at index " + index, e);
        }
    }

    /**
     * Returns the description of the card at the specified index
     * @param index The index of the card in the hand
     * @return The description of the card at the specified index
     * @throws InvalidMoveException If index is out of bounds
     */
    public String getDescription(int index) throws InvalidMoveException {
        return getCard(index).getDescription();
    }

    /**
     * @return The number of cards in this hand
     */
    public int getNumberOfCards() {
        return _cards.size();
    }

    /**
     * @return Whether or not this hand contains any encounter cards
     */
    public boolean hasEncounterCard() {
        for (CosmicCard card : _cards) {
            if (card instanceof EncounterCard) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the given card from the hand
     * @param card The card to be removed
     * @return True if the card is successfully removed
     * @throws InvalidMoveException If the card is not in the hand
     */
    public boolean removeCard(CosmicCard card) throws InvalidMoveException {
        if (!_cards.contains(card)) {
            throw new InvalidMoveException(card + " is not in this hand");
        }
        return _cards.remove(card);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hand:\n  Id\tCard\n");
        for (int i = 0; i < _cards.size(); i++) {
            sb.append("  ").append(i).append("\t\t").append(_cards.get(i)).append("\n");
        }
        return sb.toString();
    }
}
