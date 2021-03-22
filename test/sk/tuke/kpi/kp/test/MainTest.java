package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void allTest() {
        CommentServiceJDBCTest commentServiceJDBCTest = new CommentServiceJDBCTest();
        commentServiceJDBCTest.addCommentTest();
        commentServiceJDBCTest.getCommentsTest();
        commentServiceJDBCTest.resetTest();

        GameTest gameTest = new GameTest();
        gameTest.evaluateTest();
        gameTest.resetTest();
        gameTest.fillingHolesTest();
        gameTest.putPinTest();
        gameTest.generateCombinationTest();

        HoleTest holeTest = new HoleTest();
        holeTest.createHoleTest();
        holeTest.setColorTest();

        RatingServiceJDBCTest ratingServiceJDBCTest = new RatingServiceJDBCTest();
        ratingServiceJDBCTest.setRatingTest();
        ratingServiceJDBCTest.getAverageRatingTest();
        ratingServiceJDBCTest.resetTest();

        ScoreServiceJDBCTest scoreServiceJDBCTest = new ScoreServiceJDBCTest();
        scoreServiceJDBCTest.addScoreTest();
        scoreServiceJDBCTest.getTopScoresTest();
        scoreServiceJDBCTest.resetTest();
    }
}
