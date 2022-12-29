package com.codingame.game;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.codingame.game.board.BoardAction;
import com.codingame.game.board.BoardManager;
import com.codingame.game.config.LeagueManager;
import com.codingame.game.gui.Interface;
import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.endscreen.EndScreenModule;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.toggle.ToggleModule;
import com.codingame.gameengine.module.tooltip.TooltipModule;
import com.google.inject.Inject;

public class Referee extends AbstractReferee {

    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    @Inject private TooltipModule tooltipModule;
    @Inject private EndScreenModule endScreenModule;
    @Inject private ToggleModule toggleModule;
    private BoardManager boardManager;
    private Interface gui;
    private Random random;

    @Override
    public void init() {
        LeagueManager.pickLeague(gameManager.getLeagueLevel());
        random = new SecureRandom(ByteBuffer.wrap(new byte[8]).putLong(gameManager.getSeed()).array());
        boardManager = new BoardManager(gameManager);
        gui = new Interface(graphicEntityModule);
        gui.hud(gameManager, boardManager, tooltipModule, toggleModule);
        gameManager.setMaxTurns(1);
    }

    @Override
    public void gameTurn(int turn) {
        // first 4 turns to place 2 x village + 2 x road

        // TODO: getActivePlayers always returns 0
        Player activePlayer = gameManager.getPlayer(boardManager.getActivePlayer());
        boardManager.sendPlayerInput(activePlayer);
        activePlayer.execute();

        try {
            List<String> outputs = activePlayer.getOutputs();
            if (outputs.size() != 1) {
                activePlayer.setScore(-1);
                activePlayer.deactivate(String.format("%s - Incorrect output", activePlayer.getNicknameToken()));
                return;
            }

            // TODO: Commands separated by ; or on multiple lines?
            String[] commands = outputs.get(0).split(";");

            List<BoardAction> actions = new ArrayList<>();

            for (String command : commands) {
                // TODO: Adjust commands and generate proper BoardAction object
//                actions.add(BoardAction)
            }

            boardManager.validate(actions);
            boardManager.doActions(actions);

            // TODO:
//            gui.newGameState();
        } catch (TimeoutException e) {
            activePlayer.deactivate(String.format("$%d timeout!", activePlayer.getIndex()));
        }
    }


    @Override
    public void onEnd() {
        endScreenModule.setScores(new int[] {5, 10, 14});
    }
}
