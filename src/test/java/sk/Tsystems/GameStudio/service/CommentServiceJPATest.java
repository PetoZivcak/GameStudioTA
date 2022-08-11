//package sk.Tsystems.GameStudio.service;
//
//import org.junit.jupiter.api.Test;
//import sk.Tsystems.GameStudio.builders.CommentBuilder;
//import sk.Tsystems.GameStudio.entity.Comment;
//import sk.Tsystems.GameStudio.entity.Rating;
//
//import java.util.List;
//
//public class CommentServiceJPATest {
//    private CommentService commentServiceJPA = new CommentServiceJPA();
//
//    @Test
//    public void addComment_should_persistComment_when_validCommentEntered() {
//        // prepare
//        Comment newCommentUsingBuilderPattern = new CommentBuilder()
//                 .withGame("asdf")
//                 .withUsername("23")
//                 .build();
//
//        // run
//        commentServiceJPA.addComment(newComment);
//
//        // assert
//    }
//
//    @Test
//    public void addComment_should_persistComment_when_validCommentWithLongUsername() {
//        // prepare
//        Comment newCommentUsingBuilderPattern = new CommentBuilder()
//                .withGame("asdf")
//                .withUsername("23sssssssssssssssssssssssssssssssssssssss")
//                .build();
//
//        // run
//        commentServiceJPA.addComment(newComment);
//
//        // assert
//    }
//
//    @Test
//    public void addComment_should_NotPersistComment_when_nullEntered() {
//        // run
//        commentServiceJPA.addComment(null);
//
//        // assert
//    }
//
//    @Test
//    public void addComment_should_NotPersistComment_when_emptyComment() {
//        // prepare
//       Comment c = new Comment();
//       List<Rating> ratings = new List<Rating>();
//       Rating r1 = new Rating();
//               ratings.add(r1);
//               c.setRatings(ratings);
//        Rating r2 = new Rating();
//        ratings.add(r2);
//        c.setRatings(ratings);
//
//
//            Comment c = new CommentBuilder()
//                    .withUsername("asdf")
//                    .withRating(new RatingBuilder().build())
//                    .withRating(new RatingBuilder().build())
//                    .withRating(new RatingBuilder().build())
//                    .build();
//
//    }
//}
