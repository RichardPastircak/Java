package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.kpi.kp.mastermind.consoleui.Console;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceJDBC implements RatingService{
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT rating FROM rating WHERE game = ? AND player = ?";
    public static final String SELECT_FOR_AVERAGESCORE = "SELECT rating FROM rating WHERE game = ?";
    public static final String DELETE = "DELETE FROM rating";
    public static final String DELETE_COLUMN = "DELETE FROM rating WHERE game = ? AND player = ?";
    public static final String INSERT = "INSERT INTO rating (game, player, rating, ratedOn) VALUES (?, ?, ?, ?)";

    //inserts new rating to database (only one rating per player)
    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT);
             PreparedStatement selectStatement = connection.prepareStatement(SELECT)
        ){
            statement.setString(1, rating.getGame());
            statement.setString(2, rating.getPlayer());
            statement.setInt(3, rating.getRating());
            statement.setTimestamp(4, rating.getRatedOn());

            selectStatement.setString(1, rating.getGame());
            selectStatement.setString(2, rating.getPlayer());

            try (ResultSet rs = selectStatement.executeQuery();
            ){
                if (rs.next()){
                    PreparedStatement deleteStatement = connection.prepareStatement(DELETE_COLUMN);
                    deleteStatement.setString(1, rating.getGame());
                    deleteStatement.setString(2, rating.getPlayer());
                    deleteStatement.executeUpdate();
                }
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RatingException("Problem setting rating", e);
        }
    }

    //gets average rating of game from all players
    @Override
    public int getAverageRating(String game) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_FOR_AVERAGESCORE)
        ){

            statement.setString(1, game);
            try(ResultSet rs = statement.executeQuery()) {
                int averageRating = 0;
                int numOfRatings = 0;
                while (rs.next()){
                    averageRating += rs.getInt(1);
                    numOfRatings++;
                }

                return averageRating / numOfRatings;
            }
        }catch (SQLException e){
            throw new RatingException("Problem getting average rating", e);
        }
    }

    //gets rating of certain player returns rating score or -1 if combination of game and player wasnt found
    @Override
    public int getRating(String game, String player) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);
            statement.setString(2, player);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RatingException("Problem getting rating", e);
        }
    }

    //delets content of ratin table
    @Override
    public void reset() throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new RatingException("Problem deleting rating", e);
        }
    }
}
