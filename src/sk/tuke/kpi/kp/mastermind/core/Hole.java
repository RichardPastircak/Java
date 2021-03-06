package sk.tuke.kpi.kp.mastermind.core;

public class Hole {
    private final Pin pin;

    public Hole() {
        pin = new Pin(PinColor.EMPTY);
    }

    public Pin getPin() {
        return pin;
    }
}
