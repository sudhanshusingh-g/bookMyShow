package com.example.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
public class Movie extends BaseModel {
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Format> formats;

    private double rating;
}
