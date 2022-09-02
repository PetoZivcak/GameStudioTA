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
    private boolean isPlaying = true;
    // private boolean marking = false;

    @RequestMapping
    public String stones(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, Model model) {
        boolean justFinished = false;
        if (row == null && column == null) {
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




            }


        if (field.solvedGame()==true) {
            justFinished = true;

            if (field.solvedGame()==true && this.isPlaying == true) { //I just won/lose
                this.isPlaying = false;
                int checker = 0;
                if (userController.isLogged() && field.solvedGame()==true && checker == 0) {


                    Score newScore = new Score("stones", userController.getLoggedUser(), this.field.getScore(), new Date());
                    scoreService.addScore(newScore);
                    checker = 1;
                }

            }
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
        startNewGame();
        prepareModel(model);
        return "stones";
    }

    @RequestMapping("/asynch")
    public String loadInAsynchMode() {

        startOrUpdateGame(null, null, null);
        this.field.solvedGame();
        return "stonesAsynch";
    }

    @RequestMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field processUserInputJson(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, @RequestParam(required = false) String command) {
        boolean justFinished = startOrUpdateGame(row, column,command);
        this.field.setJustFinished(justFinished);
        this.field.solvedGame();
        return this.field;
    }

    @RequestMapping(value = "/jsonnew", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Field newGameJson() {
        startNewGame();
        this.field.setJustFinished(false);
        return this.field;
    }

    private void prepareModel(Model model) {


        model.addAttribute("stonesField", field.getSquares());
        model.addAttribute("gameStatus", field.solvedGame());
        model.addAttribute("stonesResult", winnOrLoss(field.getState()));
        model.addAttribute("bestScores", scoreService.getBestScores("stones"));
        model.addAttribute("allComments", commentService.getComments("stones"));
        model.addAttribute("averageRating", ratingService.getAverageRating("stones"));

        // model.addAttribute("bestScores",);

        myScore = scoreService.getBestScores("stones");
        model.addAttribute("stonesScoreList", myScore);


    }

    private String winnOrLoss(sk.Tsystems.GameStudio.stones.core.GameState gameState) {

        if (gameState.equals(GameState.FAILED)) {

            return "Prehral si, tvoje score je" + " 0";
        }
        if (gameState.equals(GameState.SOLVED)) {


            return "Vyhral si, tvoje score je " + field.getScore() + " bodov.";
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

    public void startNewGame() {
        field = new Field(3, 3);
        makeChaos(field);
    }

    private boolean startOrUpdateGame(Integer row, Integer column, String command) {
        boolean justFinished = false;

        if (row == null && column == null & command==null) {
            startNewGame();
        }
        if (command != null) {
            field.moveSquare(command);
            if (field.getState().equals(GameState.SOLVED)) {
              justFinished = true;
            }
            return justFinished;
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




        }
        if (field.solvedGame()==true) {
            justFinished = true;

            if (this.field.solvedGame()==true && this.isPlaying == true) { //I just won/lose
                this.isPlaying = false;
                int checker = 0;
                if (userController.isLogged() &&  field.solvedGame()==true && checker == 0) {


                    Score newScore = new Score("stones", userController.getLoggedUser(), this.field.getScore(), new Date());
                    scoreService.addScore(newScore);
                    checker = 1;
                }

            }
        }
        return justFinished;
    }
//    private boolean startOrUpdateKeyboardInput(String command){
//        boolean justFinished = false;
//        if(command!=null){
//            field.moveSquare(command);
//        }
//        if(field.getState().equals(GameState.SOLVED)){
//             justFinished = true;
//        }
//        return justFinished;
//    }

}
