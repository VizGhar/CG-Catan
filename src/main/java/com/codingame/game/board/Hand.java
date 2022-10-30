package com.codingame.game.board;

/**
 * Player's hand
 */
public final class Hand {

    // TODO: add played/hidden bonus cards
    private int stockWood;
    private int stockWheat;
    private int stockSheep;
    private int stockOre;
    private int stockBrick;

    public Hand(int stockWood, int stockWheat, int stockSheep, int stockOre, int stockBrick) {
        this.stockWood = stockWood;
        this.stockWheat = stockWheat;
        this.stockSheep = stockSheep;
        this.stockOre = stockOre;
        this.stockBrick = stockBrick;
    }

    public int getStockWood() {
        return stockWood;
    }

    public void setStockWood(int stockWood) {
        this.stockWood = stockWood;
    }

    public int getStockWheat() {
        return stockWheat;
    }

    public void setStockWheat(int stockWheat) {
        this.stockWheat = stockWheat;
    }

    public int getStockSheep() {
        return stockSheep;
    }

    public void setStockSheep(int stockSheep) {
        this.stockSheep = stockSheep;
    }

    public int getStockOre() {
        return stockOre;
    }

    public void setStockOre(int stockOre) {
        this.stockOre = stockOre;
    }

    public int getStockBrick() {
        return stockBrick;
    }

    public void setStockBrick(int stockBrick) {
        this.stockBrick = stockBrick;
    }

    public void pay(Price price) {
        this.stockWood -= price.getWoodPrice();
        this.stockWheat -= price.getWheatPrice();
        this.stockSheep -= price.getSheepPrice();
        this.stockOre -= price.getOrePrice();
        this.stockBrick -= price.getBrickPrice();
    }
}