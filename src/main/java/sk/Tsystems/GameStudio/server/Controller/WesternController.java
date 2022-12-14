package sk.Tsystems.GameStudio.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Score;

import sk.Tsystems.GameStudio.western.core.Enemy;
import sk.Tsystems.GameStudio.western.core.Field;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.RatingService;
import sk.Tsystems.GameStudio.service.ScoreService;
import sk.Tsystems.GameStudio.western.core.GameState;
import sk.Tsystems.GameStudio.western.core.Tile;


import java.util.Date;

@Controller
@RequestMapping("/western")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class WesternController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserController userController;
    private Field field;
    private boolean isPlaying = true;

    @RequestMapping
    public String western(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, Model model) {

        if (field == null) {
            initField();
        }

        if (row != null && column != null) {

            if (this.field.getState() == GameState.PLAYING) {
                this.field.fireTile(row, column);
            }


            if (this.field.getState() != GameState.PLAYING && this.isPlaying == true) { //I just won/lose
                this.isPlaying = false;
                int checker = 0;
                if (userController.isLogged() && field.getState() == GameState.SOLVED && checker == 0) {


                    Score newScore = new Score("western", userController.getLoggedUser(), this.field.getScore(), new Date());
                    scoreService.addScore(newScore);
                    checker = 1;
                }

            }

        }

        prepareModel(model);
        return "western";
    }

    @RequestMapping("/new")
    public String newGame(Model model) {
        initField();

        this.isPlaying = true;

        prepareModel(model);
        return "western";
    }

    @RequestMapping("/start")
    public String startGame(Model model) {
        field.setAllTilesOpened(this.field);
        prepareModel(model);
        return "western";

    }

    @RequestMapping("/asynch")
    public String loadInAsynchMode() {
        startOrUpdateGame(null, null);
        this.isPlaying = true;
        return "westernAsynch";
    }


    @RequestMapping(value = "/jsonstart", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field openTilesAndStartPlaying() {

        this.field.setJustFinished(false);
        this.field.setAllTilesOpened(this.field);

        return this.field;
    }

    @RequestMapping(value = "/jsonnew", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field newGameJson() {
        initField();
        this.field.setJustFinished(false);
        return this.field;
    }


    @RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public sk.Tsystems.GameStudio.western.core.Field processUserInputJson(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column) {


        boolean justFinished = startOrUpdateGame(row, column);
        this.field.setJustFinished(justFinished);

        return this.field;
    }
//
//    @RequestMapping(value="/jsonmark", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public sk.Tsystems.GameStudio.minesweeper.core.Field changeMarkingJson(){
//        switchMode();
//        this.field.setJustFinished(false);
//        this.field.setMarking(marking);
//        return this.field;
//    }
//
//    @RequestMapping(value="/jsonnew", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public sk.Tsystems.GameStudio.minesweeper.core.Field newGameJson(){
//        startNewGame();
//        this.field.setJustFinished(false);
//        this.field.setMarking(marking);
//        return this.field;
//    }


    private void initField() {
        this.field = new Field(3, 3, 4);


    }

    public String getTileClass(Tile tile) {
        switch (tile.getState()) {
            case OPEN:
                if (tile instanceof Enemy)
                    return "open" + ((Enemy) tile).getIdentificator();
                else
                    return "openHOSTAGE";
            case CLOSED:
                return "closed";

            case FIRED:
                if (tile instanceof Enemy)
                    return "fired" + ((Enemy) tile).getIdentificator();
                else
                    return "firedHOSTAGE";

            default:
                throw new RuntimeException("Unexpected tile state");
        }
    }

    private void prepareModel(Model model) {


        model.addAttribute("isPlaying", this.isPlaying);
        model.addAttribute("gameStatus", this.getGameStatus());
        model.addAttribute("westernField", this.field.getTiles());
        model.addAttribute("bestScores", scoreService.getBestScores("western"));
        model.addAttribute("allComments", commentService.getComments("western"));
        model.addAttribute("averageRating", ratingService.getAverageRating("western"));


        model.addAttribute("comment", new Comment());


    }

    public String getGameStatus() {
        String gameStatus = "";
        if (this.field.getState() == GameState.FAILED) {
            gameStatus = "Prehral si";
        } else if (this.field.getState() == GameState.SOLVED) {
            gameStatus = "Vyhral si (sk??re: " + this.field.getScore() + ")";
        }
        return gameStatus;
    }

    public void setIsPlaying() {

        if (this.field.getState() == GameState.FAILED) {
            this.isPlaying = false;
        } else if (this.field.getState() == GameState.SOLVED) {
            this.isPlaying = false;
        }
    }

    private boolean startOrUpdateGame(Integer row, Integer column) {
        boolean justFinished = false;
        if (field == null) {
            initField();
        }

        if (row != null && column != null) {


            this.field.fireTile(row, column);


            if (this.field.getState() != GameState.PLAYING && this.isPlaying == true) { //I just won/lose
                this.isPlaying = false;

                justFinished = true;


                if (userController.isLogged() && this.field.getState() == GameState.SOLVED) {
                    Score newScore = new Score("western", userController.getLoggedUser(), this.field.getScore(), new Date());
                    scoreService.addScore(newScore);

                }
            }
        }
        return justFinished;
    }

}
