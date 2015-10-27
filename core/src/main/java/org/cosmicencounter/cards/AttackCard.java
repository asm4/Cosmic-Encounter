package org.cosmicencounter.cards;

public class AttackCard extends EncounterCard {
    private static final String ATTACK_CARD_DESCRIPTION = "Opposed by attack:\n" +
            "Higher total wins (ships + card) wins.\n" +
            "Opposed by Negotiate:\n" +
            "Wins but opponent collects compensation.";
    private int _attackValue;

    /**
     * Creates an attack card with the given attack value
     * @param attackValue The attack value of the card
     */
    public AttackCard(int attackValue) {
        super(ATTACK_CARD_DESCRIPTION);
        _attackValue = attackValue;
    }

    /**
     * @return The attack value of this card
     */
    public int getValue() {
        return _attackValue;
    }

    @Override
    public String toString() {
        return "Attack: " + _attackValue;
    }
}
