package sk.tuke.gamestudio.test;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;
import sk.tuke.gamestudio.entity.Rating;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Random;

public class RatingServiceJDBCTest {
    @Test
    public void setRatingTest(){
        RatingService ratingService = new RatingServiceJDBC();
        ratingService.reset();
        int num = new Random().nextInt(10);
        ratingService.setRating(new Rating("Test", "jozoRatingTest", num, new Timestamp(System.currentTimeMillis())));

        assertEquals(num, ratingService.getRating("Test", "jozoRatingTest"));
    }

    @Test
    public void getAverageRatingTest(){
        RatingService ratingService = new RatingServiceJDBC();
        ratingService.reset();
        ratingService.setRating(new Rating("Test", "jozoRatingTest1", 10, new Timestamp(System.currentTimeMillis())));
        ratingService.setRating(new Rating("Test", "jozoRatingTest2", 8, new Timestamp(System.currentTimeMillis())));

        assertEquals(ratingService.getAverageRating("Test"), 9);
    }

    @Test
    public void resetTest(){
        RatingService ratingService = new RatingServiceJDBC();
        ratingService.setRating(new Rating("Test", "jozoRatingTest", 10, new Timestamp(System.currentTimeMillis())));
        ratingService.reset();
        assertTrue(ratingService.getRating("Test", "jozoRatingTest") != 10);
    }
}
