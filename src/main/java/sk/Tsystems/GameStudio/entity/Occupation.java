package sk.Tsystems.GameStudio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Occupation {
    public int getIdent() {
        return ident;
    }

    @Id
    @GeneratedValue
    private int ident;
    @Column(length = 32, nullable = false,unique = true)
    private String occupation;

    @OneToMany(mappedBy = "ident")
    private List<Player> players;

    public Occupation(String occupation) {
        this.occupation = occupation;

    }

    public Occupation() {
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Occupation{" +
                "ident=" + ident +
                ", occupation='" + occupation + '\'' +
                ", players=" + players +
                '}';
    }
}
