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
    private List<Hole> playerHole;
    private List<Hole> evaluationHole;



    @Test
    public void generate(){
        playerHole = new ArrayList<>(4);
        evaluationHole = new ArrayList<>(4);
        /*for(int i = 0; i < 4; i++){
            playerHole.get(i).setPin(new Pin(PinColor.RED));
            evaluationHole.get(i).setPin(new Pin(PinColor.RED));
        }*/

        for (int i = 0; i < 4; i++){
            playerHole.remove(playerHole.get(i));
            playerHole.add(new Hole());
            evaluationHole.remove(evaluationHole.get(i));
            evaluationHole.add(new Hole());
        }

        for (int i = 0; i < 4; i++) {
            assertTrue(playerHole.get(i).getPin().getColor() == PinColor.EMPTY);
            assertTrue(evaluationHole.get(i).getPin().getColor() == PinColor.EMPTY);
        }
    }

}
