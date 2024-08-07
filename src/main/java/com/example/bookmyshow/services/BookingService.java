package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.ShowSeat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) {
        return null;
    }

}
