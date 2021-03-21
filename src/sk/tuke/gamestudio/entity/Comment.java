package sk.tuke.gamestudio.entity;


import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private String game;

    private String player;

    private String comment;

    private Timestamp commentedOn;

    public Comment(String game, String player, String comment, Timestamp commentedOn) {
        this.game = game;
        this.player = player;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Timestamp commentedOn) {
        this.commentedOn = commentedOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                ", commentedOn=" + commentedOn +
                '}';
    }
}
