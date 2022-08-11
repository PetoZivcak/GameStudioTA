package sk.Tsystems.GameStudio.minesweeper;

import sk.Tsystems.GameStudio.entity.Score;
import sk.Tsystems.GameStudio.minesweeper.consoleui.ConsoleUI;
import sk.Tsystems.GameStudio.minesweeper.core.Field;
import sk.Tsystems.GameStudio.service.ScoreService;
import sk.Tsystems.GameStudio.service.ScoreServiceJDBC;

import java.util.List;

/**
 * Main application class.
 */
public class Minesweeper {

    //Settings
    private Settings setting;

    public Settings getSettings() {
        return this.setting;
    }


    /**
     * User interface.
     */

    private UserInterface userInterface;

    private BestTimes bestTimes = new BestTimes();

    private long startMillis;
    private static Minesweeper instance;

    //vracia prave jednu instanciu singletona
    public static Minesweeper getInstance() {
        if (instance == null) {
            new Minesweeper();
        }
        return instance;
    }


    /**
     * Constructor.
     */
    //singleton - konstruktor musi byt private. Zakazeme robit new Minesweeper()
    private Minesweeper() {
        instance = this; //singleton
       setting = Settings.load();

        userInterface = new ConsoleUI();
       // Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
       // userInterface.newGameStarted(field);
       // userInterface.play();

    }

    /**
     *
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Minesweeper.getInstance();
    }



    public int getPlayingSeconds() {
        return (int) ((System.currentTimeMillis() - startMillis) / 1000);
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    public List<Score> doDatabaseOperationsScore(int databaseOperationsScore, Score score) {
        List<Score> myScore;
        myScore = null;
        ScoreService scoreService = new ScoreServiceJDBC();
        switch (databaseOperationsScore) {
            case 1:
                scoreService.addScore(score);
                break;
            case 2:
                myScore = scoreService.getBestScores(score.getGame());
                return myScore;
            case 3:
                scoreService.reset();
                break;
        }

        return myScore;
    }

    public void setSettings(Settings settings) {
        this.setting = settings;
        this.setting.save();
    }

    public Settings getSetting() {
        return this.setting;
    }
}