package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "trainees")
@NamedQueries({
    @NamedQuery(
                name = "getAllTrainees",
                query = "SELECT t FROM Trainee AS t ORDER BY t.id DESC"
            ),
    @NamedQuery(
                name = "getAllTraineesCount",
                query = "SELECT COUNT(t) FROM Trainee AS t"
            ),
    @NamedQuery(
                name = "checkUsername",
                query = "SELECT COUNT(t) FROM Trainee AS t WHERE t.username = :username"
            ),
    @NamedQuery(
                name = "checkLoginUsernameAndPassword",
                query = "SELECT COUNT(t) FROM Trainee AS t WHERE t.username = :username AND t.password = :password"
            )
})

@Entity
public class Trainee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }



}
