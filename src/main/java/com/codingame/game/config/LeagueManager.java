package com.codingame.game.config;

import com.codingame.game.config.league.League1;
import com.codingame.game.config.league.League2;

public class LeagueManager {

    public static League league;

    public static void pickLeague(int level) {
        switch (level) {
            case 1: league = new League1(); break;
            case 2: league = new League2(); break;
        }
    }
}
