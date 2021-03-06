package sk.tuke.kpi.kp.mastermind.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    int round;
    private Hole[] combination;
    private Hole[] playerHole;
    private Hole[] evaluationHole;
    private Hole[][] history;

    private static Game game = new Game();;

    private Game() {
        combination = new Hole[4];
        playerHole = new Hole[4];
        evaluationHole = new Hole[4];
        history = new Hole[9][8];
        round = 0;


        Random rand = new Random();
        for (int i = 0; i < 4; i++){
            int tmp = rand.nextInt(6);
            switch (tmp){
                case 0: combination[i].getPin().setColor(PinColor.RED);
                        break;
                case 1: combination[i].getPin().setColor(PinColor.BLUE);
                    break;
                case 2: combination[i].getPin().setColor(PinColor.GREEN);
                    break;
                case 3: combination[i].getPin().setColor(PinColor.YELLOW);
                    break;
                case 4: combination[i].getPin().setColor(PinColor.PINK);
                    break;
                case 5: combination[i].getPin().setColor(PinColor.BROWN);
                    break;
                default: break;
            }
        }
    }

                                        /* Getters and Setters*/
    public static Game getGame() {
        return game;
    }
                                        /*Other functions*/

    //Reset round by setting up player's and evaluation's holes with empty pins
    public void generate(){
        for (int i = 0; i < 4; i++){
            playerHole[i].getPin().setColor(PinColor.EMPTY);
            evaluationHole[i].getPin().setColor(PinColor.EMPTY);
        }
    }

    //evaluates player score
    public void evaluate(){
        int position_counter = 0;
        int[] markingCombinationHoles = {0,0,0,0};


        //add BLACK pins
        for(int i = 0; i < 4; i++){
            if(playerHole[i].getPin().getColor() == combination[i].getPin().getColor()){
                evaluationHole[position_counter].getPin().setColor(PinColor.BLACK);
                markingCombinationHoles[i] = 1;
                position_counter++;
            }
        }

        if(position_counter == 4) {
            System.out.println("WON");
        }

        //add WHITE PINS
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(playerHole[i].getPin().getColor() == combination[j].getPin().getColor() && markingCombinationHoles[j] != 1){
                    evaluationHole[position_counter].getPin().setColor(PinColor.WHITE);
                    markingCombinationHoles[j] = 1;
                    position_counter++;
                }
            }
        }

        //fill history data with player tip and evaluation
        for (int i = 0; i < 8; i++){
            if (i < 4) {
                history[round][i] = playerHole[i % 4];
            }
            else {
                history[round][i] = evaluationHole[i % 4];
            }
        }

        //gamestate check
        round++;
        if(round == 9){
            System.out.println("FAILED");
        }
    }

    //enables player to put pin in hole
    public void putPin(PinColor color, int index){
        playerHole[index].getPin().setColor(color);
    }
}