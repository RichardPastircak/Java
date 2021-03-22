package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sk.tuke.kpi.kp.mastermind.core.Game;
import sk.tuke.kpi.kp.mastermind.core.Pin;

import java.util.Random;

public class GameTest {
    @Test
    public void testFillingHoles(){
        Game.getGame().fillUpHoles();
        for(int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getCombination()[i].getColor() == Pin.EMPTY);
            assertTrue(Game.getGame().getEvaluationHoles()[i].getColor() == Pin.EMPTY);
            assertTrue(Game.getGame().getPlayerHoles()[i].getColor() == Pin.EMPTY);
        }

        for(int i =0; i < 9; i++){
            for(int j = 0; j < 8; j++){
                assertTrue(Game.getGame().getHistory()[i][j].getColor() == Pin.EMPTY);
            }
        }
    }

    @Test
    public void testGenerateCombination(){
        Game.getGame().fillUpHoles();
        Game.getGame().generateCombination();

        for (int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getCombination()[i].getColor() != Pin.EMPTY && Game.getGame().getCombination()[i].getColor() != Pin.BLACK && Game.getGame().getCombination()[i].getColor() != Pin.GREY);
            for(int j = i+1; j < 4; j++){
                assertTrue(Game.getGame().getCombination()[i] != Game.getGame().getCombination()[j]);
            }
        }
    }

    @Test
    public void testReset(){
        Game.getGame().fillUpHoles();

        for(int i = 0; i < 4; i++){
            Game.getGame().getEvaluationHoles()[i].setColor(Pin.RED);
            Game.getGame().getPlayerHoles()[i].setColor(Pin.RED);
        }

        Game.getGame().reset();
        for (int i = 0; i < 4; i++) {
            assertTrue(Game.getGame().getEvaluationHoles()[i].getColor() == Pin.EMPTY);
            assertTrue(Game.getGame().getPlayerHoles()[i].getColor() == Pin.EMPTY);
        }
    }



    @Test
    public void testEvaluate(){
        Pin[] result = {Pin.BLACK, Pin.GREY, Pin.GREY, Pin.EMPTY};

        Game.getGame().fillUpHoles();
        //Game.getGame().generateCombination();
        Game.getGame().getPlayerHoles()[0].setColor(Pin.RED);
        Game.getGame().getPlayerHoles()[1].setColor(Pin.BLUE);
        Game.getGame().getPlayerHoles()[2].setColor(Pin.GREEN);
        Game.getGame().getPlayerHoles()[3].setColor(Pin.YELLOW);

        Game.getGame().getCombination()[0].setColor(Pin.GREEN);
        Game.getGame().getCombination()[1].setColor(Pin.BLUE);
        Game.getGame().getCombination()[2].setColor(Pin.RED);
        Game.getGame().getCombination()[3].setColor(Pin.PURPLE);

        Game.getGame().evaluate();

        for (int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getEvaluationHoles()[i].getColor() == result[i]);
        }
    }

   @Test
    public void testPutPin(){
        Game.getGame().fillUpHoles();
        Random rand = new Random();

        int index = rand.nextInt(4);

        Pin pin = Pin.EMPTY;
        for (int i = 0; i < 4; i++) {
           int tmp = rand.nextInt(6);
           switch (tmp) {
               case 0:
                   pin = Pin.RED;
                   break;
               case 1:
                   pin = Pin.BLUE;
                   break;
               case 2:
                   pin = Pin.GREEN;
                   break;
               case 3:
                   pin = Pin.YELLOW;
                   break;
               case 4:
                   pin = Pin.PURPLE;
                   break;
               case 5:
                   pin = Pin.CYAN;
                   break;
               default:
                   break;
           }
       }

        Game.getGame().putPin(pin, index);

        assertTrue(Game.getGame().getPlayerHoles()[index].getColor() == pin);
    }

}
