package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookTicketRequestDto;
import com.example.bookmyshow.dtos.BookTicketResponseDto;
import com.example.bookmyshow.dtos.Response;
import com.example.bookmyshow.exceptions.InvalidBookTicketRequestException;
import com.example.bookmyshow.models.Ticket;
import com.example.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {
    private TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @RequestMapping("/bookTicket")
    public BookTicketResponseDto bookTicket(BookTicketRequestDto bookTicketRequestDto) {
        BookTicketResponseDto bookTicketResponseDto=new BookTicketResponseDto();

        try{
            validateBookTicket(bookTicketRequestDto);
            Ticket ticket= ticketService.bookTicket(
                            bookTicketRequestDto.getUserId(),
                            bookTicketRequestDto.getShowId(),
                            bookTicketRequestDto.getShowSeatIds()
                    );
            Response response=Response.getSuccessResponse();
            bookTicketResponseDto.setResponse(response);
            bookTicketResponseDto.setTicket(ticket);
            return bookTicketResponseDto;
        }catch(Exception  e){
            Response response=Response.getFailedResponse(e.getMessage());
            bookTicketResponseDto.setResponse(response);
        }
        return bookTicketResponseDto;
    }

    private static void validateBookTicket(BookTicketRequestDto bookTicketRequestDto) throws InvalidBookTicketRequestException {
        if(bookTicketRequestDto.getShowId()<=0){
            throw new InvalidBookTicketRequestException("Show id cannot be negative");
        }
        if(bookTicketRequestDto.getUserId()<=0){
            throw new InvalidBookTicketRequestException("User id cannot be negative");
        }
        if(bookTicketRequestDto.getShowSeatIds() == null || bookTicketRequestDto.getShowSeatIds().isEmpty()){
            throw new InvalidBookTicketRequestException("Seat ids should be present");

        }
    }
}
