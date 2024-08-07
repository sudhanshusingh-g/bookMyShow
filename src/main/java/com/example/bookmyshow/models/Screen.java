package com.example.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Screen extends BaseModel {
    private String title;
    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Format> formats;
}
