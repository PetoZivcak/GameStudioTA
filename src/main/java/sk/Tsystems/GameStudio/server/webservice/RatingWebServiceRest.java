package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public int getAverageRating(@PathVariable String game) {

        return ratingService.getAverageRating(game);
    }
//    @PostMapping("/{game}/{user}")
//    public void saveRating(@PathVariable String game, @PathVariable String user, @RequestBody int ratingNumber){
//       return ratingService.setRating(new Rating());
//
//    }

    @PostMapping
    public void saveRating(@RequestBody Rating rating){
        ratingService.setRating(rating);

    }

    // GET /api/rating - return all
    // GET /api/rating/{game}/{user} - return 1 rating
    // POST /api/rating/{game}/{user} {payload - rating number } - create new rating
    // PUT /api/rating/{game}/{user} {paylaod - rating number } - update rating
    // DELETE /api/rating/{game}/{user}

}
