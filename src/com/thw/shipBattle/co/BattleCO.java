package com.thw.shipBattle.co;

public class BattleCO {

     private String battleSize;
    private int ships;
    private String[] shipDetails;
    private String[] shipSequence;
    private String player1Name;
    private String player2Name;

     public String getBattleSize() {
          return battleSize;
     }

     public void setBattleSize(String battleSize) {
          this.battleSize = battleSize;
     }

     public int getShips() {
          return ships;
     }

     public void setShips(int ships) {
          this.ships = ships;
     }

     public String[] getShipDetails() {
          return shipDetails;
     }

     public void setShipDetails(String[] shipDetails) {
          this.shipDetails = shipDetails;
     }

     public String[] getShipSequence() {
          return shipSequence;
     }

     public void setShipSequence(String[] shipSequence) {
          this.shipSequence = shipSequence;
     }

     public String getPlayer1Name() {
          return player1Name;
     }

     public void setPlayer1Name(String player1Name) {
          this.player1Name = player1Name;
     }

     public String getPlayer2Name() {
          return player2Name;
     }

     public void setPlayer2Name(String player2Name) {
          this.player2Name = player2Name;
     }
}
