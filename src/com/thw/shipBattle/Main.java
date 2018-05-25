package com.thw.shipBattle;

import com.thw.shipBattle.co.BattleCO;
import com.thw.shipBattle.domain.ShipPlayer;
import com.thw.shipBattle.service.BattleGameService;
import com.thw.shipBattle.service.impl.BattleGameServiceImpl;

public class Main {

    public static void main(String[] args) {

        BattleGameServiceImpl battleGameService = new BattleGameServiceImpl();

        BattleCO battleCO=new BattleCO();
        battleCO.setBattleSize("5 E");
        battleCO.setShips(2);
        String[] shipDetails = {"Q 1 1 A1 B2", "P 2 1 D4 C3"};
        battleCO.setShipDetails(shipDetails);
        String[] shipSequence = {"B2 B2 C3 C4 B3", "A1 B2 B3 A1 D1 E1 D4 D4 D5 D5"};
        battleCO.setShipSequence(shipSequence);

        battleCO.setPlayer1Name("Player 1");
        battleCO.setPlayer2Name("Player 2");
        battleGameService.battleShips(battleCO);

    }
}
