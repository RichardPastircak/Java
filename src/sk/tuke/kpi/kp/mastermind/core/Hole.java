package sk.tuke.kpi.kp.mastermind.core;

public class Hole {
    private Pin pin;

    public Hole() {
        pin = new Pin(PinColor.EMPTY);
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }
}
