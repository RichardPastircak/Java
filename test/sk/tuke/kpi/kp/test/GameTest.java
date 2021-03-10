package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sk.tuke.kpi.kp.mastermind.core.Game;
import sk.tuke.kpi.kp.mastermind.core.PinColor;

import java.util.Random;

public class GameTest {
    @Test
    public void testFillingHoles(){
        Game.getGame().fillUpHoles();
        for(int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getCombination()[i].getPin().getColor() == PinColor.EMPTY);
            assertTrue(Game.getGame().getEvaluationHoles()[i].getPin().getColor() == PinColor.EMPTY);
            assertTrue(Game.getGame().getPlayerHoles()[i].getPin().getColor() == PinColor.EMPTY);
        }

        for(int i =0; i < 9; i++){
            for(int j = 0; j < 8; j++){
                assertTrue(Game.getGame().getHistory()[i][j].getPin().getColor() == PinColor.EMPTY);
            }
        }
    }

    @Test
    public void testGenerateCombination(){
        Game.getGame().fillUpHoles();
        Game.getGame().generateCombination();

        for (int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getCombination()[i].getPin().getColor() != PinColor.EMPTY && Game.getGame().getCombination()[i].getPin().getColor() != PinColor.BLACK && Game.getGame().getCombination()[i].getPin().getColor() != PinColor.GREY);
            for(int j = i+1; j < 4; j++){
                assertTrue(Game.getGame().getCombination()[i] != Game.getGame().getCombination()[j]);
            }
        }
    }

    @Test
    public void testReset(){
        Game.getGame().fillUpHoles();

        for(int i = 0; i < 4; i++){
            Game.getGame().getEvaluationHoles()[i].getPin().setColor(PinColor.RED);
            Game.getGame().getPlayerHoles()[i].getPin().setColor(PinColor.RED);
        }

        Game.getGame().reset();
        for (int i = 0; i < 4; i++) {
            assertTrue(Game.getGame().getEvaluationHoles()[i].getPin().getColor() == PinColor.EMPTY);
            assertTrue(Game.getGame().getPlayerHoles()[i].getPin().getColor() == PinColor.EMPTY);
        }
    }



    @Test
    public void testEvaluate(){
        PinColor[] result = {PinColor.BLACK, PinColor.GREY, PinColor.GREY, PinColor.EMPTY};

        Game.getGame().fillUpHoles();
        //Game.getGame().generateCombination();
        Game.getGame().getPlayerHoles()[0].getPin().setColor(PinColor.RED);
        Game.getGame().getPlayerHoles()[1].getPin().setColor(PinColor.BLUE);
        Game.getGame().getPlayerHoles()[2].getPin().setColor(PinColor.GREEN);
        Game.getGame().getPlayerHoles()[3].getPin().setColor(PinColor.YELLOW);

        Game.getGame().getCombination()[0].getPin().setColor(PinColor.GREEN);
        Game.getGame().getCombination()[1].getPin().setColor(PinColor.BLUE);
        Game.getGame().getCombination()[2].getPin().setColor(PinColor.RED);
        Game.getGame().getCombination()[3].getPin().setColor(PinColor.PURPLE);

        Game.getGame().evaluate();

        for (int i = 0; i < 4; i++){
            assertTrue(Game.getGame().getEvaluationHoles()[i].getPin().getColor() == result[i]);
        }
    }

   @Test
    public void testPutPin(){
        Game.getGame().fillUpHoles();
        Random rand = new Random();

        int index = rand.nextInt(4);

        PinColor pinColor = PinColor.EMPTY;
        for (int i = 0; i < 4; i++) {
           int tmp = rand.nextInt(6);
           switch (tmp) {
               case 0:
                   pinColor = PinColor.RED;
                   break;
               case 1:
                   pinColor = PinColor.BLUE;
                   break;
               case 2:
                   pinColor = PinColor.GREEN;
                   break;
               case 3:
                   pinColor = PinColor.YELLOW;
                   break;
               case 4:
                   pinColor = PinColor.PURPLE;
                   break;
               case 5:
                   pinColor = PinColor.CYAN;
                   break;
               default:
                   break;
           }
       }

        Game.getGame().putPin(pinColor, index);

        assertTrue(Game.getGame().getPlayerHoles()[index].getPin().getColor() == pinColor);
    }

}
