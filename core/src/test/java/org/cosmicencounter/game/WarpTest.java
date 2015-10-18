package org.cosmicencounter.game;

import org.junit.Assert;
import org.junit.Test;

public class WarpTest {
    @Test
    public void testCanSendShips() {
        Warp warp = new Warp();
        Assert.assertFalse(warp.canSendShips(new Player("Player 1", GameColor.BLACK)));
    }

    @Test
    public void testName() {
        Warp warp = new Warp();
        Assert.assertEquals(Warp.NAME, warp.getName());
    }
}
