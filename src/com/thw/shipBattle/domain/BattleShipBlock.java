package com.thw.shipBattle.domain;

public class BattleShipBlock {
    private int strength;
    private BattleShip battleShip;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public BattleShip getBattleShip() {
        return battleShip;
    }

    public void setBattleShip(BattleShip battleShip) {
        this.battleShip = battleShip;
    }

    public BattleShipBlock(int strength, BattleShip battleShip) {
        this.strength = strength;
        this.battleShip = battleShip;
    }

}
