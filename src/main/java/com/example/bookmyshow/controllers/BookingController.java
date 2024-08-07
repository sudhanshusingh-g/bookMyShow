package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookingRequestDto;
import com.example.bookmyshow.dtos.BookingResponseDto;
import com.example.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private  BookingService bookingService;

    public BookingResponseDto bookMovie(BookingRequestDto bookingRequestDto) {
        return null;
    }
}
