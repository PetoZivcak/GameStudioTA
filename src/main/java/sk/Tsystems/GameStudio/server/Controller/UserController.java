package sk.Tsystems.GameStudio.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.Tsystems.GameStudio.entity.*;
import sk.Tsystems.GameStudio.service.*;


import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserController {
    @Autowired
    public CommentService commentService;

    @Autowired
    public RatingService ratingService;
    @Autowired
    public PlayerService playerService;
    @Autowired
    public CountryServiceJPA countryService;
    @Autowired
    public OccupationServiceJPA occupationService;





    private String loggedUser;

    @RequestMapping("/login")
    public String login(String login, String password, Model model) {
       // System.out.println(playerService.getPlayersByUserName(login));
      if(playerService.getPlayersByUserName(login).isEmpty()){
          System.out.println("som nulovy");
          return "redirect:/registerForm";
      }else{

        if (("heslo").equals(password)) {
            this.loggedUser = login.trim();
            if (this.loggedUser.length() > 0) {
                return "redirect:/gamestudio";
            }
            return "redirect:/gamestudio";
        }
        this.loggedUser = null;
          return "redirect:/gamestudio";
        }

    }

    @RequestMapping("/{game}/addComment")
    public String addComment(String myComment, @PathVariable String game) {
        Comment comment=new Comment();
        comment.setGame(game);
        comment.setUsername(loggedUser);
        comment.setCommented_on(new Date());
        comment.setComment(myComment);
        commentService.addComment(comment);
        return "redirect:/{game}";
    }

    @RequestMapping("/{game}/addRating")
    public String addRating(String myRating,@PathVariable String game){
        if(isParsable(myRating) && Integer.parseInt(myRating)>0&& Integer.parseInt(myRating)<6){
        Rating rating=new Rating();
        rating.setRated_on(new Date());
        rating.setUsername(loggedUser);
        rating.setGame(game);
        rating.setRating(Integer.parseInt(myRating));
        ratingService.setRating(rating);
        return "redirect:/{game}";}else {

          return   "redirect:/{game}"; }

    }
    @RequestMapping("/addUser")
    public String addUser(String userName, String fullName, String myOccupation, String myCountry){
        Player player=new Player();
        player.setSelfevaluation(5);
        player.setOccupation(occupationService.getOccupationInt(Integer.parseInt(myOccupation)));
        player.setUsername(userName);
        player.setFullname(fullName);
        player.setCountry(countryService.getCountryInt(Integer.parseInt(myCountry)));
        playerService.addPlayer(player);
        return   "redirect:/gamestudio";
    }

    @RequestMapping("/logout")
    public String login() {
        this.loggedUser = null;
        return "redirect:/gamestudio";

    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }

    private static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
    @RequestMapping("/registerForm")
    public String testSite(Model model){
        prepareModel(model);
        return "registration";
    }
    private void prepareModel(Model model){
        model.addAttribute("getCountries",countryService.getCountries());
        model.addAttribute("getOccupations", occupationService.getOccupations());
    }
}
