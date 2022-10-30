package com.codingame.game.board;

public final class Price {

    private final int wood;
    private final int wheat;
    private final int sheep;
    private final int ore;
    private final int brick;

    public Price(int wood, int wheat, int sheep, int ore, int brick) {
        this.wood = wood;
        this.wheat = wheat;
        this.sheep = sheep;
        this.ore = ore;
        this.brick = brick;
    }

    public int getWoodPrice() {
        return wood;
    }

    public int getWheatPrice() {
        return wheat;
    }

    public int getSheepPrice() {
        return sheep;
    }

    public int getOrePrice() {
        return ore;
    }

    public int getBrickPrice() {
        return brick;
    }
}