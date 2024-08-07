package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User bookedBy;


    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToMany
    private List<ShowSeat> showSeats;
    private Date bookingDate;
    private double amount;

    @OneToMany
    private List<Payment> payments;
}
