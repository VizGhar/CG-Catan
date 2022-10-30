package com.codingame.game.gui;

import com.codingame.game.Player;
import com.codingame.game.board.*;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;

// Z indexes
// background stuff 0-9
// player related stuff (name, color...) 10-19
// tiles 20-39
public class Interface {

    private final GraphicEntityModule graphicsModule;

    public Interface(GraphicEntityModule graphicsModule) {
        this.graphicsModule = graphicsModule;
    }

    private final HashMap<Integer, Pair<Integer, Integer>> basePositions = new HashMap<>();

    {
        basePositions.put(0, new ImmutablePair<>(760, 196));
        basePositions.put(1, new ImmutablePair<>(960, 196));
        basePositions.put(2, new ImmutablePair<>(1160, 196));
        basePositions.put(3, new ImmutablePair<>(1260, 368));
        basePositions.put(4, new ImmutablePair<>(1360, 540));
        basePositions.put(5, new ImmutablePair<>(1260, 712));
        basePositions.put(6, new ImmutablePair<>(1160, 884));
        basePositions.put(7, new ImmutablePair<>(960, 884));
        basePositions.put(8, new ImmutablePair<>(760, 884));
        basePositions.put(9, new ImmutablePair<>(660, 712));
        basePositions.put(10, new ImmutablePair<>(560, 540));
        basePositions.put(11, new ImmutablePair<>(660, 368));
        basePositions.put(12, new ImmutablePair<>(860, 368));
        basePositions.put(13, new ImmutablePair<>(1060, 368));
        basePositions.put(14, new ImmutablePair<>(1160, 540));
        basePositions.put(15, new ImmutablePair<>(1060, 712));
        basePositions.put(16, new ImmutablePair<>(860, 712));
        basePositions.put(17, new ImmutablePair<>(760, 540));
        basePositions.put(18, new ImmutablePair<>(960, 540));
    }

    public void hud(MultiplayerGameManager<Player> gameManager, BoardManager boardManager) {
        graphicsModule.createSprite()
                .setX(0)
                .setY(0)
                .setBaseWidth(1920)
                .setBaseHeight(1080)
                .setImage("background.jpeg");

        // color frames
        graphicsModule.createRectangle()
                .setX(20)
                .setY(20)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(10)
                .setFillColor(gameManager.getPlayer(0).getColorToken());

        graphicsModule.createRectangle()
                .setX(1800)
                .setY(20)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(10)
                .setFillColor(gameManager.getPlayer(1).getColorToken());

        graphicsModule.createRectangle()
                .setX(20)
                .setY(960)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(10)
                .setFillColor(gameManager.getPlayer(2).getColorToken());

        graphicsModule.createRectangle()
                .setX(1800)
                .setY(960)
                .setWidth(100)
                .setHeight(100)
                .setZIndex(10)
                .setFillColor(gameManager.getPlayer(3).getColorToken());

        // avatars
        graphicsModule.createSprite()
                .setX(25)
                .setY(25)
                .setBaseWidth(90)
                .setBaseHeight(90)
                .setZIndex(11)
                .setImage(gameManager.getPlayer(0).getAvatarToken());

        graphicsModule.createSprite()
                .setX(1805)
                .setY(25)
                .setBaseWidth(90)
                .setBaseHeight(90)
                .setZIndex(11)
                .setImage(gameManager.getPlayer(1).getAvatarToken());

        graphicsModule.createSprite()
                .setX(25)
                .setY(965)
                .setBaseWidth(90)
                .setBaseHeight(90)
                .setZIndex(11)
                .setImage(gameManager.getPlayer(2).getAvatarToken());

        graphicsModule.createSprite()
                .setX(1805)
                .setY(965)
                .setBaseWidth(90)
                .setBaseHeight(90)
                .setZIndex(11)
                .setImage(gameManager.getPlayer(0).getAvatarToken());

        for (int i = 0; i < boardManager.getTiles().size(); i++) {
            BoardTile tile = boardManager.getTiles().get(i);
            Pair<Integer, Integer> position = basePositions.get(i);
            if (position == null) throw new IllegalStateException();
            int x = position.getLeft();
            int y = position.getRight();
            graphicsModule.createSprite()
                    .setX(x - 100)
                    .setY(y - 100)
                    .setZIndex(i + 20)
                    .setImage(tile.getType().toString().toLowerCase() + ".png");

            if (tile.getType() == BoardTile.TileType.DESSERT) {
                continue;
            }

            graphicsModule.createSprite()
                    .setAnchor(0.5)
                    .setX(x)
                    .setY(y + 50)
                    .setZIndex(i + 40)
                    .setImage(tile.getNumber() + ".png");
        }
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
