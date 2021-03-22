package sk.tuke.kpi.kp.test;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;
import static org.junit.jupiter.api.Assertions.*;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.sql.Timestamp;
import java.util.Random;

public class RatingServiceJDBCTest {
    @Test
    public void testSetRating(){
        RatingService ratingService = new RatingServiceJDBC();
        int num = new Random().nextInt(10);
        ratingService.setRating(new Rating("Test", "jozoRatingTest", num, new Timestamp(System.currentTimeMillis())));

        assertEquals(num, ratingService.getRating("Test", "jozoRatingTest"));
    }
}
