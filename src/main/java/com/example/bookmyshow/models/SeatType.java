package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SeatType extends BaseModel{
    private String seatType;
}
