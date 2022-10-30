package com.codingame.game.board;

/**
 * Contains {@link TileType} representing which resource you
 * will receive if correct number is shown on game dices.
 *
 * You can set the number using {@link #setNumber(int)} method.
 */
public class BoardTile {

    private final TileType type;
    private int number;

    public BoardTile(TileType type, int number) {
        this.type = type;
        this.number = number;
    }

    public BoardTile(TileType type) {
        this.type = type;
        this.number = 0;
    }

    public TileType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public enum TileType {
        WOOD, WHEAT, SHEEP, ORE, BRICK, DESSERT
    }
}
