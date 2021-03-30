package sk.tuke.gamestudio.game.mastermind;

import sk.tuke.gamestudio.game.mastermind.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.mastermind.core.Game;

public class Mastermind {
    public static void main(String[] args) {
      ConsoleUI consoleUI = new ConsoleUI(new Game());
      consoleUI.play();
    }
}
