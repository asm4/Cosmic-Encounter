package org.cosmicencounter.cards;

public class MorphCard extends EncounterCard {
    private static final String MORPH_DESCRIPTION = "Duplicates opponent's encounter card when revealed.";

    /**
     * Creates a new Morph card that morphs into the encounter card of the player's opponent
     */
    public MorphCard() {
        super(MORPH_DESCRIPTION);
    }

    @Override
    public String toString() {
        return "Morph: M";
    }
}
