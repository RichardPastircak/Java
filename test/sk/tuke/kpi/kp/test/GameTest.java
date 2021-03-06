package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sk.tuke.kpi.kp.mastermind.core.Hole;
import sk.tuke.kpi.kp.mastermind.core.Pin;
import sk.tuke.kpi.kp.mastermind.core.PinColor;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTest {
    Random random = new Random();
    private Hole[] playerHole = new Hole[4];
    private Hole[] evaluationHole = new Hole[4];
    private Hole[] combination = new Hole[4];

    public void fillUpHoles(){
        for (int i = 0; i < 4; i++){
            playerHole[i] = new Hole();
            evaluationHole[i] = new Hole();
            combination[i] = new Hole();
        }
    }

    @Test
    public void generate(){
        fillUpHoles();

        for(int i = 0; i < 4; i++){
            playerHole[i].getPin().setColor(PinColor.RED);
            evaluationHole[i].getPin().setColor(PinColor.RED);
        }

        for (int i = 0; i < 4; i++){
            playerHole[i].getPin().setColor(PinColor.EMPTY);
            evaluationHole[i].getPin().setColor(PinColor.EMPTY);
        }

        for (int i = 0; i < 4; i++) {
            assertTrue(playerHole[i].getPin().getColor() == PinColor.EMPTY);
            assertTrue(evaluationHole[i].getPin().getColor() == PinColor.EMPTY);
        }
    }

    @Test
    public void evaluate(){
        int position_counter = 0;
        int[] markingCombinationHoles = {0,0,0,0};

        fillUpHoles();
        PinColor[] result = {PinColor.BLACK,PinColor.WHITE,PinColor.WHITE, PinColor.EMPTY};
        playerHole[0].getPin().setColor(PinColor.RED);
        playerHole[1].getPin().setColor(PinColor.BLUE);
        playerHole[2].getPin().setColor(PinColor.GREEN);
        playerHole[3].getPin().setColor(PinColor.YELLOW);
        combination[0].getPin().setColor(PinColor.YELLOW);
        combination[1].getPin().setColor(PinColor.BLUE);
        combination[2].getPin().setColor(PinColor.RED);
        combination[3].getPin().setColor(PinColor.PINK);

        for(int i = 0; i < 4; i++){
            if(playerHole[i].getPin().getColor() == combination[i].getPin().getColor()){
                evaluationHole[position_counter].getPin().setColor(PinColor.BLACK);
                markingCombinationHoles[i] = 1;
                position_counter++;
            }
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(playerHole[i].getPin().getColor() == combination[j].getPin().getColor() && markingCombinationHoles[j] != 1){
                    evaluationHole[position_counter].getPin().setColor(PinColor.WHITE);
                    markingCombinationHoles[j] = 1;
                    position_counter++;
                }
            }
        }

        for (int i = 0; i < 4; i++){
            assertTrue(evaluationHole[i].getPin().getColor() == result[i]);
        }
    }

    @Test
    public void testPutPin(){
        fillUpHoles();
        for(int i = 0; i < 4; i++){
            int tmp = random.nextInt(6);
                switch (tmp){
                    case 0: putPin(PinColor.RED, i);
                        break;
                    case 1: putPin(PinColor.BLUE, i);
                        break;
                    case 2: putPin(PinColor.GREEN, i);
                        break;
                    case 3: putPin(PinColor.YELLOW, i);
                        break;
                    case 4: putPin(PinColor.PINK, i);
                        break;
                    case 5: putPin(PinColor.BROWN, i);
                        break;
                    default: break;
                }
        }
    }

    public void putPin(PinColor color, int index){
        playerHole[index].getPin().setColor(color);
        assertTrue(playerHole[index].getPin().getColor() == color);
    }

}
