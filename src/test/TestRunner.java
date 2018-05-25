package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.com.thw.shipBattle.service.BattleFieldServiceImplTest;
import test.com.thw.shipBattle.service.BattleGameServiceImplTest;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJunit.class,BattleFieldServiceImplTest.class,BattleGameServiceImplTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
