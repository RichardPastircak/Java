package sk.tuke.gamestudio.game.mastermind.core;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.mastermind.core.Hole;
import sk.tuke.gamestudio.game.mastermind.core.Pin;

import static org.junit.jupiter.api.Assertions.*;

public class HoleTest {
    @Test
    public void createHoleTest(){
        Hole hole = new Hole();
        assertTrue(hole.getColor() == Pin.EMPTY);
    }

    @Test
    public void setColorTest(){
        Hole hole = new Hole();
        hole.setColor(Pin.RED);
        assertTrue(hole.getColor() == Pin.RED);
    }
}
