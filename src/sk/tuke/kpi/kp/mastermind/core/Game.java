package sk.tuke.kpi.kp.mastermind.core;


import java.util.Random;

public class Game {
    int round;
    private GameState gameState = GameState.PLAYING;
    private Hole[] combination;
    private Hole[] playerHoles;
    private Hole[] evaluationHoles;
    private Hole[][] history;

    private static Game game = new Game();

    private Game() {
        combination = new Hole[4];
        playerHoles = new Hole[4];
        evaluationHoles = new Hole[4];
        history = new Hole[9][8];
        round = 8;

        fillUpHoles();
        generateCombination();
    }

                                        /* Getters and Setters*/

    public static Game getGame() {
        return game;
    }

    public Hole[] getCombination() {
        return combination;
    }

    public Hole[] getPlayerHoles() {
        return playerHoles;
    }

    public Hole[] getEvaluationHoles() {
        return evaluationHoles;
    }

    public Hole[][] getHistory() {
        return history;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getRound() {
        return round;
    }

    /*------------------------Other functions-----------------------------------*/
    public void fillUpHoles(){
        for (int i = 0; i < 4; i++){
            playerHoles[i] = new Hole();
            evaluationHoles[i] = new Hole();
            combination[i] = new Hole();
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 8; j++){
                history[i][j] = new Hole();
            }
        }
    }

    //generate random combination
    public void generateCombination() {
        Pin[] usedColors = {Pin.EMPTY, Pin.EMPTY, Pin.EMPTY, Pin.EMPTY};
        Pin newColor = Pin.EMPTY;
        Random rand = new Random();
        int i = 0;

        while(i < 4) {
            int tmp = rand.nextInt(6);
            switch (tmp) {
                case 0:
                    newColor = Pin.RED;
                    break;
                case 1:
                    newColor = Pin.BLUE;
                    break;
                case 2:
                    newColor = Pin.GREEN;
                    break;
                case 3:
                    newColor = Pin.YELLOW;
                    break;
                case 4:
                    newColor = Pin.PURPLE;
                    break;
                case 5:
                    newColor = Pin.CYAN;
                    break;
                default:
                    break;
            }

            for(int j = 0; j < 4; j++){
                if(newColor == usedColors[j]){
                    break;
                }
                if(j == 3){
                    combination[i].setColor(newColor);
                    usedColors[i] = newColor;
                    i++;
                }
            }
        }
    }

    //Reset round by setting up player's and evaluation's holes with empty pins
    public void reset(){
        for (int i = 0; i < 4; i++){
            playerHoles[i].setColor(Pin.EMPTY);
            evaluationHoles[i].setColor(Pin.EMPTY);
        }
    }

    //evaluates player score
    public void evaluate(){
        int position_counter = 0;
        int[] markingCombinationHoles = {0,0,0,0};


        //add BLACK pins
        for(int i = 0; i < 4; i++){
            if(playerHoles[i].getColor() == combination[i].getColor()){
                evaluationHoles[position_counter].setColor(Pin.BLACK);
                markingCombinationHoles[i] = 1;
                position_counter++;
            }
        }

        //add WHITE PINS
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(playerHoles[i].getColor() == combination[j].getColor() && markingCombinationHoles[j] != 1){
                    evaluationHoles[position_counter].setColor(Pin.GREY);
                    markingCombinationHoles[j] = 1;
                    position_counter++;
                }
            }
        }

        //fill history data with player tip and evaluation
        for (int i = 0; i < 8; i++){
            if (i < 4) {
                history[round][i].setColor(playerHoles[i].getColor());
            }
            else {
                history[round][i].setColor(evaluationHoles[i - 4].getColor());
            }
        }

        //gamestate check
        round++;
        if(round == 9){
            gameState = GameState.LOSE;
        }

        for(Hole hole: evaluationHoles){
            if(hole.getColor() != Pin.BLACK){
                return;
            }
        }
        gameState = GameState.WIN;
    }

    //enables player to put pin in hole
    public void putPin(Pin color, int index){
        playerHoles[index].setColor(color);
    }
}