package sk.tuke.gamestudio.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.sql.Timestamp;

@Entity
@NamedQuery( name = "Rating.deletePlayerRating",
        query = "DELETE FROM Rating r WHERE r.game=:game AND r.player=:player")

@NamedQuery( name = "Rating.getRating",
        query = "SELECT r.rating FROM Rating r WHERE r.game=:game AND r.player=:player")

@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game=:game")

@NamedQuery( name = "Rating.resetRatings",
        query = "DELETE FROM Rating ")

public class Rating {
    @Id
    @GeneratedValue
    private int identif;

    private String game;
    private String player;
    private int rating;
    private Timestamp ratedOn;

    public Rating() {
    }

    public Rating(String game, String player, int rating, Timestamp ratedOn) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public int getIdentif() {
        return identif;
    }

    public void setIdentif(int identif) {
        this.identif = identif;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(Timestamp ratedOn) {
        this.ratedOn = ratedOn;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + ratedOn +
                '}';
    }
}
