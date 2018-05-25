package com.thw.shipBattle.service.impl;

import com.thw.shipBattle.domain.BattleField;
import com.thw.shipBattle.domain.BattleShip;
import com.thw.shipBattle.domain.BattleShipBlock;
import com.thw.shipBattle.domain.Coordinate;
import com.thw.shipBattle.enums.BattleShipClass;
import com.thw.shipBattle.service.BattleFieldService;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BattleFieldServiceImpl implements BattleFieldService {


    public BattleField addBattleShipInSquad(BattleShip battleShip, BattleField battleField) {

        battleShip.setCoordinate(getCoordinate(battleShip.getCoordinates()));
        battleField.addBattleShip(battleShip);

        int strength = battleShip.getBattleShipClass() == BattleShipClass.Q_CLASS ? 2 : 1;

        Coordinate coordinate = battleShip.getCoordinate();

        HashMap<Coordinate, BattleShipBlock> hashMap = battleField.getBattleShipHashMap();

        for (int x = coordinate.getX(); x < coordinate.getX() + battleShip.getWidth(); x++) {
            for (int y = coordinate.getY(); y < coordinate.getY() + battleShip.getHeight(); y++) {

                hashMap.put(new Coordinate(x, y), new BattleShipBlock(strength, battleShip));

            }

        }

        return battleField;
    }

    public Coordinate getCoordinate(String targetKey) {
        char[] axis = targetKey.toCharArray();

        char y = (char) (axis[0] - 64);
        int x = Integer.parseInt(String.copyValueOf(axis).substring(1, axis.length));

        Coordinate coordinates = new Coordinate();
        coordinates.setX(x);
        coordinates.setY(y);
        return coordinates;
    }


}
