package org.cosmicencounter.game;

public enum GameColor {
    BLACK,
    BLUE,
    GREEN,
    ORANGE,
    PURPLE,
    RED,
    WHITE,
    YELLOW;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
