package com.codingame.game.gui;

import com.codingame.game.Player;
import com.codingame.game.board.*;
import com.codingame.game.config.LeagueManager;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Z indexes
// background stuff 0-9
// player related stuff (name, color...) 10-19
// tiles 20-39
// numbers 40-59
// robber 60
//
// Board is composed of 19 tiles as following
//
//     000 001 002
//   011 012 013 003
// 010 017 018 014 004
//   009 016 015 005
//     008 007 006
public class Interface {

    private final GraphicEntityModule graphicsModule;

    private Sprite robber;

    public Interface(GraphicEntityModule graphicsModule) {
        this.graphicsModule = graphicsModule;
    }

    private final Pair<Integer, Integer> tileOffset = new ImmutablePair<>(-100, -100);
    private final Pair<Integer, Integer> numberOffset = new ImmutablePair<>(0, 50);
    private final Pair<Integer, Integer> robberOffset = new ImmutablePair<>(0, -46);
    private final HashMap<Integer, Pair<Integer, Integer>> basePositions = new HashMap<>();
    private final List<Pair<Integer, Integer>> threePlayersHandsPosition = new ArrayList<>();
    private final List<Pair<Integer, Integer>> fourPlayersHandsPosition = new ArrayList<>();

    {
        basePositions.put(0, new ImmutablePair<>(1070, 196));
        basePositions.put(1, new ImmutablePair<>(1270, 196));
        basePositions.put(2, new ImmutablePair<>(1470, 196));
        basePositions.put(3, new ImmutablePair<>(1570, 368));
        basePositions.put(4, new ImmutablePair<>(1670, 540));
        basePositions.put(5, new ImmutablePair<>(1570, 712));
        basePositions.put(6, new ImmutablePair<>(1470, 884));
        basePositions.put(7, new ImmutablePair<>(1270, 884));
        basePositions.put(8, new ImmutablePair<>(1070, 884));
        basePositions.put(9, new ImmutablePair<>(970, 712));
        basePositions.put(10, new ImmutablePair<>(870, 540));
        basePositions.put(11, new ImmutablePair<>(970, 368));
        basePositions.put(12, new ImmutablePair<>(1170, 368));
        basePositions.put(13, new ImmutablePair<>(1370, 368));
        basePositions.put(14, new ImmutablePair<>(1470, 540));
        basePositions.put(15, new ImmutablePair<>(1370, 712));
        basePositions.put(16, new ImmutablePair<>(1170, 712));
        basePositions.put(17, new ImmutablePair<>(1070, 540));
        basePositions.put(18, new ImmutablePair<>(1270, 540));

        threePlayersHandsPosition.add(new ImmutablePair<>(25, 155));
        threePlayersHandsPosition.add(new ImmutablePair<>(25, 415));
        threePlayersHandsPosition.add(new ImmutablePair<>(25, 675));

        fourPlayersHandsPosition.add(new ImmutablePair<>(25, 25));
        fourPlayersHandsPosition.add(new ImmutablePair<>(25, 285));
        fourPlayersHandsPosition.add(new ImmutablePair<>(25, 545));
        fourPlayersHandsPosition.add(new ImmutablePair<>(25, 805));
    }

    public void hud(MultiplayerGameManager<Player> gameManager, BoardManager boardManager) {
        graphicsModule.createSprite()
                .setX(0)
                .setY(0)
                .setBaseWidth(1920)
                .setBaseHeight(1080)
                .setZIndex(0)
                .setImage("background.png");

        if (LeagueManager.league.arePortsEnabled()) {
            graphicsModule.createSprite()
                    .setX(0)
                    .setY(0)
                    .setBaseWidth(1920)
                    .setBaseHeight(1080)
                    .setZIndex(1)
                    .setImage("ports.png");
        }

        // player hands
        final List<Pair<Integer, Integer>> handPositions = gameManager.getPlayerCount() == 3 ? threePlayersHandsPosition : fourPlayersHandsPosition;
        for (int i = 0; i < gameManager.getPlayerCount(); i++) {
            graphicsModule.createGroup(
                            graphicsModule.createSprite()
                                    .setX(0)
                                    .setY(0)
                                    .setBaseWidth(600)
                                    .setBaseHeight(250)
                                    .setZIndex(9)
                                    .setImage("hand.png"),

                            graphicsModule.createSprite()
                                    .setX(30)
                                    .setY(30)
                                    .setBaseWidth(90)
                                    .setBaseHeight(90)
                                    .setZIndex(11)
                                    .setImage(gameManager.getPlayer(i).getAvatarToken()),

                            graphicsModule.createRectangle()
                                    .setX(30)
                                    .setY(30)
                                    .setWidth(90)
                                    .setHeight(90)
                                    .setZIndex(10)
                                    .setFillColor(gameManager.getPlayer(i).getColorToken()),

                            graphicsModule.createSprite()
                                    .setX(25)
                                    .setY(25)
                                    .setBaseWidth(100)
                                    .setBaseHeight(100)
                                    .setZIndex(12)
                                    .setImage("frame_" + (i + 1) + ".png")
                    )
                    .setZIndex(10)
                    .setX(handPositions.get(i).getLeft())
                    .setY(handPositions.get(i).getRight());
        }

        for (int i = 0; i < boardManager.getTiles().size(); i++) {
            BoardTile tile = boardManager.getTiles().get(i);
            Pair<Integer, Integer> tilePosition = basePositions.get(i);
            graphicsModule.createSprite()
                    .setX(tilePosition.getLeft() + tileOffset.getLeft())
                    .setY(tilePosition.getRight() + tileOffset.getRight())
                    .setZIndex(i + 20)
                    .setImage(tile.getType().toString().toLowerCase() + ".png");

            if (tile.getType() == BoardTile.TileType.DESSERT) {
                continue;
            }

            graphicsModule.createSprite()
                    .setAnchor(0.5)
                    .setX(tilePosition.getLeft() + numberOffset.getLeft())
                    .setY(tilePosition.getRight() + numberOffset.getRight())
                    .setZIndex(i + 40)
                    .setImage(tile.getNumber() + ".png");
        }

        Pair<Integer, Integer> tilePosition = basePositions.get(boardManager.getRobberPosition());
        robber = graphicsModule.createSprite()
                .setAnchor(0.5)
                .setX(tilePosition.getLeft())   // ignore robberOffset for dessert
                .setY(tilePosition.getRight())  // ignore robberOffset for dessert
                .setZIndex(60)
                .setImage("robber.png");
    }

    private static class GameState {
        // Hold previous game state so that you know,
        // what needs to be animated
    }

    public void newGameState(
            List<Road> roads,
            List<Village> villages,
            List<Town> towns,
            Hand player0,
            Hand player1,
            Hand player2,
            Hand player3
            // ...
    ) {

    }
}
