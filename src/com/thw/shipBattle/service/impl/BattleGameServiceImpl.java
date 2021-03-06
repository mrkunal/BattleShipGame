package com.thw.shipBattle.service.impl;

import com.thw.shipBattle.co.BattleCO;
import com.thw.shipBattle.domain.*;
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
       return startBattle(firstBattleField, secondBattleField);
    }

    private BattleShipClass getBattleShipClass( String shipClass) {

        switch (shipClass) {
            case "Q":
                return BattleShipClass.Q_CLASS;
            case "P":
                return BattleShipClass.P_CLASS;
        }
        return null;
    }


   private BattleShip populateBattleShip(BattleShip battleShip, String shipDetail, int playerNum) {

        String[] ship = shipDetail.split(" ");

        BattleShipClass battleShipClass = getBattleShipClass( ship[0]);
        battleShip.setBattleShipClass(battleShipClass);
        battleShip.setHeight(Integer.parseInt(ship[2]));
        battleShip.setWidth(Integer.parseInt(ship[1]));
        battleShip.setCoordinates(ship[playerNum]);

        battleShip.setStrength(battleShip.getHeight()* battleShip.getWidth() * (battleShip.getBattleShipClass()==BattleShipClass.Q_CLASS?2:1));

        return battleShip;
    }

  private  BattleField populateSequenceInBattleField(BattleField battleField, String[] targetSequence) {
        LinkedList<String> battleTargetSequenceList = new LinkedList<>();

        Collections.addAll(battleTargetSequenceList, targetSequence);
        battleField.setSequence(battleTargetSequenceList);
        return battleField;
    }

   private ShipPlayer startBattle(BattleField attackerBattleField, BattleField targetBattleField) {

        while (!attackerBattleField.getSequence().isEmpty() || !targetBattleField.getSequence().isEmpty()) {

            if (attackerBattleField.getSequence().isEmpty()) {
                System.out.println(attackerBattleField.getShipPlayer().getName() + " no more missiles left to launch");
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
            }else{
               if(targetBattleField.isDestroyed())
               {
                   System.out.println(attackerBattleField.getShipPlayer().getName()+"  won the battle");
                   return attackerBattleField.getShipPlayer();

               }
            }

        }
      return null;
    }

   private Boolean attackBattleField(BattleField attackingBattleField, BattleField targetBattleField, String targetBlock) {

        Coordinate targetCoordinate = battleFieldService.getCoordinate(targetBlock);

        BattleShipBlock battleShipBlock = targetBattleField.getBattleShipHashMap().get(targetCoordinate);
        if(battleShipBlock!=null) {
            int strength = battleShipBlock.getStrength();
            if (strength > 0) {
                battleShipBlock.setStrength(--strength);
                int shipStrength = battleShipBlock.getBattleShip().getStrength();
                battleShipBlock.getBattleShip().setStrength(--shipStrength);
                System.out.println(attackingBattleField.getShipPlayer().getName() + " fires a missile with target " + targetBlock + " which got hit");

                return true;
            }
        }
        System.out.println(attackingBattleField.getShipPlayer().getName() + " fires a missile with target " + targetBlock + " which got miss.");
        return false;
    }


}
