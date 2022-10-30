package com.codingame.game.board;

import com.codingame.game.Player;
import com.codingame.game.config.LeagueManager;
import com.codingame.gameengine.core.MultiplayerGameManager;

import java.util.*;

/**
 * This class holds all the information about players and their {@link Hand}s.
 * It handles all actions made by players.
 */
public class BoardManager {

    // TODO: add Robber
    // TODO: dice rolls
    // TODO: basically whole game logic :D

    private int activePlayer;

    private final Hand[] playerHands;
    private final MultiplayerGameManager<Player> gameManager;

    private final List<Road> roads;
    private final List<Village> villages;
    private final List<Town> towns;
    private int robberPosition;

    private final List<BoardTile> tiles = new ArrayList<>();
    private final List<Integer> scoring;

    {
        tiles.addAll(Arrays.asList(
                new BoardTile(BoardTile.TileType.DESSERT),
                new BoardTile(BoardTile.TileType.WOOD),
                new BoardTile(BoardTile.TileType.WOOD),
                new BoardTile(BoardTile.TileType.WOOD),
                new BoardTile(BoardTile.TileType.WOOD),
                new BoardTile(BoardTile.TileType.BRICK),
                new BoardTile(BoardTile.TileType.BRICK),
                new BoardTile(BoardTile.TileType.BRICK),
                new BoardTile(BoardTile.TileType.SHEEP),
                new BoardTile(BoardTile.TileType.SHEEP),
                new BoardTile(BoardTile.TileType.SHEEP),
                new BoardTile(BoardTile.TileType.SHEEP),
                new BoardTile(BoardTile.TileType.WHEAT),
                new BoardTile(BoardTile.TileType.WHEAT),
                new BoardTile(BoardTile.TileType.WHEAT),
                new BoardTile(BoardTile.TileType.WHEAT),
                new BoardTile(BoardTile.TileType.ORE),
                new BoardTile(BoardTile.TileType.ORE),
                new BoardTile(BoardTile.TileType.ORE)
        ));

        this.scoring = new ArrayList<>();
        scoring.addAll(Arrays.asList(
                5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11
        ));
    }

    public List<BoardTile> getTiles() {
        return tiles;
    }

    public BoardManager(MultiplayerGameManager<Player> gameManager) {
        this.gameManager = gameManager;
        this.activePlayer = 0;
        this.playerHands = new Hand[gameManager.getPlayerCount()];
        for (int id = 0; id < gameManager.getPlayerCount(); id++) {
            playerHands[id] = new Hand(
                    LeagueManager.league.getInitialWood(),
                    LeagueManager.league.getInitialWheat(),
                    LeagueManager.league.getInitialSheep(),
                    LeagueManager.league.getInitialOre(),
                    LeagueManager.league.getInitialBrick()
            );
        }
        this.roads = new ArrayList<>();
        this.villages = new ArrayList<>();
        this.towns = new ArrayList<>();

        // shuffle tiles, place them on board and assign them "numbers"
        Collections.shuffle(tiles, new Random(gameManager.getSeed()));
        int offset = 0;
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).getType() == BoardTile.TileType.DESSERT) {
                robberPosition = i;
                offset = -1;
                continue;
            }
            tiles.get(i).setNumber(scoring.get(i + offset));
        }
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void sendPlayerInput(Player player) {
        Hand playerHand = playerHands[player.getIndex()];
        player.sendInputLine(
                playerHand.getStockWood() + " " +
                playerHand.getStockWheat() + " " +
                playerHand.getStockSheep() + " " +
                playerHand.getStockOre() + " " +
                playerHand.getStockBrick()
        );

        player.sendInputLine(Integer.toString(roads.size()));
        for (Road road : roads) {
            player.sendInputLine(road.getOwner() + " " + road.getHexA() + " " + road.getHexB());
        }

        player.sendInputLine(Integer.toString(villages.size()));
        for (Village village : villages) {
            player.sendInputLine(village.getOwner() + " " + village.getHexA() + " " + village.getHexB() + " " + village.getHexC());
        }

        player.sendInputLine(Integer.toString(towns.size()));
        for (Town town : towns) {
            player.sendInputLine(town.getOwner() + " " + town.getHexA() + " " + town.getHexB() + " " + town.getHexC());
        }
    }

    public boolean validate(List<BoardAction> actions) {
        // TODO: return some error (InsufficientFunds, IllegalPlacement etc.)
        return true;
    }

    public void doActions(List<BoardAction> actions) {
        for (BoardAction action : actions) {
            if (action instanceof BoardAction.BuyRoadAction) {
                buyRoad((BoardAction.BuyRoadAction) action);
            } else if (action instanceof BoardAction.BuyVillageAction) {
                buyVillage((BoardAction.BuyVillageAction) action);
            } else if (action instanceof BoardAction.BuyTownAction) {
                buyTown((BoardAction.BuyTownAction) action);
            } else if (action instanceof BoardAction.BuySpecialCardAction) {
                buySpecialCard((BoardAction.BuySpecialCardAction) action);
            } else if (action instanceof BoardAction.SkipAction) {
                skip();
            }
        }

        if (actions.size() == 0 || !(actions.get(actions.size() - 1) instanceof BoardAction.SkipAction)) {
            skip();
        }
    }

    public int getRobberPosition() {
        return robberPosition;
    }

    private void buyRoad(BoardAction.BuyRoadAction action) {
        roads.add(new Road(action.getHexA(), action.getHexB(), activePlayer));
        playerHands[activePlayer].pay(LeagueManager.league.getRoadPrice());
    }

    private void buyVillage(BoardAction.BuyVillageAction action) {
        villages.add(new Village(action.getHexA(), action.getHexB(), action.getHexC(), activePlayer));
        playerHands[activePlayer].pay(LeagueManager.league.getVillagePrice());
    }

    private void buyTown(BoardAction.BuyTownAction action) {
        towns.add(new Town(action.getHexA(), action.getHexB(), action.getHexC(), activePlayer));
        playerHands[activePlayer].pay(LeagueManager.league.getTownPrice());
    }

    private void buySpecialCard(BoardAction.BuySpecialCardAction action) {
        // TODO: special card
        playerHands[activePlayer].pay(LeagueManager.league.getSpecialCardPrice());
    }

    private void skip() {
        do {
            activePlayer = activePlayer + 1 % gameManager.getPlayerCount();
        } while (!gameManager.getPlayer(activePlayer).isActive());
    }
}
