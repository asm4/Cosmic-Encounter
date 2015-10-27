package org.cosmicencounter.cards;

import org.junit.Assert;
import org.junit.Test;

public class ReinforcementCardTest {
    @Test
    public void testGetValue() {
        int value = 1;
        ReinforcementCard card = new ReinforcementCard(value);
        Assert.assertEquals(value, card.getValue());
    }
}
