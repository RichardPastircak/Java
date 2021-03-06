package sk.tuke.kpi.kp.mastermind.core;

public class Pin {
    private PinColor color;

    public Pin(PinColor color) {
        this.color = color;
    }

    public PinColor getColor() {
        return color;
    }

    public void setColor(PinColor color) {
        this.color = color;
    }
}
