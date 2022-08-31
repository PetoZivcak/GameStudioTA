//package sk.Tsystems.GameStudio.server.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.WebApplicationContext;
//import sk.Tsystems.GameStudio.entity.Comment;
//import sk.Tsystems.GameStudio.entity.Rating;
//import sk.Tsystems.GameStudio.service.CommentService;
//import sk.Tsystems.GameStudio.service.PlayerService;
//import sk.Tsystems.GameStudio.service.RatingService;
//
//import java.util.Date;
//
//@Controller
//@Scope(WebApplicationContext.SCOPE_SESSION)
//public class UserControllerZaloha {
//    @Autowired
//    public CommentService commentService;
//
//    @Autowired
//    public RatingService ratingService;
//    @Autowired
//    public PlayerService playerService;
//
//    private String loggedUser;
//
//    @RequestMapping("/login")
//    public String login(String login, String password) {
//        if (("heslo").equals(password)) {
//            this.loggedUser = login.trim();
//            if (this.loggedUser.length() > 0) {
//                return "redirect:/gamestudio";
//            }
//        }
//        this.loggedUser = null;
//        return "redirect:/gamestudio";
//    }
//
//    @RequestMapping("/{game}/addComment")
//    public String addComment(String myComment, @PathVariable String game) {
//        Comment comment=new Comment();
//        comment.setGame(game);
//        comment.setUsername(loggedUser);
//        comment.setCommented_on(new Date());
//        comment.setComment(myComment);
//        commentService.addComment(comment);
//        return "redirect:/{game}";
//    }
//
//    @RequestMapping("/{game}/addRating")
//    public String addRating(String myRating,@PathVariable String game){
//        if(isParsable(myRating) && Integer.parseInt(myRating)>0&& Integer.parseInt(myRating)<6){
//        Rating rating=new Rating();
//        rating.setRated_on(new Date());
//        rating.setUsername(loggedUser);
//        rating.setGame(game);
//        rating.setRating(Integer.parseInt(myRating));
//        ratingService.setRating(rating);
//        return "redirect:/{game}";}else {
//
//          return   "redirect:/{game}"; }
//
//    }
////    @RequestMapping("/{game}/addUser")
////    public String addUser(String userName, String fullName, @PathVariable String game){
////        Player player=new Player();
////        player.setSelfevaluation(5);
////        player.setOccupation(new Occupation("Geodet"));
////        player.setUsername(userName);
////        player.setFullname(fullName);
////        player.setCountry(new Country("Slovensko"));
////        return   "redirect:/{game}";
////    }
//
//    @RequestMapping("/logout")
//    public String login() {
//        this.loggedUser = null;
//        return "redirect:/gamestudio";
//
//    }
//
//    public String getLoggedUser() {
//        return loggedUser;
//    }
//
//    public boolean isLogged() {
//        return loggedUser != null;
//    }
//
//    private static boolean isParsable(String input) {
//        try {
//            Integer.parseInt(input);
//            return true;
//        } catch (final NumberFormatException e) {
//            return false;
//        }
//    }
//}
