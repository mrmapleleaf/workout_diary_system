package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
            name = "checkFollowedAlready",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.trainee1 = :trainee1 AND f.trainee2 = :trainee2"
            ),
    @NamedQuery(
            name = "getAllFollow",
            query = "SELECT f FROM Follow AS f WHERE f.trainee1 = :trainee1"
            ),
    @NamedQuery(
            name = "getAllFollowCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.trainee1 = :trainee1"
            ),
    @NamedQuery(
            name = "getAllFollowed",
            query = "SELECT f FROM Follow AS f WHERE f.trainee2 = :trainee2"
            ),
    @NamedQuery(
            name = "getAllFollowedCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.trainee2 = :trainee2"
            )
})

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follow_id")
    private Trainee trainee1;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Trainee trainee2;

    @Column(name ="created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trainee getTrainee1() {
        return trainee1;
    }

    public void setTrainee1(Trainee trainee1) {
        this.trainee1 = trainee1;
    }

    public Trainee getTrainee2() {
        return trainee2;
    }

    public void setTrainee2(Trainee trainee2) {
        this.trainee2 = trainee2;
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
