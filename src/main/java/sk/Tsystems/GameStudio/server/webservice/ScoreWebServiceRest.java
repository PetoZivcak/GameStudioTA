package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.Tsystems.GameStudio.entity.Score;
import sk.Tsystems.GameStudio.service.ScoreService;

import javax.annotation.Resource;
import java.util.List;

/**
 * controller || api || restapi
 */
@RestController
@RequestMapping("/api/score")
public class ScoreWebServiceRest {
    @Autowired()
    private ScoreService scoreService;


    @GetMapping("/{game}")
    public List<Score> getBestScores(@PathVariable String game) {

        return scoreService.getBestScores("minesweeper");
    }
    @PostMapping
    public void addScore(@RequestBody Score score){
        scoreService.addScore(score);
    }


}
