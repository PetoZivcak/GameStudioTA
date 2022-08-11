package sk.Tsystems.GameStudio.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.Tsystems.GameStudio.SpringClient;
import sk.Tsystems.GameStudio.builders.CommentBuilder;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Rating;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringClient.class)
public class CommentServiceJPATest {
    @Autowired
    CommentService commentService;

    @Test
    public void addComment_should_persistComment_when_validCommentEntered() {
        commentService.reset();
        Comment comment = new Comment("minesweeper", "Peto", "Comment1", new Date());
        Comment comment2 = new Comment("minesweeper", "Peto", "Comment2", new Date());
        Comment comment3 = new Comment("stones", "Peto", "Comment3", new Date());
        commentService.addComment(comment);
        commentService.addComment(comment2);
        commentService.addComment(comment3);

        assertEquals(2, commentService.getComments("minesweeper").size());
        assertEquals(1,commentService.getComments("stones").size());


    }

    @Test
    public void addComment_should_persistComment_when_validCommentWithLongUsername() {
        commentService.reset();
        Comment comment = new Comment("minesweeper", "JaSomVelikanskeMenoKtoreSaMaUlozitDoDatabazy", "Comment1", new Date());
        commentService.addComment(comment);
        assertEquals(1, commentService.getComments("minesweeper").size());
    }




}
