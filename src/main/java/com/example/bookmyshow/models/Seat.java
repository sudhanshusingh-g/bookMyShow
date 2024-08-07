package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    private int rowVal;
    private int colVal;

    @ManyToOne
    private SeatType seatType;

}
