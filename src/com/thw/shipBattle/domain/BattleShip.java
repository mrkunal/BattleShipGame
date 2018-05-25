package com.thw.shipBattle.domain;

import com.thw.shipBattle.enums.BattleShipClass;

public class BattleShip {

  private BattleShipClass battleShipClass;
  private int strength;
  private int height;
  private int width;
  private Coordinate coordinate;
  private boolean isDestroyed;
  private String coordinates;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public BattleShipClass getBattleShipClass() {
        return battleShipClass;
    }

    public void setBattleShipClass(BattleShipClass battleShipClass) {
        this.battleShipClass = battleShipClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
