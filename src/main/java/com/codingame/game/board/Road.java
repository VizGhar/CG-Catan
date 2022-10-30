package com.codingame.game.board;

public final class Road {
    private final int hexA;
    private final int hexB;
    private final int owner;

    public Road(int hexA, int hexB, int owner) {
        this.hexA = hexA;
        this.hexB = hexB;
        this.owner = owner;
    }

    public int getHexA() {
        return hexA;
    }

    public int getHexB() {
        return hexB;
    }

    public int getOwner() {
        return owner;
    }
}