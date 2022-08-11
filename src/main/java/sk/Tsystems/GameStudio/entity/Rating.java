package sk.Tsystems.GameStudio.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(uniqueConstraints={
@UniqueConstraint(columnNames = {"game", "username"})
})
public class Rating {
@Id
@GeneratedValue
private long indent;
@Column(length = 64,nullable = false)
    private String game;
    @Column(length = 64,nullable = false)
    private String username;
   @Column(columnDefinition = "INT CHECK(rating BETWEEN 1 AND 5) NOT NULL")
     private int rating;
    private Date rated_on;

    public Rating(String game, String username, int rating, Date rated_on) {
        this.game = game;
        this.username = username;
        this.rating = rating;
        this.rated_on = rated_on;
    }

    public Rating() {
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRated_on() {
        return rated_on;
    }

    public void setRated_on(Date rated_on) {
        this.rated_on = rated_on;
    }
}
