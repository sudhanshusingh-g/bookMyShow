package com.example.bookmyshow.models;



import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theatre extends BaseModel{
    private String theatre_name;
    private String theatre_location;

    @OneToMany
    private List<Screen> screens;

    @ManyToOne
    private Region region;
}
