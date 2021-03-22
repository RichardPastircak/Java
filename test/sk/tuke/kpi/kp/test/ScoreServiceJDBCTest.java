package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceJDBCTest {
    @Test
    public void testAddScore(){
        ScoreService scoreService = new sk.tuke.gamestudio.service.ScoreServiceJDBC();
        List<Score> scoresBefore = scoreService.getTopScores("Test");
        scoreService.addScore(new Score("Test", "jozoUT", 1000, new Timestamp(System.currentTimeMillis())));
        List<Score> scoresAfter = scoreService.getTopScores("Test");

        assertEquals(scoresAfter.size() - scoresBefore.size(), 1);
    }
}
