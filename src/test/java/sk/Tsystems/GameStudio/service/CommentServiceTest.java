package sk.Tsystems.GameStudio.service;

import sk.Tsystems.GameStudio.entity.Comment;
import org.junit.jupiter.api.Test;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.CommentServiceFile;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {
    private CommentService commentService = new CommentServiceFile();

    @Test
    public void testReset() {
        commentService.addComment(new Comment("minesweeper", "Jano", "My Comment", new Date()));
        commentService.reset();
        assertEquals(0, commentService.getComments("minesweeper").size());
    }

    @Test
    public void testAddScore() {
        commentService.reset();
        var date = new Date();
        commentService.addComment(new Comment("minesweeper", "Peto", "My comment", date));
        var comments = commentService.getComments("minesweeper");
        assertEquals(1, comments.size());
        assertEquals("sk/Tsystems/GameStudio", comments.get(0).getGame());
        assertEquals("Peto", comments.get(0).getUsername());
        assertEquals("My comment", comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommented_on());

    }

    @Test
    public void testGetBestScores() {
        commentService.reset();
        long addHours=10000;
        var date=new Date();
            var date1 = new Date(date.getTime()-addHours);
             var date2 = new Date(date.getTime()-addHours*10);
            var date3 = new Date(date.getTime()-addHours*20);
            var date4= new Date(date.getTime()-addHours*30);

            commentService.addComment(new Comment("minesweeper", "Peto", "My comment1", date1));
            commentService.addComment(new Comment("minesweeper", "Katka", "My comment2", date2));
            commentService.addComment(new Comment("tiles", "Zuzka", "My comment3", date3));
            commentService.addComment(new Comment("minesweeper", "Jergus", "My comment4", date4));

            var comments = commentService.getComments("minesweeper");

            assertEquals(3, comments.size());

//            assertComment('game', 'Peto', 'My comment1', null, comments.get(0));
//            assertComment('game', 'Peto', 'My comment1', null, comments.get(1));
//            assertEquals("minesweeper", comments.get(0).getGame());
//            assertEquals("Peto", comments.get(0).getUsername());
//            assertEquals("My comment1", comments.get(0).getComment());
//            assertEquals(date1, comments.get(0).getCommented_on());

            assertEquals("minesweeper", comments.get(1).getGame());
            assertEquals("Katka", comments.get(1).getUsername());
            assertEquals("My comment2", comments.get(1).getComment());
            assertEquals(date2, comments.get(1).getCommented_on());

            assertEquals("minesweeper", comments.get(2).getGame());
            assertEquals("Jergus", comments.get(2).getUsername());
            assertEquals("My comment4", comments.get(2).getComment());
            assertEquals(date4, comments.get(2).getCommented_on());

    }

//    private void assertComment(String game, String peto, String my_comment1, Date date1, Comment comment) {
//        assertEquals(game, comment.getGame());
//        assertEquals(peto, comment.getUsername());
//        assertEquals(my_comment1, comment.getComment());
//        assertEquals(date1, comment.getCommented_on());
//    }

}
