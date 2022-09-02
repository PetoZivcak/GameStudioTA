package sk.Tsystems.GameStudio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    public int getIdent() {
        return ident;
    }

    @Id
    @GeneratedValue
    private int ident;

    @Column(length = 128, nullable = false,unique = true)
    private String country;
    @OneToMany(mappedBy = "ident")
    private List<Player> players;

    public Country(String country) {
        this.country = country;

    }

    public Country() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Country: "
                + country + '\'' ;
    }
}
