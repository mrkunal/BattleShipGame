package test.com.thw.shipBattle.service;

import com.thw.shipBattle.domain.Coordinate;
import com.thw.shipBattle.service.impl.BattleFieldServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattleFieldServiceImplTest {

    BattleFieldServiceImpl battleFieldService=new BattleFieldServiceImpl();

    @Test
    public void testGetCoordinate(){


       Coordinate coordinate= battleFieldService.getCoordinate("B2");

       assertEquals(coordinate.getX(),2);
       assertEquals(coordinate.getY(),2);

        System.out.println("Coordinate Code working fine");
    }

}