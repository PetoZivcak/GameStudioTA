package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Score;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.CommentServiceJPA;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentWebServiceRest {
    @Autowired
    CommentService commentService;

    @GetMapping("/{game}")
    public List<Comment> getBestScores(@PathVariable String game) {

        return commentService.getComments(game);
    }
    @PostMapping
    public void addScore(@RequestBody Comment comment){
        commentService.addComment(comment);
    }
}
