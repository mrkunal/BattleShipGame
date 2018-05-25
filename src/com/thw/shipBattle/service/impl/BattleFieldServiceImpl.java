package com.thw.shipBattle.service.impl;

import com.thw.shipBattle.domain.BattleField;
import com.thw.shipBattle.domain.BattleShip;
import com.thw.shipBattle.domain.Coordinate;
import com.thw.shipBattle.service.BattleFieldService;

import java.util.HashMap;

public class BattleFieldServiceImpl implements BattleFieldService {



  public BattleField addBattleShipInSquad(BattleShip battleShip,BattleField battleField)
    {

       battleShip.setCoordinate(getCoordinate(battleShip.getCoordinates()));
       battleField.addBattleShip(battleShip);
       return battleField;
    }

  public Coordinate getCoordinate(String targetKey)
    {
      char[] axis=targetKey.toCharArray();

      char y = axis[0];
      int x = Integer.parseInt( String.copyValueOf(axis).substring(1,axis.length));

      Coordinate coordinates=new Coordinate();
      coordinates.setX(x);
      coordinates.setY(y);
      return coordinates;
    }



}
