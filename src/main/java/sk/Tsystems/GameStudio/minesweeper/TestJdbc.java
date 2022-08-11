package sk.Tsystems.GameStudio.minesweeper;

import sk.Tsystems.GameStudio.service.RatingService;
import sk.Tsystems.GameStudio.service.RatingServiceJDBC;

public class TestJdbc {


    public static void main(String[] args) throws Exception {
       // Comment comment=new Comment("minesweeper","Peto","Moj comment", new Date());

        // CommentService commentService=new CommentServiceJDBC();
        // commentService.reset();
//         commentService.addComment(comment);
        //System.out.println(commentService.getComments("minesweeper"));
       // }
        RatingService ratingService=new RatingServiceJDBC();
       int myRating= ratingService.getRating("sk/Tsystems/GameStudio","peto");
        System.out.println(myRating);

//        ScoreService scoreService = new ScoreServiceJDBC();
//        scoreService.reset();
//        try (
//
//                var connection = DriverManager.getConnection("jdbc:postgresql://localhost/gamestudio", "postgres", "postgres");
//                var statement = connection.createStatement();
//                var rs =statement.executeQuery("SELECT game, username, points, played_on FROM score WHERE game='minesweeper' ORDER BY points DESC LIMIT 5");
//
//        ) {
//            System.out.println("Pripojenie uspesne");
//            while (rs.next()){
//                System.out.printf("%s,%s,%d,%s\n",rs.getString(1),rs.getString(2),rs.getInt(3),rs.getTimestamp(4));
//               // System.out.println(rs.getInt(3));
//            }
//           // statement.executeUpdate("INSERT INTO score VALUES ('minesweeper', 'July', 363, '2022-07-31 12:45')");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }}


