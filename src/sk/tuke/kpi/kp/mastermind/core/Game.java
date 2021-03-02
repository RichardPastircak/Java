package sk.tuke.kpi.kp.mastermind.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    int round;
    private List<Hole> combination;
    private List<Hole> playerHole;
    private List<Hole> evaluationHole;
    private Hole[][] history;

    public static Game game = new Game();

    private Game() {
        combination = new ArrayList<>(4);
        playerHole = new ArrayList<>(4);
        evaluationHole = new ArrayList<>(4);
        history = new Hole[9][8];
        round = 0;

        Random rand = new Random();
        for (int i = 0; i < 4; i++){
            int tmp = rand.nextInt(6);
            switch (tmp){
                case 0: combination.get(i).setPin(new Pin(PinColor.RED));
                        break;
                case 1: combination.get(i).setPin(new Pin(PinColor.BLUE));
                    break;
                case 2: combination.get(i).setPin(new Pin(PinColor.GREEN));
                    break;
                case 3: combination.get(i).setPin(new Pin(PinColor.YELLOW));
                    break;
                case 4: combination.get(i).setPin(new Pin(PinColor.PINK));
                    break;
                case 5: combination.get(i).setPin(new Pin(PinColor.BROWN));
                    break;
                default: break;
            }
        }
    }

    //Reset round by filling up player and evaluation HOles with new holes
    public void generate(){
        for (int i = 0; i < 4; i++){
            playerHole.remove(playerHole.get(i));
            playerHole.add(new Hole());
            evaluationHole.remove(evaluationHole.get(i));
            evaluationHole.add(new Hole());
        }
    }

    //evaluates player score
    public void evaluate(){
        int tmp = 0;

        //add BLACK pins
        for(int i = 0; i < 4; i++){
            if(playerHole.get(i).getPin().getColor() == combination.get(i).getPin().getColor()){
                evaluationHole.get(tmp).setPin(new Pin(PinColor.BLACK));
                tmp++;
            }
        }

        if(tmp == 4) {
            System.out.println("WON");
        }

        //add WHITE PINS
        for(int i = 0; i < 4; i++){
            for(Hole hole: combination){
                if(playerHole.get(i).getPin().getColor() == hole.getPin().getColor()){
                    evaluationHole.get(tmp).setPin(new Pin(PinColor.WHITE));
                    tmp++;
                }
            }
        }

        //fill history data
        for (int i = 0; i < 8; i+=2){
            history[round][i] = playerHole.get(i/2);
            history[round][i+1] = evaluationHole.get(i/2);
        }

        //gamestate check
        round++;
        if(round == 9){
            System.out.println("FAILED");
        }
    }

    //enables player to put pin in hole
    public void putPin(PinColor color, int index){
        playerHole.get(index).setPin(new Pin(color));
    }
}