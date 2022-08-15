package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Occupation;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.OccupationService;

import java.util.List;

@RestController
@RequestMapping("/api/occupation")
public class OccupationWebServiceRest {
    @Autowired
    OccupationService occupationService;

    @GetMapping
    public List<Occupation> getOccupations() {

        return occupationService.getOccupations();
    }
    @PostMapping
    public void addScore(@RequestBody Occupation occupation){
        occupationService.addOccupation(occupation);
    }

}
