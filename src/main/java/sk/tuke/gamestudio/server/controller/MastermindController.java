package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.mastermind.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.mastermind.core.Game;
import sk.tuke.gamestudio.game.mastermind.core.GameState;
import sk.tuke.gamestudio.game.mastermind.core.Hole;
import sk.tuke.gamestudio.game.mastermind.core.Pin;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/mastermind")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MastermindController {
    private String userName;
    private String color;
    private boolean evaluate;
    private boolean logged;
    private Game game = new Game();
    private int round = game.getCurrentRound();
    private GameState gameState;
    private int score;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;

    @RequestMapping
    public String mastermind(@RequestParam(required = false) String name, @RequestParam(required = false) String password ,Model model){
        fillModel(model);
        try {
            userName = name;
            if((userName.compareTo("admin") == 0 && password.compareTo("admin") == 0) || userName.compareTo("jožko") == 0 && password.compareTo("mrkvička") == 0){
                logged = true;
            }
            else{
                return "redirect:/";
            }
        }
        catch (Exception e){
            userName = "Guest";
            logged = false;
        }
        evaluate = true;

        return "thymeleaf";
    }

    @RequestMapping("/changecolor")
    public String color(@RequestParam(required = false) String color, @RequestParam(required = false) String position, Model model){
        fillModel(model);

        try {
            this.color = color;
            game.putPin(Pin.values()[switchColor()], Integer.parseInt(position));
        }
        catch (Exception e){}

        return "thymeleaf";
    }

    @RequestMapping("/evaluate")
    public String evaluate(Model model){
        fillModel(model);
        if (evaluate) {
            for(Hole hole : game.getPlayerHoles()){
                if (hole.getColor() == Pin.EMPTY){
                    return "thymeleaf";
                }
            }
            game.evaluate();
            gameState = game.getGameState();

            if(gameState != GameState.PLAYING){
                //calculate score
                ConsoleUI consoleUI = new ConsoleUI(game);
                int[] bestRound = consoleUI.findBestRound();
                score = (10 - game.getCurrentRound()) * (bestRound[0]*10 + bestRound[1]*5);

                //add score to databases
               if (isLogged()){
                    scoreService.addScore(new Score("Mastermind", userName, score, new Timestamp(System.currentTimeMillis())));
                }

                fillModel(model);

                return "endGame";
            }

        }
        else{
            game.reset();
            round = game.getCurrentRound();
        }

        evaluate = !evaluate;
        return "thymeleaf";
    }

    @RequestMapping("/new")
    public String newGame(Model model){
        game = new Game();
        round = game.getCurrentRound();
        evaluate = true;
        fillModel(model);

        return "thymeleaf";
    }

    @RequestMapping("/rating")
        public String rate(@RequestParam(required = false) String endRating, @RequestParam(required = false) String thmlfRating, Model model){
            try {
                int rating;

                if(endRating != null) {
                    rating = Integer.parseInt(endRating);
                    if (rating >= 1 && rating <= 10) {
                        ratingService.setRating(new Rating("Mastermind", userName, rating, new Timestamp(System.currentTimeMillis())));
                    }

                    fillModel(model);
                    return "endGame";
                }
                else {
                    rating = Integer.parseInt(thmlfRating);
                    if (rating >= 1 && rating <=10){
                        ratingService.setRating(new Rating("Mastermind", userName, rating, new Timestamp(System.currentTimeMillis())));
                    }

                    fillModel(model);
                    return "thymeleaf";
                }
            }
            catch (Exception e){
                fillModel(model);
                return (endRating != null) ? "endGame" : "thymeleaf";
            }
        }

     @RequestMapping("/comment")
     public String comment(@RequestParam(required = false) String endComment, @RequestParam(required = false) String thmlfComment, Model model){

        if(endComment != null) {
            commentService.addComment(new Comment("Mastermind", userName, endComment, new Timestamp(System.currentTimeMillis())));
            fillModel(model);
            return "endGame";
        }
        else {
            commentService.addComment(new Comment("Mastermind", userName, thmlfComment, new Timestamp(System.currentTimeMillis())));
            fillModel(model);
            return "thymeleaf";
        }
     }

    public String getField(){
        StringBuilder sb = new StringBuilder();

        //user and evaluation holes
        for (int i = 0; i < game.getNumOfHoles(); i++){
            sb.append(String.format("<a class='%s' href='/mastermind/changecolor?color=%d&position=%d'></a>", game.getPlayerHoles()[i].getColor().toString().toLowerCase(Locale.ROOT),
                    game.getPlayerHoles()[i].getColor().ordinal(), i));
        }
        sb.append("<div class='space'></div>");
        for (int i = 0; i < game.getNumOfHoles(); i++){
            sb.append("<div class='" + game.getEvaluationHoles()[i].getColor().toString().toLowerCase(Locale.ROOT) + "'></div>");
        }

        return sb.toString();
    }

    public String getHistory(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < game.getNumOfRounds()-1; i++){
            sb.append("<div>");
            for(int j = 0; j < game.getNumOfHistoryHoles(); j++) {
                sb.append("<div class='" + game.getHistory()[i][j].getColor().toString().toLowerCase(Locale.ROOT) + "'></div>");
                if (j == 3){
                    sb.append("<div class='space'></div>");
                }
            }
            sb.append("</div>");
        }

        return sb.toString();
    }

    public String getCombination(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < game.getNumOfHoles(); i++){
            sb.append(String.format("<div class='%s'></div>", game.getCombination()[i].getColor().toString().toLowerCase(Locale.ROOT)));
        }

        return sb.toString();
    }

    public void fillModel(Model model){
        model.addAttribute("scores", scoreService.getTopScores("Mastermind"));
        model.addAttribute("avgRating", ratingService.getAverageRating("Mastermind"));
        model.addAttribute("comments", commentService.getComments("Mastermind"));
    }

    //-----------------------------Help function-----------------------------
        //changes the color of pins after clicking on them
    public int switchColor(){
        int tmp = Integer.parseInt(color);

        if(tmp != 5 && tmp != 8){
            return tmp+1;
        }
        else if (tmp == 5) {
            return 8;
        }
        else {
            return 0;
        }
    }

    //helps with generating score by founding the round that has the most black and then most grey pins in evaluation


    //------------------------------------GETTERS--------------------------

    public String getUserName() {
        return userName;
    }

    public String getColor() {
        return color;
    }

    public boolean isEvaluate() {
        return evaluate;
    }

    public String getRound(){
        return ("Round: " + (round+1));
    }

    public boolean isGameState() {
        return gameState == GameState.WIN;
    }

    public String getScore() {
        return "Your score is: " + score;
    }

    public boolean isLogged() {
        return logged;
    }
}
