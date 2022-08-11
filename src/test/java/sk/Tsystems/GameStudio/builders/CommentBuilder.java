package sk.Tsystems.GameStudio.builders;

import sk.Tsystems.GameStudio.entity.Comment;

import java.security.cert.CertPathBuilder;

public class CommentBuilder {
    Comment comment = new Comment();
    public CommentBuilder withGame(String game) {
        this.comment.setGame(game);
        return this;
    }

    public CommentBuilder withUsername(String username) {
        this.comment.setUsername(username);
        return this;
    }

    public Comment build() {
        return comment;
    }
}
