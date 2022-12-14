//package sk.Tsystems.GameStudio.server.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.WebApplicationContext;
//import sk.Tsystems.GameStudio.entity.Comment;
//import sk.Tsystems.GameStudio.entity.Score;
//import sk.Tsystems.GameStudio.minesweeper.core.Clue;
//import sk.Tsystems.GameStudio.minesweeper.core.Field;
//import sk.Tsystems.GameStudio.minesweeper.core.GameState;
//import sk.Tsystems.GameStudio.minesweeper.core.Tile;
//import sk.Tsystems.GameStudio.service.CommentService;
//import sk.Tsystems.GameStudio.service.RatingService;
//import sk.Tsystems.GameStudio.service.ScoreService;
//
//import java.util.Date;
//
//@Controller
//@RequestMapping("/minesweeper")
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class MinesweeperControllerZaloha {
//
//    @Autowired
//    private ScoreService scoreService;
//    @Autowired
//    private CommentService commentService;
//    @Autowired
//    private RatingService ratingService;
//    @Autowired
//    private UserController userController;
//    private Field field;
//    /**
//     * false if opening tiles, true if marking tiles
//     */
//    private boolean marking = false;
//
//    /**
//     * false if finished (won or lost), true if playing the game
//     */
//    private boolean isPlaying = true;
//
//
//    @RequestMapping
//    public String minesweeper(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, Model model) {
//
//        if (field == null) {
//            initField();
//        }
//
//        if (row != null && column != null) {
//
//            if (this.field.getState() == GameState.PLAYING) {
//                if (marking) {
//                    this.field.markTile(row, column);
//                } else {
//                    this.field.openTile(row, column);
//                }
//
//            }
//
//
//            if (this.field.getState() != GameState.PLAYING && this.isPlaying == true) { //I just won/lose
//                this.isPlaying = false;
//                int checker=0;
//                if (userController.isLogged() && field.getState()==GameState.SOLVED && checker==0) {
//
//
//                     Score newScore = new Score("minesweeper", userController.getLoggedUser(), this.field.getScore(), new Date());
//                    scoreService.addScore(newScore);
//                    checker=1;
//                }
//
//            }
//
//        }
//
//        prepareModel(model);
//        return "minesweeper";
//    }
//
//    private void initField() {
//        this.field = new Field(9, 9, 1);
//    }
//
//    @RequestMapping("/mark")
//    public String changeMarking(Model model) {
//        if (this.field.getState() == GameState.PLAYING) {
//            this.marking = !this.marking;
//        }
//        prepareModel(model);
//        return "minesweeper";
//    }
//
//    @RequestMapping("/new")
//    public String newGame(Model model) {
//        initField();
//        this.isPlaying = true;
//        this.marking = false;
//        prepareModel(model);
//        return "minesweeper";
//    }
//
//    public String getCurrTime() {
//        return new Date().toString();
//    }
//
//    public boolean getMarking() {
//        return this.marking;
//    }
//
//
//    /**
//     * Generates the full table with the minesweeper field.
//     * (now unused, this is transformed to the template)
//     *
//     * @return String with HTML of the table
//     */
//    public String getFieldAsHtml() {
//
//        int rowCount = this.field.getRowCount();
//        int colCount = this.field.getColumnCount();
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("<table class='minefield'>\n");
//
//        for (int row = 0; row < rowCount; row++) {
//            sb.append("<tr>\n");
//
//            for (int col = 0; col < colCount; col++) {
//                Tile tile = this.field.getTile(row, col);
//
//                sb.append("<td class='" + getTileClass(tile) + "'> ");
//                sb.append("<a href='/minesweeper?row=" + row + "&column=" + col + "'> ");
//                sb.append("<span>" + getTileText(tile) + "</span>");
//                sb.append(" </a>\n");
//                sb.append(" </td>\n");
//
//            }
//            sb.append("</tr>\n");
//        }
//
//
//        sb.append("</table>\n");
//
//        return sb.toString();
//    }
//
//    /**
//     * Gets the text that may be displayed inside a HTML element representing 1 tile
//     * Now public as it is called from the template
//     *
//     * @param tile - the Tile object the text is extracted from
//     * @return the text that may be displayed inside a HTML element representing the Tile tile
//     */
//    public String getTileText(Tile tile) {
//        switch (tile.getState()) {
//            case CLOSED:
//                return "-";
//            case MARKED:
//                return "M";
//            case OPEN:
//                if (tile instanceof Clue) {
//                    return String.valueOf(((Clue) tile).getValue());
//                } else {
//                    return "X";
//                }
//            default:
//                throw new IllegalArgumentException("Unsupported tile state " + tile.getState());
//        }
//    }
//
//    /**
//     * Gets the HTML class of the <td> element representing the Tile tile
//     * Now public as it is called from the template
//     *
//     * @param tile - the Tile object the class is assigned to
//     * @return String with the HTML class of the <td> element representing the Tile tile
//     */
//    public String getTileClass(Tile tile) {
//        switch (tile.getState()) {
//            case OPEN:
//                if (tile instanceof Clue)
//                    return "open" + ((Clue) tile).getValue();
//                else
//                    return "mine";
//            case CLOSED:
//                return "closed";
//            case MARKED:
//                return "marked";
//            default:
//                throw new RuntimeException("Unexpected tile state");
//        }
//    }
//
//    /**
//     * Fills the Spring MVC model object for the Thymeleaf template
//     *
//     * @param model - the Spring MVC model
//     */
//    private void prepareModel(Model model) {
//
//        String gameStatus = "";
//        if (this.field.getState() == GameState.FAILED) {
//            gameStatus = "Prehral si";
//        } else if (this.field.getState() == GameState.SOLVED) {
//            gameStatus = "Vyhral si (sk??re: " + this.field.getScore() + ")";
//        } else {
//            gameStatus = "Hraje?? a ";
//            if (this.marking) {
//                gameStatus += "ozna??uje??";
//            } else {
//                gameStatus += "otv??ra??";
//            }
//        }
//
//        model.addAttribute("isPlaying", this.isPlaying);
//        model.addAttribute("marking", this.marking);
//        model.addAttribute("gameStatus", gameStatus);
//        model.addAttribute("minesweeperField", this.field.getTiles());
//        model.addAttribute("bestScores", scoreService.getBestScores("minesweeper"));
//        model.addAttribute("allComments", commentService.getComments("minesweeper"));
//        model.addAttribute("averageRating",ratingService.getAverageRating("minesweeper"));
//
//        model.addAttribute("comment",new Comment());
//
//
//    }
//
//
//}