package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "cities")
public class Region extends BaseModel {
    private String regionName;
    @OneToMany
    private List<Theatre> theatres;
}
