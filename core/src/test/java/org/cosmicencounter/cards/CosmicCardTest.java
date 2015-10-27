package org.cosmicencounter.cards;

import org.cosmicencounter.game.GameColor;
import org.cosmicencounter.game.GamePhase;
import org.cosmicencounter.game.Player;
import org.cosmicencounter.game.PlayerStatus;
import org.junit.Assert;
import org.junit.Test;

public class CosmicCardTest {
    CosmicCard attackCard = new AttackCard(0);
    CosmicCard morphCard = new MorphCard();
    CosmicCard negotiateCard = new NegotiateCard();
    CosmicCard reinforcementCard = new ReinforcementCard(0);

    @Test
    public void testGetDescription() {
        Assert.assertNotNull(attackCard.getDescription());
        Assert.assertNotNull(morphCard.getDescription());
        Assert.assertNotNull(negotiateCard.getDescription());
        Assert.assertNotNull(reinforcementCard.getDescription());
    }

    @Test
    public void testGetPlayableStates() {
        Assert.assertEquals(EncounterCard.ENCOUNTER_CARD_PLAYER_STATUSES, attackCard.getPlayableStates());
        Assert.assertEquals(EncounterCard.ENCOUNTER_CARD_PLAYER_STATUSES, morphCard.getPlayableStates());
        Assert.assertEquals(EncounterCard.ENCOUNTER_CARD_PLAYER_STATUSES, negotiateCard.getPlayableStates());
        Assert.assertEquals(ReinforcementCard.REINFORCEMENT_CARD_PLAYER_STATUSES, reinforcementCard.getPlayableStates());
    }

    @Test
    public void testCanUseCard() {
        Player offensivePlayer = new Player("offense", GameColor.BLACK);
        offensivePlayer.setPlayerStatus(PlayerStatus.OFFENSIVE);
        Player allyPlayer = new Player("ally", GameColor.BLUE);
        allyPlayer.setPlayerStatus(PlayerStatus.ALLY);
        Player byStandardPlayer = new Player("by standard", GameColor.GREEN);
        byStandardPlayer.setPlayerStatus(PlayerStatus.BYSTANDER);

        Assert.assertFalse(attackCard.canUseCard(offensivePlayer, GamePhase.DESTINY));
        Assert.assertFalse(attackCard.canUseCard(allyPlayer, GamePhase.PLANNING));
        Assert.assertTrue(attackCard.canUseCard(offensivePlayer, GamePhase.PLANNING));

        Assert.assertFalse(morphCard.canUseCard(offensivePlayer, GamePhase.DESTINY));
        Assert.assertFalse(morphCard.canUseCard(allyPlayer, GamePhase.PLANNING));
        Assert.assertTrue(morphCard.canUseCard(offensivePlayer, GamePhase.PLANNING));

        Assert.assertFalse(negotiateCard.canUseCard(offensivePlayer, GamePhase.DESTINY));
        Assert.assertFalse(negotiateCard.canUseCard(allyPlayer, GamePhase.PLANNING));
        Assert.assertTrue(negotiateCard.canUseCard(offensivePlayer, GamePhase.PLANNING));

        Assert.assertFalse(reinforcementCard.canUseCard(offensivePlayer, GamePhase.DESTINY));
        Assert.assertFalse(reinforcementCard.canUseCard(byStandardPlayer, GamePhase.REVEAL));
        Assert.assertTrue(reinforcementCard.canUseCard(offensivePlayer, GamePhase.REVEAL));
        Assert.assertTrue(reinforcementCard.canUseCard(allyPlayer, GamePhase.REVEAL));
    }

    @Test
    public void testToString() {
        Assert.assertNotNull(attackCard.toString());
        Assert.assertNotNull(morphCard.toString());
        Assert.assertNotNull(negotiateCard.toString());
        Assert.assertNotNull(reinforcementCard.toString());
    }
}
