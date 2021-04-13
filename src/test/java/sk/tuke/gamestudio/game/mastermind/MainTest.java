package sk.tuke.gamestudio.game.mastermind;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.mastermind.core.GameTest;
import sk.tuke.gamestudio.game.mastermind.core.HoleTest;
import sk.tuke.gamestudio.service.CommentServiceJDBCTest;
import sk.tuke.gamestudio.service.RatingServiceJDBCTest;
import sk.tuke.gamestudio.service.ScoreServiceJDBCTest;

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
