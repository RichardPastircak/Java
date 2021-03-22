package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import sk.tuke.kpi.kp.mastermind.core.Hole;
import sk.tuke.kpi.kp.mastermind.core.Pin;

import static org.junit.jupiter.api.Assertions.*;

public class HoleTest {
    @Test
    public void testCreateHole(){
        Hole hole = new Hole();
        assertTrue(hole.getColor() == Pin.EMPTY);
    }

    @Test
    public void testSetColor(){
        Hole hole = new Hole();
        hole.setColor(Pin.RED);
        assertTrue(hole.getColor() == Pin.RED);
    }
}
