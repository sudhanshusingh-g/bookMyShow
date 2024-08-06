package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(int userId, int showId, List<Integer> showSeats) throws Exception;
}
