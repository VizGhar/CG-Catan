package com.codingame.game.config;

import com.codingame.game.board.Price;

public abstract class League {

    public int getInitialWood() { return 0; }

    public int getInitialWheat() { return 0; }

    public int getInitialSheep() { return 0; }

    public int getInitialOre() { return 0; }

    public int getInitialBrick() { return 0; }

    public Price getRoadPrice() { return new Price(1, 0, 0, 0, 1); }

    public Price getVillagePrice() { return new Price(1, 1, 1, 0, 1); }

    public Price getTownPrice() { return new Price(0, 2, 0, 3, 0); }

    public Price getSpecialCardPrice() { return new Price(0, 1, 1, 1, 0); }

}