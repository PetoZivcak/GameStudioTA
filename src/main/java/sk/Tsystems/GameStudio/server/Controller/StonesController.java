package sk.Tsystems.GameStudio.server.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.Tsystems.GameStudio.entity.Score;

import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.RatingService;
import sk.Tsystems.GameStudio.service.ScoreService;
import sk.Tsystems.GameStudio.service.ScoreServiceJPA;
import sk.Tsystems.GameStudio.stones.core.Field;
import sk.Tsystems.GameStudio.stones.core.GameState;
import sk.Tsystems.GameStudio.stones.core.Square;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/stones")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StonesController {

    @Autowired
    ScoreService scoreService;
    @Autowired
    RatingService ratingService;
    @Autowired
    CommentService commentService;
    @Autowired
    private UserController userController;
    private List<Score> myScore;
    private Field field = new Field(3, 3);

    // private boolean marking = false;

    @RequestMapping
    public String stones(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, Model model) {
        if(row == null && column == null){
            newGame(model);
        }

        if (row != null && column != null) {
//   int nullRow=  Integer.valueOf(field.findEmpty().charAt(0));
//   int nullCol=  Integer.valueOf(field.findEmpty().charAt(2));
            String[] arrStr = field.findEmpty().split("S");
            int nullRow = Integer.parseInt(arrStr[0]);
            int nullCol = Integer.parseInt(arrStr[1]);
            if (row + 1 == nullRow && column == nullCol) {
                field.moveSquare("D");

            }
            if (row - 1 == nullRow && column == nullCol) {
                field.moveSquare("U");

            }
            if (column + 1 == nullCol && row == nullRow) {
                field.moveSquare("R");

            }
            if (column - 1 == nullCol && row == nullRow) {
                field.moveSquare("L");

            }
            prepareModel(model);
            return "stones";
        }
//
//            if (marking) {
//                field.markTile(row, column);
//            } else {
//                field.openTile(row, column);
//            }
//        }
        prepareModel(model);
        return "stones";
    }


    @RequestMapping("/new")
    public String newGame(Model model) {
        field = new Field(3, 3);
        makeChaos(field);
        prepareModel(model);
        return "stones";
    }

    private void prepareModel(Model model) {


        model.addAttribute("stonesField", field.getSquares());
        model.addAttribute("gameStatus", field.solvedGame());
        model.addAttribute("stonesResult", winnOrLoss(field.getState()));
        model.addAttribute("bestScores", scoreService.getBestScores("stones"));
        model.addAttribute("allComments", commentService.getComments("stones"));
        model.addAttribute("averageRating",ratingService.getAverageRating("stones"));

        // model.addAttribute("bestScores",);

        myScore = scoreService.getBestScores("stones");
        model.addAttribute("stonesScoreList", myScore);


    }

    private String winnOrLoss(sk.Tsystems.GameStudio.stones.core.GameState gameState) {

        if (gameState.equals(GameState.FAILED)) {

            return "Prehral si, tvoje score je" + " 0";
        }
        if (gameState.equals(GameState.SOLVED)) {
            scoreService.addScore(new Score("stones", userController.getLoggedUser(), field.getScore(), new Date()));

            return "Vyhral si, tvoje score je " + field.getScore()+" bodov.";
        }
        return "Práve hraješ";
    }

    public String getSquareText(Square square) {
        if (square.getValue() == 0) {
            return "";
        }
        return Integer.toString(square.getValue());
    }

    public void makeChaos(Field field) {
        int nrOfMoves = 5;
        String myCommand;
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int myRandom = random.nextInt(4);
            switch (myRandom) {
                case 0:
                    myCommand = "U";
                    this.field.moveSquare(myCommand);
                    nrOfMoves++;
                    break;
                case 1:
                    myCommand = "D";
                    this.field.moveSquare(myCommand);
                    nrOfMoves++;
                    break;
                case 2:
                    myCommand = "L";
                    this.field.moveSquare(myCommand);
                    nrOfMoves++;
                    break;
                case 3:
                    myCommand = "R";
                    this.field.moveSquare(myCommand);
                    nrOfMoves++;
                    break;
            }
        }

    }

}
