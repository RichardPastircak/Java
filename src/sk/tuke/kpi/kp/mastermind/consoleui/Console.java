package sk.tuke.kpi.kp.mastermind.consoleui;

import sk.tuke.kpi.kp.mastermind.core.Game;
import sk.tuke.kpi.kp.mastermind.core.GameState;
import sk.tuke.kpi.kp.mastermind.core.Hole;
import sk.tuke.kpi.kp.mastermind.core.PinColor;

import java.util.Scanner;
import java.util.regex.Pattern;


public class Console {
    //COLORS
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREY = "\u001B[37m";

    private static Console console = new Console();
    private Scanner scanner;

    private Console() {
        scanner = new Scanner(System.in);
    }

    public static Console getConsole() {
        return console;
    }

                                        /*others*/
    //Main game loop
    public void play(){
        System.out.println(ANSI_GREEN + "\nWELCOME TO MASTERMIND!" + ANSI_RESET);
        System.out.println("I am thinking 4 color combination, none of those colors are same. The color combination is represented by pins which can be: " + ANSI_RED + "RED" + ANSI_RESET + ", " + ANSI_BLUE + "BLUE" + ANSI_RESET + ", " + ANSI_GREEN + "GREEN" + ANSI_RESET + ", " + ANSI_YELLOW + "YELLOW" + ANSI_RESET + ", " + ANSI_PURPLE + "PURPLE " + ANSI_RESET + "and " + ANSI_CYAN +  "CYAN" + ANSI_RESET + ".\n" +
                "You have 9 rounds (attempts) to guess the combination. Your guess will be evaluated with" + ANSI_BLACK + " BLACK " + ANSI_RESET + "if you guessed the color and the position of the pin correctly" + ANSI_GREY + " GREY " + ANSI_RESET + "if you guessed only the color of the pin correctly and WHITE if you guessed none of those correctly.\n" +
                "Please remember, that the position of black pins, grey pins or white pins may has nothing in common with the the position of pins in your guess as in evaluation part the black pins will always be first first, the grey pins second and white pins last.\n" +
                "Good luck and Have fun.\n" +
                ANSI_GREEN + "The Game Bagins!" + ANSI_RESET);

        do {
            System.out.printf("\nRound " + (Game.getGame().getRound()+1) + ": ");
            show(Game.getGame().getPlayerHoles(), 4);
            System.out.println("\n");
            handleInput();
        }while (Game.getGame().getGameState() == GameState.PLAYING);

        if(Game.getGame().getGameState() == GameState.WIN){
            System.out.println(ANSI_GREEN + "Congrats you WON!!!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "You FAILED!" + ANSI_RESET);
        }
    }

    //Prints various type of fields during game
    public void show(Hole[] holeField, int range){
        for(int i = 0; i < range; i++){
           System.out.printf("%s ", printPins(holeField[i].getPin().getColor()));
        }
    }

    //used to print History field
    public void show(Hole[] holeField, int begin, int end){
        for(int i = begin; i < end; i++){
            System.out.printf("%s ", printPins(holeField[i].getPin().getColor()));
        }
    }

    //Converts ColorPin enums to colored 0 returns ? if something impossible happens
    public String printPins(PinColor pinColor){
        switch (pinColor){
            case RED: return (ANSI_RED + "O" + ANSI_RESET);
            case BLUE: return (ANSI_BLUE + "O" + ANSI_RESET);
            case GREEN: return (ANSI_GREEN + "O" + ANSI_RESET);
            case YELLOW: return (ANSI_YELLOW + "O" + ANSI_RESET);
            case PURPLE: return (ANSI_PURPLE + "O" + ANSI_RESET);
            case CYAN: return (ANSI_CYAN + "O" + ANSI_RESET);
            case BLACK: return (ANSI_BLACK + "O" + ANSI_RESET);
            case GREY: return (ANSI_GREY + "O" + ANSI_RESET);
            case EMPTY: return "O";
            default: break;
        }
        return "?";
    }

    //Dealing with all user input, loads it and take action according to user wish
    public void handleInput(){
        //basic game isntructions
        String input;
        System.out.println("Following options are avaible for you:\n" +
                "Add colored pin to certain position [A + Collor's first character + Position where you wish" + " to place pin], example: AB1 <- places blue pin to first spot.\n" +
                "The Colors: " + ANSI_RED + "R " + ANSI_BLUE + "B " + ANSI_GREEN + "G " + ANSI_YELLOW + "Y " + ANSI_PURPLE + "P " + ANSI_CYAN + "C" + ANSI_RESET + ".\n" +
                " Ask for evaluation of your guess when you filled all places with pins [E].\n" +
                " Show history of certain attempt/s:\n" +
                "   [H + number of round, from which you wished to see history], for example: H1 <- shows your guess and evaluation from round 1.\n" +
                "   [H + number of first round, from which you want too see history + - + number of last round (included), till which you want to see history, for example: H2-3 <- shows yoru guesses and evalution from rounds 2 and 3.\n" +
                "What action do you wish to take?");

        do {
            input = scanner.nextLine().toUpperCase();

            //Adding Pin
            if (Pattern.matches("A[RBGYPC][1-4]", input)) {
                int index = input.charAt(2) - '1';
                switch (input.charAt(1)) {
                    case 'R':
                        Game.getGame().putPin(PinColor.RED, index);
                        break;
                    case 'B':
                        Game.getGame().putPin(PinColor.BLUE, index);
                        break;
                    case 'G':
                        Game.getGame().putPin(PinColor.GREEN, index);
                        break;
                    case 'Y':
                        Game.getGame().putPin(PinColor.YELLOW, index);
                        break;
                    case 'P':
                        Game.getGame().putPin(PinColor.PURPLE, index);
                        break;
                    case 'C':
                        Game.getGame().putPin(PinColor.CYAN, index);
                        break;
                    default:
                        break;
                }

                System.out.printf("Round " + (Game.getGame().getRound()+1) + ": ");
                show(Game.getGame().getPlayerHoles(), 4);
                System.out.printf("\n");
            }

            //Evaluate guess
            else if (Pattern.matches("E", input)) {
                int emptyHoles = 0;
                for (Hole hole : Game.getGame().getPlayerHoles()) {
                    if (hole.getPin().getColor() == PinColor.EMPTY) {
                        emptyHoles++;
                    }
                }

                if (emptyHoles > 0) {
                    if (emptyHoles == 1)
                        System.out.println("It seems there is " + emptyHoles + " empty hole, please fill it so I can evaluate your guess.");
                    else
                        System.out.println("It seems there are " + emptyHoles + " empty holes, please fill them so I can evaluate your guess.");
                } else {
                    System.out.printf("\nRound " + (Game.getGame().getRound() + 1) + ": ");
                    show(Game.getGame().getPlayerHoles(), 4);
                    Game.getGame().evaluate();

                    System.out.printf(" | ");
                    show(Game.getGame().getEvaluationHoles(), 4);
                    System.out.println("\n\n-------------------------------------------------------------------------------------------");

                    Game.getGame().reset();
                }
            }

            //shows one line of history
            else if (Pattern.matches("H[1-8]", input)) {
                int index = input.charAt(1) - '1';
                if (index >= Game.getGame().getRound()) {
                    System.out.println("This round haven't been played yet, please choose different round or action.");
                } else {
                    printHistory(input.charAt(1) - '1');
                }
            }

            //shows multiple line of history
            else if (Pattern.matches("H[1-8]-[1-8]", input)) {
                int index1 = input.charAt(1) - '1';
                int index2 = input.charAt(3) - '1';

                if (index1 == index2) {
                    printHistory(index1);
                } else if (index1 >= Game.getGame().getRound() || index2 >= Game.getGame().getRound()) {
                    System.out.println("At least one round from those you wished to display haven't been played yet, please choose different rounds or action.");
                } else {
                    for (int i = index1; i <= index2; i++) {
                        printHistory(i);
                    }
                }
            }

            //unrecognized input handler
            else {
                System.out.println("Sorry, I don't quite understand your actions. Could you said it again?");
            }
        } while (input.charAt(0) != 'E');
    }

    //formats printing of history
    public void printHistory(int index){
        System.out.printf("History - Round " + (index+1) + ": ");
        show(Game.getGame().getHistory()[index], 0, 4);
        System.out.printf(" | ");
        show(Game.getGame().getHistory()[index], 4, 8);
        System.out.printf("\n");
    }

}
