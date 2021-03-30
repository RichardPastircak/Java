package sk.tuke.gamestudio.game.mastermind.core;

public class Hole {
    private Pin color;

    public Hole() {
        color = Pin.EMPTY;
    }

    public Pin getColor() {
        return color;
    }
    public void setColor(Pin color) {
        this.color = color;
    }
}
