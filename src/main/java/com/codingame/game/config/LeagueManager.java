package com.codingame.game.config;

import com.codingame.game.config.league.League1;

public class LeagueManager {

    public static League league;

    public static void pickLeague(int level) {
        switch (level) {
            default: league = new League1();
        }
    }
}
