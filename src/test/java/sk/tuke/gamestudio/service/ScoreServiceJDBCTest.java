package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.service.ScoreService;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;
import sk.tuke.gamestudio.entity.Score;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceJDBCTest {
    @Test
    public void addScoreTest(){
        ScoreService scoreService = new ScoreServiceJDBC();
        List<Score> scoresBefore = scoreService.getTopScores("Test");
        scoreService.addScore(new Score("Test", "jozoUT", 1000, new Timestamp(System.currentTimeMillis())));
        List<Score> scoresAfter = scoreService.getTopScores("Test");

        assertEquals(scoresAfter.size() - scoresBefore.size(), 1);
    }

    @Test
    public void getTopScoresTest(){
        ScoreService scoreService = new ScoreServiceJDBC();
        scoreService.reset();
        scoreService.addScore(new Score("Test", "jozoRatingTest", 100, new Timestamp(System.currentTimeMillis())));
        scoreService.addScore(new Score("Test", "jozoRatingTest", 101, new Timestamp(System.currentTimeMillis())));

        assertEquals(scoreService.getTopScores("Test").size(), 2);
    }

    @Test
    public void resetTest(){
        ScoreService scoreService = new ScoreServiceJDBC();
        scoreService.addScore(new Score("Test", "jozoRatingTest", 100, new Timestamp(System.currentTimeMillis())));
        scoreService.reset();
        assertEquals(scoreService.getTopScores("Test").size(), 0);
    }
}
