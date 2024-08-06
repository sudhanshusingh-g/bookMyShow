package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private String seatNo;
    private Integer rowVal;
    private Integer colVal;
    private SeatType seatType;
}
