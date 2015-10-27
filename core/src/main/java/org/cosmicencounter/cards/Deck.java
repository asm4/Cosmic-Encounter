package org.cosmicencounter.cards;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;

/**
 * A deck of cards.
 * @param <T> The type of cards this deck contains
 */
public class Deck<T extends Card> {
    private Stack<T> _cards;

    /**
     * Create a new deck of cards
     */
    public Deck() {
        _cards = new Stack<>();
    }

    /**
     * Shuffle the deck of cards
     */
    public void shuffle() {
        for (int i = 0; i < (Math.random() * 100) + 1; i++) {
            Collections.shuffle(_cards);
        }
    }

    /**
     * @return The card on the top of the deck
     */
    public T draw() {
        return _cards.pop();
    }

    /**
     * Look at the top card without taking it
     * @return The card on the top of the deck
     */
    public T peek() {
        return _cards.peek();
    }

    /**
     * Put a card on the top of the pile
     * @param card The card
     * @return The card that was added
     */
    public T add(T card) {
        return _cards.push(card);
    }

    /**
     * @return If the deck is out of cards
     */
    public boolean isEmpty() {
        return _cards.empty();
    }

    /**
     * Flip the deck over so the top card is now on the bottom and the bottom card is now on top
     */
    public void flipDeck() {
        Collections.reverse(_cards);
    }
}
