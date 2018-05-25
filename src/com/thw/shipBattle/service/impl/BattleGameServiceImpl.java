package com.thw.shipBattle.service.impl;

import com.thw.shipBattle.co.BattleCO;
import com.thw.shipBattle.domain.BattleShip;
import com.thw.shipBattle.domain.BattleField;
import com.thw.shipBattle.domain.Coordinate;
import com.thw.shipBattle.domain.ShipPlayer;
import com.thw.shipBattle.enums.BattleShipClass;
import com.thw.shipBattle.service.BattleFieldService;
import com.thw.shipBattle.service.BattleGameService;

import java.util.*;

public class BattleGameServiceImpl implements BattleGameService {

    BattleFieldServiceImpl battleFieldService = new BattleFieldServiceImpl();

    public ShipPlayer battleShips(BattleCO battleCO) {

        ShipPlayer firstPlayer = new ShipPlayer(battleCO.getPlayer1Name());
        ShipPlayer secondPlayer = new ShipPlayer(battleCO.getPlayer2Name());

        BattleField firstBattleField = new BattleField(firstPlayer);
        BattleField secondBattleField = new BattleField(secondPlayer);

        for (int shipCount = 0; shipCount < battleCO.getShips(); shipCount++) {

            String shipDetails = battleCO.getShipDetails()[shipCount];

            BattleShip firstPlayerShip = new BattleShip();
            BattleShip secondPlayerShip = new BattleShip();

            firstPlayerShip = populateBattleShip(firstPlayerShip, shipDetails, 3);
            secondPlayerShip = populateBattleShip(secondPlayerShip, shipDetails, 4);

            firstBattleField = battleFieldService.addBattleShipInSquad(firstPlayerShip, firstBattleField);
            secondBattleField = battleFieldService.addBattleShipInSquad(secondPlayerShip, secondBattleField);

        }

        firstBattleField = populateSequenceInBattleField(firstBattleField, battleCO.getShipSequence()[0].split(" "));
        secondBattleField = populateSequenceInBattleField(secondBattleField, battleCO.getShipSequence()[1].split(" "));
        startBattle(firstBattleField, secondBattleField);

        return null;
    }

    static BattleShipClass getBattleShipClass(BattleShip battleShip,String shipClass) {

        switch (shipClass) {
            case "Q":battleShip.setStrength(2);
                return  BattleShipClass.Q_CLASS;
            case "P":battleShip.setStrength(1);
                return BattleShipClass.P_CLASS;
        }
        return null;
    }


    BattleShip populateBattleShip(BattleShip battleShip, String shipDetail, int playerNum) {

        String[] ship = shipDetail.split(" ");

        BattleShipClass battleShipClass = getBattleShipClass(battleShip,ship[0]);
        battleShip.setBattleShipClass(battleShipClass);
        battleShip.setHeight(Integer.parseInt(ship[2]));
        battleShip.setWidth(Integer.parseInt(ship[1]));
        battleShip.setCoordinates(ship[playerNum]);

        return battleShip;
    }

    BattleField populateSequenceInBattleField(BattleField battleField, String[] targetSequence) {
        LinkedList<String> battleTargetSequenceList = new LinkedList<>();

        Collections.addAll(battleTargetSequenceList, targetSequence);
//        for (String target:targetSequence
//             ) {
//
//            battleTargetSequenceList.add(target);
//        }

        battleField.setSequence(battleTargetSequenceList);
        return battleField;
    }

    void startBattle(BattleField attackerBattleField, BattleField targetBattleField) {

        while (!attackerBattleField.getSequence().isEmpty() || !targetBattleField.getSequence().isEmpty()) {

            if(attackerBattleField.getSequence().isEmpty())
            {   System.out.println(attackerBattleField.getShipPlayer().getName() +" no more missiles left to launch");
                BattleField tempBattleField;
                tempBattleField = attackerBattleField;
                attackerBattleField = targetBattleField;
                targetBattleField = tempBattleField;
            }

            String targetBlock = attackerBattleField.getSequence().getFirst();
            attackerBattleField.getSequence().removeFirst();

            if (!attackBattleField(attackerBattleField, targetBattleField, targetBlock)) {
                BattleField tempBattleField;
                tempBattleField = attackerBattleField;
                attackerBattleField = targetBattleField;
                targetBattleField = tempBattleField;
            }

        }

    }

    Boolean attackBattleField(BattleField attackingBattleField, BattleField targetBattleField, String targetBlock) {

        Coordinate targetCoordinate=battleFieldService.getCoordinate(targetBlock);

        Iterator<BattleShip> itr= targetBattleField.getBattleShipList().iterator();
        while(itr.hasNext())
        {
            if(isBattleShipHit(itr.next(),targetCoordinate))
            {
                System.out.println(attackingBattleField.getShipPlayer().getName() +" fires a missile with target "+ targetBlock +" which got hit");
                return true;
            }
        }


        System.out.println(attackingBattleField.getShipPlayer().getName() +" fires a missile with target "+ targetBlock +" which got miss.");
        return false;
    }

    Boolean isBattleShipHit(BattleShip battleShip, Coordinate targetCoordinate) {
        Coordinate shipCoordinate = battleShip.getCoordinate();


        if (shipCoordinate.getX() <= targetCoordinate.getX() && shipCoordinate.getX() + battleShip.getWidth() - 1 >= targetCoordinate.getX()) {


            if(shipCoordinate.getY() <=targetCoordinate.getY() && shipCoordinate.getY() + battleShip.getHeight() -1 >= targetCoordinate.getY())
            {
                int strength=battleShip.getStrength();

                if(strength>0)
                {
                    battleShip.setStrength(--strength);
                    return true;
                }
            }


        }

        return false;
    }


}
