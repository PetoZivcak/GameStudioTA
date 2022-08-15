package sk.Tsystems.GameStudio.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "fullname"})
})
public class Player {
    @Id
    @GeneratedValue
    private int ident;
    @Column(length = 32, nullable = false)
    private String username;
    @Column(length = 128, nullable = false)
    private String fullname;
    @Column(columnDefinition = "INT CHECK(selfevaluation BETWEEN 1 AND 10)")
    private int selfevaluation;
    @ManyToOne
    @JoinColumn(name = "Country.ident", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "Occupation.ident", nullable = false)
    private Occupation occupation;

    public Player(String username, String fullname, int selfevaluation, Country country, Occupation occupation) {
        this.username = username;
        this.fullname = fullname;
        this.selfevaluation = selfevaluation;
        this.country = country;
        this.occupation = occupation;
    }

    public Player() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getSelfevaluation() {
        return selfevaluation;
    }

    public void setSelfevaluation(int selfevaluation) {
        this.selfevaluation = selfevaluation;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Player: " +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'';
    }
}

