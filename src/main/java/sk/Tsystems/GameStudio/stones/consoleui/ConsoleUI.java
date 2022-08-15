package sk.Tsystems.GameStudio.stones.consoleui;



import org.springframework.beans.factory.annotation.Autowired;
import sk.Tsystems.GameStudio.entity.*;
import sk.Tsystems.GameStudio.minesweeper.Settings;
import sk.Tsystems.GameStudio.minesweeper.UserInterface;
import sk.Tsystems.GameStudio.service.*;
import sk.Tsystems.GameStudio.stones.core.Field;
import sk.Tsystems.GameStudio.stones.core.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI  {

    private String userName1;
    private String selfEvaluation;
    private String country;
    private Country countryToWrite;
    private Player playerToWrite;
    private String occupation;
    private String dificultyLevel;
    private String ratingString;
    List<Score> myScore;
    private Settings setting;
    private   String userName ;
    private int nrOfMoves;
    private Field field;
    GameState gameState;
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

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private String read() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }


    public void start(Field field) {
        this.field = field;
        makeChaos(this.field);
        System.out.println("Zadaj uzivatelske meno");
        userName = read();

        while (!userNameCheck(userName, 32)) {

            userName = read();


        }
        if (playerService.getPlayersByUserName(userName) != null) {
            System.out.println("Vyber si jedneho z existujucich hracov, alebo pridaj noveho vyplnenim vstupu: \n" + playerService.getPlayersByUserName(userName));
        } else {

        }

        System.out.println("Zadaj cele meno, ak pouzijes existujuce, hrajes ako existujuci hrac, ak zadas nove, vytvoris noveho hraca ");
        userName1 = read();
        while (!userNameCheck(userName1, 128)) {
            userName1 = read();
        }
        System.out.println("Zadaj sebahodnotenie od 1 do 10");
        selfEvaluation = read();

        int checker = 0;
        while (checker == 0) {
            while (isParsable(selfEvaluation) == false) {
                System.out.println("Zadajte prosim cislo");
                selfEvaluation = read();
            }
            if (Integer.parseInt(selfEvaluation) < 1 || Integer.parseInt(selfEvaluation) > 10) {
                System.out.println("Zadajte prosim cislo od 1 do 10");
                selfEvaluation = read();
            } else {
                checker = 1;
            }
        }

        if (playerService.getPlayerByUserNameAndFullName(userName, userName1) == null) {
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
            country = read();
            checker = 0;
            while (checker == 0) {
                while (isParsable(country) == false) {
                    System.out.println("Zadajte prosim cislo");
                    country = read();
                }
                if (Integer.parseInt(country) < 1 || Integer.parseInt(country) > countryService.getCountries().size() + 1) {
                    System.out.println("Zadajte prosim cislo od 1 do " + (countryService.getCountries().size() + 1));
                    country = read();
                } else {
                    checker = 1;
                }
            }
            if (Integer.parseInt(country) == (countryService.getCountries().size() + 1)) {
                System.out.println("Zadajte meno krajiny: ");
                country = read();
                countryToWrite = new Country(country);
                countryService.addCountry(countryToWrite);
            } else {
                countryToWrite = countryService.getCountry(countryService.getCountries().get(Integer.parseInt(country) - 1).getCountry());

            }
            System.out.println("Vyber si jedno z povolani: " + (occupationService.getOccupations().size()));
            occupation = read();
            checker = 0;
            while (checker == 0) {
                while (isParsable(occupation) == false) {
                    System.out.println("Zadajte prosim cislo");
                    occupation = read();
                }
                if (Integer.parseInt(occupation) < 1 || Integer.parseInt(occupation) > occupationService.getOccupations().size()) {
                    System.out.println("Zadajte prosim cislo od 1 do " + (occupationService.getOccupations().size()));
                    occupation = read();
                } else {
                    checker = 1;
                }
            }

            playerToWrite = new Player(userName, userName1, Integer.parseInt(selfEvaluation), countryToWrite, occupationService.getOccupations().get(Integer.parseInt(occupation) - 1));
            playerService.addPlayer(playerToWrite);
        } else {
            if (playerService.getPlayerByUserNameAndFullName(userName, userName1).getUsername().equals(userName) && playerService.getPlayerByUserNameAndFullName(userName, userName1).getFullname().equals(userName1)) {
                playerToWrite = playerService.getPlayerByUserNameAndFullName(userName, userName1);
                playerToWrite.setSelfevaluation(Integer.parseInt(selfEvaluation));
                playerService.addPlayer(playerToWrite);
            }

        }
               do {

            update();
            try {
                input();
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
            if (field.solvedGame() == true) {
                  update();
                System.out.println(" ");
                finalOperation(gameState= GameState.SOLVED);


                System.exit(0);
            }

        } while (field.solvedGame() == false);
    }





    //@Override
    public void newGameStarted(Field field) {

    }

    public void update() {

        System.out.println();
        //Tlač hodnôt poľa
        for (int i = 0; i < field.getNrOfRow(); i++) {
            for (int j = 0; j < field.getNrOfCol(); j++) {
                if (field.getSquare(i, j).getValue() == 0) {
                    System.out.printf("%4s", " ");
                } else {
                    System.out.printf("%4s", field.getSquare(i, j).getValue());
                }
                if (j == field.getNrOfCol() - 1) {
                    System.out.println();
                }

            }

        }


    }


    public void play() {
        Field field = new Field(3, 3 );
        start(field);

    }

    public void makeChaos(Field field) {
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

    public void input() throws MyException {
        final Pattern PATTERNINPUT = Pattern.compile("[UDLR]{1}", Pattern.CASE_INSENSITIVE);

        String input = read().toUpperCase();
        Matcher matcherInput = PATTERNINPUT.matcher(input);
        if (input.contains("X")) {
            System.out.println("Hra bola ukoncena");
            System.exit(0);
        }
        if (matcherInput.matches()) {

            this.field.moveSquare(input);
        } else {
            throw new MyException("Nespravny vstup na pohyb pouzite tlacitka u d l r");
        }
    }
    public void finalOperation(GameState gameState){


        Score score=new Score("stones", userName,0,new Date());
        String myEndOfGameString="";
        if(gameState==GameState.FAILED){
            myEndOfGameString="Prehral si, tvoje skore je 0, mozes nechat comment: ";
        }
        if(gameState==GameState.SOLVED){
           int myPoints=this.field.getScore();
            myEndOfGameString="Vyhral si si, tvoje skore je "+ myPoints+" bodov, mozes nechat comment: ";
            System.out.println(myEndOfGameString);
        }
        String myComment=read();
        Comment comment=new Comment("stones",userName,myComment,new Date());
        //CommentServiceJDBC comentService= new CommentServiceJDBC();
        commentService.addComment(comment);

        System.out.println("Zadaj prosim hodnotenie hry");
        Integer myRating=0;
        myRating=Integer.parseInt(read());
        Rating rating=new Rating("stones",userName,myRating,new Date());
        // ratingService=new RatingServiceJDBC();
        ratingService.setRating(rating);



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
    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

}
