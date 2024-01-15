package com.example.day5.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Videos")
public class Video {
    @Id
    String id;
    String title;
    String poster;
    String description;
    int views;
    Boolean active;
    @OneToMany(mappedBy = "video")
    List<Favorite> favorites;
}
