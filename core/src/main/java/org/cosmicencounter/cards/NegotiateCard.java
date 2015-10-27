package org.cosmicencounter.cards;

public class NegotiateCard extends EncounterCard {
    private static final String NEGOTIATE_CARD_DESCRIPTION = "Opposed by Attack:\n" +
            "Loses, but collects compensation.\n" +
            "Opposed by Negotiate:\n" +
            "Players have one minute to make a deal or lose three ships to the warp.";

    /**
     * Creates a new Negotiate card
     */
    public NegotiateCard() {
        super(NEGOTIATE_CARD_DESCRIPTION);
    }

    @Override
    public String toString() {
        return "Negotiate: N";
    }
}
