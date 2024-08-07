package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.BookingStatus;
import lombok.Data;

@Data
public class BookingResponseDto {
    private Booking booking;
    private BookingStatus bookingStatus;
}
