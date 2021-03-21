package sk.tuke.gamestudio.entity;


import java.sql.Timestamp;

public class Rating {
    private String game;

    private String player;

    private int rating;

    private Timestamp ratedOn;

    public Rating(String game, String player, int rating, Timestamp ratedOn) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedOn = ratedOn;
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
