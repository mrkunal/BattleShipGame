package com.thw.shipBattle.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BattleField {

   private ShipPlayer shipPlayer;
   private List<BattleShip> battleShipList;
   private LinkedList<String> sequence;

   private HashMap<Coordinate,BattleShipBlock> battleShipHashMap;

    public ShipPlayer getShipPlayer() {
        return shipPlayer;
    }

    public void setShipPlayer(ShipPlayer shipPlayer) {
        this.shipPlayer = shipPlayer;
    }

    public List<BattleShip> getBattleShipList() {
        return battleShipList;
    }

    public void setBattleShipList(List<BattleShip> battleShipList) {
        this.battleShipList = battleShipList;
    }

    public BattleField(ShipPlayer shipPlayer) {
        this.shipPlayer = shipPlayer;
        this.battleShipHashMap=new HashMap<>();
    }


    public HashMap<Coordinate, BattleShipBlock> getBattleShipHashMap() {
        return battleShipHashMap;
    }

    public void setBattleShipHashMap(HashMap<Coordinate, BattleShipBlock> battleShipHashMap) {
        this.battleShipHashMap = battleShipHashMap;
    }

    public void addBattleShip(BattleShip battleShip)
    {
         if(battleShipList==null)
         {battleShipList=new LinkedList<BattleShip>();
         }
         battleShipList.add(battleShip);
    }

    public LinkedList<String> getSequence() {
        return sequence;
    }

    public void setSequence(LinkedList<String> sequence) {
        this.sequence = sequence;
    }

    public Boolean isDestroyed(){

        int strength=0;
        for(BattleShip battleShip: this.getBattleShipList())
        {
            strength+=battleShip.getStrength();
        }
        return strength>0?false:true;
    }
}
