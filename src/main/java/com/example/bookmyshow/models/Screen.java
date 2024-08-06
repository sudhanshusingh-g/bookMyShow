package com.example.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Screen extends BaseModel {
    private String screenName;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    @CollectionTable(name = "show_formats")
    @Column(name = "format")
    private List<Format> formatList;
    @OneToMany
    private List<Seat> seats;
}
