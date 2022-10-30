package com.codingame.game.board;

public final class Village {
    private final int hexA;
    private final int hexB;
    private final int hexC;
    private final int owner;

    public Village(int hexA, int hexB, int hexC, int owner) {
        this.hexA = hexA;
        this.hexB = hexB;
        this.hexC = hexC;
        this.owner = owner;
    }

    public int getHexA() {
        return hexA;
    }

    public int getHexB() {
        return hexB;
    }

    public int getHexC() {
        return hexC;
    }

    public int getOwner() {
        return owner;
    }
}