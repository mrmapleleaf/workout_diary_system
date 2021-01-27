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

@Table(name = "likes")
    @NamedQueries ({
        @NamedQuery(
                name = "getAllLikes",
                query = "SELECT l FROM Likes AS l WHERE l.workoutreport = :workoutreport ORDER BY l.id DESC"
                ),
        @NamedQuery(
                name = "getAllLikesCount",
                query = "SELECT COUNT(l) FROM Likes AS l WHERE l.workoutreport = :workoutreport"
                ),
        @NamedQuery(
                name = "checkLikedAlready",
                query = "SELECT COUNT(l) FROM Likes AS l WHERE l.trainee = :trainee AND l.workoutreport = :workoutreport"
                )
    })

@Entity
public class Likes {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "workoutreport_id")
    private WorkoutReport workoutreport;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public WorkoutReport getWorkoutreport() {
        return workoutreport;
    }

    public void setWorkoutreport(WorkoutReport workoutreport) {
        this.workoutreport = workoutreport;
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
