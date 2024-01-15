package com.example.day5.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Favorites", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"VideoId", "UserId"})
})
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "userid")
    User user;
    @ManyToOne
    @JoinColumn(name = "videoid")
    Video video;
    @Temporal(TemporalType.DATE)
    Date likedate = new Date();
}
