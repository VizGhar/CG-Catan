package com.codingame.game.board;

public abstract class BoardAction {

    public static class BuyRoadAction extends BoardAction {
        private final int hexA;
        private final int hexB;

        public BuyRoadAction(int hexA, int hexB) {
            this.hexA = hexA;
            this.hexB = hexB;
        }

        public int getHexA() {
            return hexA;
        }

        public int getHexB() {
            return hexB;
        }
    }

    public static class BuyVillageAction extends BoardAction {
        private final int hexA;
        private final int hexB;
        private final int hexC;

        public BuyVillageAction(int hexA, int hexB, int hexC) {
            this.hexA = hexA;
            this.hexB = hexB;
            this.hexC = hexC;
        }

        public int getHexA() {
            return hexA;
        }

        public int getHexB() {
            return hexB;
        }

        public int getHexC() {
            return hexC;
        }
    }

    public static class BuyTownAction extends BoardAction {
        private final int hexA;
        private final int hexB;
        private final int hexC;

        public BuyTownAction(int hexA, int hexB, int hexC) {
            this.hexA = hexA;
            this.hexB = hexB;
            this.hexC = hexC;
        }

        public int getHexA() {
            return hexA;
        }

        public int getHexB() {
            return hexB;
        }

        public int getHexC() {
            return hexC;
        }
    }

    public static class BuySpecialCardAction extends BoardAction {

    }

    public static class SkipAction extends BoardAction {

    }
}
