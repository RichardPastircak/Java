package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void allTest() {
        CommentServiceJDBCTest commentServiceJDBCTest = new CommentServiceJDBCTest();
        commentServiceJDBCTest.testAddComment();

        GameTest gameTest = new GameTest();
        gameTest.testEvaluate();
        gameTest.testReset();
        gameTest.testFillingHoles();
        gameTest.testPutPin();
        gameTest.testGenerateCombination();

        HoleTest holeTest = new HoleTest();
        holeTest.testCreateHole();
        holeTest.testSetColor();

        RatingServiceJDBCTest ratingServiceJDBCTest = new RatingServiceJDBCTest();
        ratingServiceJDBCTest.testSetRating();

        ScoreServiceJDBCTest scoreServiceJDBCTest = new ScoreServiceJDBCTest();
        scoreServiceJDBCTest.testAddScore();
    }
}
