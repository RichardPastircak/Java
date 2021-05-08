package sk.tuke.gamestudio.game.mastermind.core;


import java.util.Random;

public class Game {
    private int currentRound;
    private final int numOfRounds;
    private final int numOfHoles;
    private final int numOfHistoryHoles;
    private GameState gameState = GameState.PLAYING;
    private Hole[] combination;
    private Hole[] playerHoles;
    private Hole[] evaluationHoles;
    private Hole[][] history;

    public Game() {
        currentRound = 1;
        numOfRounds = 9;
        numOfHoles = 4;
        numOfHistoryHoles = 2*numOfHoles;

        combination = new Hole[numOfHoles];
        playerHoles = new Hole[numOfHoles];
        evaluationHoles = new Hole[numOfHoles];
        history = new Hole[numOfRounds][numOfHistoryHoles];


        fillUpHoles();
        generateCombination();
    }

                                        /* Getters and Setters*/

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

    public int getCurrentRound() {
        return currentRound;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public int getNumOfHoles() {
        return numOfHoles;
    }

    public int getNumOfHistoryHoles() {
        return numOfHistoryHoles;
    }

    /*------------------------Other functions-----------------------------------*/
    public void fillUpHoles(){
        for (int i = 0; i < numOfHoles; i++){
            playerHoles[i] = new Hole();
            evaluationHoles[i] = new Hole();
            combination[i] = new Hole();
        }

        for(int i = 0; i < numOfRounds; i++){
            for(int j = 0; j < numOfHistoryHoles; j++){
                history[i][j] = new Hole();
            }
        }
    }

    //generate random combination
    public void generateCombination() {
        Pin[] usedColors = new Pin[numOfHoles];

        for (Pin pin : usedColors){
            pin = Pin.EMPTY;
        }

        Pin newColor = Pin.EMPTY;
        Random rand = new Random();
        int i = 0;

        while(i < numOfHoles) {
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

            for(int j = 0; j < numOfHoles; j++){
                if(newColor == usedColors[j]){
                    break;
                }
                //is at the end, no match was found
                else if(j == numOfHoles-1){
                    combination[i].setColor(newColor);
                    usedColors[i] = newColor;
                    i++;
                }
            }
        }
    }

    //Reset round by setting up player's and evaluation's holes with empty pins
    public void reset(){
        for (int i = 0; i < numOfHoles; i++){
            playerHoles[i].setColor(Pin.EMPTY);
            evaluationHoles[i].setColor(Pin.EMPTY);
        }
    }

    //evaluates player score
    public void evaluate(){
        int position_counter = 0;
        int[] markingCombinationHoles = new int[numOfHoles];
        for(int i : markingCombinationHoles){
            i = 0;
        }


        //add BLACK pins
        for(int i = 0; i < numOfHoles; i++){
            if(playerHoles[i].getColor() == combination[i].getColor()){
                evaluationHoles[position_counter].setColor(Pin.BLACK);
                markingCombinationHoles[i] = 1;
                position_counter++;
            }
        }

        //add WHITE PINS
        for(int i = 0; i < numOfHoles; i++){
            for(int j = 0; j < numOfHoles; j++){
                if(playerHoles[i].getColor() == combination[j].getColor() && markingCombinationHoles[j] != 1){
                    evaluationHoles[position_counter].setColor(Pin.GREY);
                    markingCombinationHoles[j] = 1;
                    position_counter++;
                }
            }
        }

        //fill history data with player tip and evaluation
        for (int i = 0; i < numOfHistoryHoles; i++){
            if (i < numOfHistoryHoles/2) {
                history[currentRound][i].setColor(playerHoles[i].getColor());
            }
            else {
                history[currentRound][i].setColor(evaluationHoles[i - numOfHistoryHoles/2].getColor());
            }
        }

        //gamestate check
        currentRound++;
        if(currentRound == numOfRounds){
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