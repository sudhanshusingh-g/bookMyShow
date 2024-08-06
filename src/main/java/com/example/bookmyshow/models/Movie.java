package com.example.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
public class Movie extends BaseModel {
    private String name;
    private String director;
    private Date releaseDate;
    private Double rating;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres")
    @Column(name = "genre")
    private List<String> genre;
    private String description;
    private String poster;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_cast")
    @Column(name = "cast_member")
    private List<String> cast;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_languages")
    @Column(name = "language")
    private List<String> languages;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.ORDINAL)
    @CollectionTable(name = "movie_format")
    @Column(name = "format")
    private List<Format> formats;
}
