package sk.tuke.gamestudio.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sk.tuke.gamestudio.game.mastermind.core.Game;
import sk.tuke.gamestudio.game.mastermind.core.Pin;

import java.util.Random;

public class GameTest {
    Game game = new Game();

    @Test
    public void fillingHolesTest(){
        game.fillUpHoles();
        for(int i = 0; i < 4; i++){
            assertTrue(game.getCombination()[i].getColor() == Pin.EMPTY);
            assertTrue(game.getEvaluationHoles()[i].getColor() == Pin.EMPTY);
            assertTrue(game.getPlayerHoles()[i].getColor() == Pin.EMPTY);
        }

        for(int i =0; i < 9; i++){
            for(int j = 0; j < 8; j++){
                assertTrue(game.getHistory()[i][j].getColor() == Pin.EMPTY);
            }
        }
    }

    @Test
    public void generateCombinationTest(){
        game.fillUpHoles();
        game.generateCombination();

        for (int i = 0; i < 4; i++){
            assertTrue(game.getCombination()[i].getColor() != Pin.EMPTY && game.getCombination()[i].getColor() != Pin.BLACK && game.getCombination()[i].getColor() != Pin.GREY);
            for(int j = i+1; j < 4; j++){
                assertTrue(game.getCombination()[i] != game.getCombination()[j]);
            }
        }
    }

    @Test
    public void resetTest(){
        game.fillUpHoles();

        for(int i = 0; i < 4; i++){
            game.getEvaluationHoles()[i].setColor(Pin.RED);
            game.getPlayerHoles()[i].setColor(Pin.RED);
        }

        game.reset();
        for (int i = 0; i < 4; i++) {
            assertTrue(game.getEvaluationHoles()[i].getColor() == Pin.EMPTY);
            assertTrue(game.getPlayerHoles()[i].getColor() == Pin.EMPTY);
        }
    }



    @Test
    public void evaluateTest(){
        Pin[] result = {Pin.BLACK, Pin.GREY, Pin.GREY, Pin.EMPTY};

        game.fillUpHoles();
        //game.generateCombination();
        game.getPlayerHoles()[0].setColor(Pin.RED);
        game.getPlayerHoles()[1].setColor(Pin.BLUE);
        game.getPlayerHoles()[2].setColor(Pin.GREEN);
        game.getPlayerHoles()[3].setColor(Pin.YELLOW);

        game.getCombination()[0].setColor(Pin.GREEN);
        game.getCombination()[1].setColor(Pin.BLUE);
        game.getCombination()[2].setColor(Pin.RED);
        game.getCombination()[3].setColor(Pin.PURPLE);

        game.evaluate();

        for (int i = 0; i < 4; i++){
            assertTrue(game.getEvaluationHoles()[i].getColor() == result[i]);
        }
    }

   @Test
    public void putPinTest(){
        game.fillUpHoles();
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

        game.putPin(pin, index);

        assertTrue(game.getPlayerHoles()[index].getColor() == pin);
    }

}
