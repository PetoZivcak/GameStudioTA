package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Rating;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingWebServiceRest {
    @Autowired
    RatingService ratingService;
    @GetMapping("/average/{game}")
    public int getBestScores(@PathVariable String game) {

        return ratingService.getAverageRating(game);
    }
}
