package sk.Tsystems.GameStudio.minesweeper.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.Tsystems.GameStudio.entity.*;
import sk.Tsystems.GameStudio.minesweeper.Minesweeper;
import sk.Tsystems.GameStudio.minesweeper.Settings;
import sk.Tsystems.GameStudio.minesweeper.UserInterface;
import sk.Tsystems.GameStudio.minesweeper.core.Field;
import sk.Tsystems.GameStudio.minesweeper.core.GameState;
import sk.Tsystems.GameStudio.minesweeper.core.Tile;
import sk.Tsystems.GameStudio.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    private static final Pattern PATTERN = Pattern.compile("([OM]{1})([A-Z]{1})(\\d{1,2})", Pattern.CASE_INSENSITIVE);

    /**
     * Playing field.
     */

    private String userName;
    private String userName1;
    private String selfEvaluation;
    private String country;
    private Country countryToWrite;
    private Player playerToWrite;
    private String occupation;
    private String dificultyLevel;
    private Field field;
    private String ratingString;
    List<Score> myScore;
    private Settings setting;

    public Settings getSettings() {
        return this.setting;
    }


    /**
     * Input reader.
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private OccupationService occupationService;
    @Autowired
    private CountryService countryService;


    /**
     * Reads line of text from the reader.
     *
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Starts the game.
     *
     * @param field field of mines and clues
     */
    @Override
    public void newGameStarted(Field field) {

        System.out.println("Zadaj uzivatelske meno");
        userName = readLine();

        while (!userNameCheck(userName, 32)) {

            userName = readLine();


        }
        if (playerService.getPlayersByUserName(userName) != null) {
            System.out.println("Vyber si jedneho z existujucich hracov, alebo pridaj noveho vyplnenim vstupu: \n" + playerService.getPlayersByUserName(userName));
        } else {

        }

        System.out.println("Zadaj cele meno, ak pouzijes existujuce, hrajes ako existujuci hrac, ak zadas nove, vytvoris noveho hraca ");
        userName1 = readLine();
        while (!userNameCheck(userName1, 128)) {
            userName1 = readLine();
        }
        System.out.println("Zadaj sebahodnotenie od 1 do 10");
        selfEvaluation = readLine();

        int checker = 0;
        while (checker == 0) {
            while (isParsable(selfEvaluation) == false) {
                System.out.println("Zadajte prosim cislo");
                selfEvaluation = readLine();
            }
            if (Integer.parseInt(selfEvaluation) < 1 || Integer.parseInt(selfEvaluation) > 10) {
                System.out.println("Zadajte prosim cislo od 1 do 10");
                selfEvaluation = readLine();
            } else {
                checker = 1;
            }
        }

        if (playerService.getPlayerByUserNameAndFullName(userName,userName1)==null){
//if(playerService.getPlayerByUserNameAndFullName(userName,userName1).getUsername().equals(userName)&&playerService.getPlayerByUserNameAndFullName(userName,userName1).getFullname().equals(userName1)){
//   playerToWrite=playerService.getPlayerByUserNameAndFullName(userName,userName1);
//   playerToWrite.setSelfevaluation(Integer.parseInt(selfEvaluation));
//   playerService.addPlayer(playerToWrite);
//}else{

            System.out.println("Dostupne krajiny:");
            for (int nrOfCountries = 0; nrOfCountries < countryService.getCountries().size(); nrOfCountries++) {
                System.out.println("Country " + (nrOfCountries + 1) + " " + countryService.getCountries().get(nrOfCountries));

            }
            System.out.println("Pre vyber zadajte cislo krajiny, alebo pridajte novu stlacenim cisla " + (countryService.getCountries().size() + 1) + ":");
            country = readLine();
            checker = 0;
            while (checker == 0) {
                while (isParsable(country) == false) {
                    System.out.println("Zadajte prosim cislo");
                    country = readLine();
                }
                if (Integer.parseInt(country) < 1 || Integer.parseInt(country) > countryService.getCountries().size() + 1) {
                    System.out.println("Zadajte prosim cislo od 1 do " + (countryService.getCountries().size() + 1));
                    country = readLine();
                } else {
                    checker = 1;
                }
            }
            if (Integer.parseInt(country) == (countryService.getCountries().size() + 1)) {
                System.out.println("Zadajte meno krajiny: ");
                country = readLine();
                countryToWrite = new Country(country);
                countryService.addCountry(countryToWrite);
            } else {
                countryToWrite = countryService.getCountry(countryService.getCountries().get(Integer.parseInt(country) - 1).getCountry());

            }
            System.out.println("Vyber si jedno z povolani: " + (occupationService.getOccupations().size()));
            occupation = readLine();
            checker = 0;
            while (checker == 0) {
                while (isParsable(occupation) == false) {
                    System.out.println("Zadajte prosim cislo");
                    occupation = readLine();
                }
                if (Integer.parseInt(occupation) < 1 || Integer.parseInt(occupation) > occupationService.getOccupations().size()) {
                    System.out.println("Zadajte prosim cislo od 1 do " + (occupationService.getOccupations().size()));
                    occupation = readLine();
                } else {
                    checker = 1;
                }
            }

            playerToWrite = new Player(userName, userName1, Integer.parseInt(selfEvaluation), countryToWrite,occupationService.getOccupations().get(Integer.parseInt(occupation)-1));
           playerService.addPlayer(playerToWrite);
        }else{
            if(playerService.getPlayerByUserNameAndFullName(userName,userName1).getUsername().equals(userName)&&playerService.getPlayerByUserNameAndFullName(userName,userName1).getFullname().equals(userName1)){
   playerToWrite=playerService.getPlayerByUserNameAndFullName(userName,userName1);
   playerToWrite.setSelfevaluation(Integer.parseInt(selfEvaluation));
   playerService.addPlayer(playerToWrite);
}

        }
//            }

        System.out.println("Vyberte si prosim obtiaznost, alebo potvrdte obtiaznost BEGINNER stlacenim tlacitka ENTER");
        System.out.println("1 [BEGINNER], 2 [EXPERT], 3 [INTERMEDIATE]");
        dificultyLevel = readLine();
        Settings settings = Settings.load();

        if (dificultyLevel != null && !dificultyLevel.equals("")) {
            try {
                int intDificultyLevel = Integer.parseInt(dificultyLevel);
                switch (intDificultyLevel) {
                    case 1:
                        settings = Settings.BEGINNER;
                        break;
                    case 2:
                        settings = Settings.INTERMEDIATE;
                        break;
                    case 3:
                        settings = Settings.EXPERT;
                        break;

                }
            } catch (NumberFormatException e) {
                //empty
            }
        }


//            public Settings getSetting() {
//        return this.setting;
//    }


        //  Minesweeper.getInstance().setSettings(settings);
        this.field = new Field(settings.getRowCount(), settings.getColumnCount(), settings.getMineCount());
        // Minesweeper.getInstance().setStartMillis(System.currentTimeMillis());

        do {
            update();
            processInput();
            if (this.field.getState() == GameState.FAILED) {
                finalOperation(this.field.getState());
            }
            if (this.field.getState() == GameState.SOLVED) {
                finalOperation(this.field.getState());
            }
            // throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while (true);
    }

    public void setSettings(Settings settings) {

        this.setting = settings;
        this.setting.save();
    }

    /**
     * Updates user interface - prints the field.
     */
    @Override
    public void update() {
        int playFieldRowCount = this.field.getRowCount() + 1;
        int playFieldColumnCount = this.field.getColumnCount() + 1;

        if (this.field.getRowCount() > 16 || this.field.getColumnCount() > 30) {
            System.out.println("Prilis velke pole, zadajte prosim max v rozsahu 16x30");
            return;

        }

        System.out.print("   ");
        for (int t = 0; t < field.getColumnCount(); t++) {
            System.out.printf("%-4s", t);
        }
        System.out.println();
        //Tlač hodnôt poľa
        for (int r = 0; r < field.getRowCount(); r++) {
            System.out.printf("%-3s", (char) (65 + r));

            for (int c = 0; c < field.getColumnCount(); c++) {
                Tile t = field.getTile(r, c);
                if (t.getState() == Tile.State.CLOSED) {
                    System.out.printf("%-4s", "-");
                } else if (t.getState() == Tile.State.OPEN) {
                    System.out.printf("%-4s", t);

                } else {
                    System.out.printf("%-4s", "M");
                }

            }
            System.out.println();

        }
//        System.out.println(Minesweeper.getInstance().startMillis);

    }

    @Override
    public void play() {


        Field field = new Field(9, 9, 2);
        newGameStarted(field);

    }


    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        // System.out.println("Zadajte prosim vstup v pozadovanom formate");
        String line = readLine();
        try {
            handleInput(line);
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleInput(String input) throws WrongFormatException {

        if (input.contains("exit")) {
            System.out.println("Hra bola ukoncena");
            System.exit(0);
        }
        Matcher m = PATTERN.matcher(input);

        if (m.matches()) {
            int chValue = (Character.getNumericValue(m.group(2).charAt(0))) - 10;
            int strValue = Integer.parseInt(m.group(3));
            // System.out.println(chValue);
            if (chValue > field.getRowCount() || strValue > field.getColumnCount()) {
                throw new WrongFormatException("Nevychadzaj mimo pole");
            }
            Tile t = field.getTile(chValue, strValue);

            switch (m.group(1).toLowerCase()) {
                case "o":
                    field.openTile(chValue, strValue);
                    break;

                case "m":
                    field.markTile(chValue, strValue);
                    break;
            }
        } else {
            throw new WrongFormatException("Zly format vstupu ");


            //System.out.println("Zadajte prosim spravny format vstupu");
        }
        // throw new UnsupportedOperationException("Method processInput not yet implemented");
    }

    public boolean commentCheck(String comment) {
        boolean result = true;
        if (comment.length() > 1000) {
            result = false;
        }
        return result;
    }

    public boolean ratingCheck(String rating) {
        boolean result = true;

        try {
            Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            result = false;
            return result;
        } catch (NullPointerException e) {
            result = false;
            return result;
        }
        if (Integer.parseInt(rating) < 1 || Integer.parseInt(rating) > 5) {
            result = false;
        }
        return result;
    }

    public boolean userNameCheck(String userName, int lenghtOfString) {
        boolean result = true;
        if (userName.length() > lenghtOfString) {
            System.out.println("Zadali ste prilis dlhe meno, prosim zadajte meno do 64 znakov");
            result = false;
        }
        if (userName.length() == 0) {
            System.out.println("Prosim zadajte meno hraca");
            result = false;

        }

        return result;
    }

    public void finalOperation(GameState gameState) {
        Score score = new Score("minesweeper", userName, 0, new Date());
        String gameWinOrFailedInfo = "";
        try {

            if (gameState == gameState.FAILED) {

                gameWinOrFailedInfo = "Prehral si, tvoje skore je 0, mozes nechat comment: ";
            }
            if (gameState == gameState.SOLVED) {
                score.setPoints(this.field.getScore());
                //  score.setPoints(((this.field.getColumnCount() * this.field.getRowCount()) * 10) - Minesweeper.getInstance().getPlayingSeconds());
                gameWinOrFailedInfo = "Vyhral si, tvoje skore je: " + score.getPoints() + ", mozes nechat comment: ";
            }

            System.out.println(gameWinOrFailedInfo);

            String myComment = readLine();
            commentCheck(myComment);
            while (!commentCheck(myComment)) {
                System.out.println("Zadali ste prilis dlhy comment pozadovana dlzka je do 1000 z nakov");
                myComment = readLine();
                commentCheck(myComment);
            }
            Comment comment = new Comment("minesweeper", userName, myComment, new Date());
            // CommentService commentService = new CommentServiceJDBC();
            commentService.addComment(comment);
            scoreService.addScore(score);
            // Minesweeper.getInstance().doDatabaseOperationsScore(1, score);
            myScore = scoreService.getBestScores("minesweeper");
            List<Comment> myComments = commentService.getComments("minesweeper");
            System.out.println("Mozes zadat hodnotenie hry:");
            ratingString = readLine();
            while (!ratingCheck(ratingString)) {
                System.out.println("Zadali ste nespravny vstup pre rating, zadajte prosim cislo od 1 do 5");
                ratingString = readLine();
                ratingCheck(ratingString);
            }
            int myRating = Integer.parseInt(ratingString);

            Rating rating = new Rating("minesweeper", userName, myRating, new Date());
            // RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
            ratingService.setRating(rating);

            System.out.println("Comments------------------------------------------------");
            for (Comment c : myComments
            ) {
                System.out.printf("Game: %s, Player: %s, Comment: %s, Date and time: %td.%<tm.%<tY/%<tH:%<tM:%<tS\n", c.getGame(), c.getUsername(), c.getComment(), c.getCommented_on());
            }
            System.out.println("Score------------------------------------------------");
            for (Score s : myScore
            ) {
                System.out.printf("Game: %s, Player: %s, Score: %d, Date and time: %td.%<tm.%<tY/%<tH:%<tM:%<tS\n", s.getGame(), s.getUsername(), s.getPoints(), s.getPlayedOn());

            }
            System.out.println("Average rating of a Game Minesweeper------------------------------------------------");

            System.out.printf("%nAverage rating: %d%n", ratingService.getAverageRating("minesweeper"));

            System.exit(0);
        } catch (Exception e) {
            System.err.println("Chyba  pripojenia na databazu, vase skore ani hodnotenie nemoze byt zaznamenane, ukoncujem konzolu: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

//    //kontroluje, ci je vstup parsovatelny integer v rozmedzi hodnot rangeValue1 a rangeValue2
//    private int numberInputCheck(String input, int rangeValue1, int rangeValue2, String message1, String message2) {
//        int returnValue = 0;
//        int checker = 0;
//        while (checker == 0) {
//            while (isParsable(input) == false&& checker==0)  {
//                System.out.println(message1);
//                input = readLine();}
//
//                if (Integer.parseInt(selfEvaluation) < 1 || Integer.parseInt(selfEvaluation) > 10) {
//                    System.out.println(message2);
//                    input = readLine();
//                } else {
//                    checker = 1;
//                    returnValue = Integer.parseInt(input);
//                }
//
//        }
//        return returnValue;
//    }
}
