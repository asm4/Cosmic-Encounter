package org.cosmicencounter.cards;

import org.junit.Assert;
import org.junit.Test;

public class AttackCardTest {
    @Test
    public void testGetValue() {
        int value = 1;
        AttackCard attackCard = new AttackCard(value);
        Assert.assertEquals(value, attackCard.getValue());
    }
}
