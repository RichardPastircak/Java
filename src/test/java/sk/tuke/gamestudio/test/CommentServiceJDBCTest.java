package sk.tuke.gamestudio.test;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceJDBC;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceJDBCTest {
    @Test
    public void addCommentTest(){
        CommentService commentService = new CommentServiceJDBC();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        commentService.addComment(new Comment("Test", "jozoCommentTest", "test", timestamp));
        int num = 0;

        for (Comment comment : commentService.getComments("Test")){
            if (comment.getPlayer().equals("jozoCommentTest") && comment.getComment().equals("test") && comment.getCommentedOn().compareTo(timestamp) == 0){
                num++;
            }
        }

        assertEquals(1, num);
    }

    @Test
    public void getCommentsTest(){
        CommentService commentService = new CommentServiceJDBC();
        commentService.reset();
        commentService.addComment(new Comment("Test", "jozoRatingTest", "pokus", new Timestamp(System.currentTimeMillis())));
        commentService.addComment(new Comment("Test", "jozoRatingTest", "pokus", new Timestamp(System.currentTimeMillis())));

        assertEquals(commentService.getComments("Test").size(), 2);
    }


    @Test
    public void resetTest(){
        CommentService commentService = new CommentServiceJDBC();
        commentService.addComment(new Comment("Test", "jozoRatingTest", "pokus", new Timestamp(System.currentTimeMillis())));
        commentService.reset();
        assertEquals(commentService.getComments("Test").size(), 0);
    }
}
