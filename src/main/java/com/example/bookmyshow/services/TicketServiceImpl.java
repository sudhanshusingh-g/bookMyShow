package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.InvalidBookTicketRequestException;
import com.example.bookmyshow.exceptions.SeatsUnavailableException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.TicketRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private UserRepository _userRepository;
    private ShowRepository _showRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(UserRepository userRepository, ShowRepository showRepository,ShowSeatRepository showSeatRepository, TicketRepository ticketRepository) {
        _userRepository = userRepository;
        _showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
    }
    @Transactional(isolation= Isolation.SERIALIZABLE)
    @Override
    public Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception {
        Optional<User> userOptional= this._userRepository.findById(userId);
        User user;
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        else {
            throw new InvalidBookTicketRequestException("User not found");
        }

        Show show = this._showRepository.findById(showId).orElseThrow(() -> new InvalidBookTicketRequestException("Show id is invalid"));
        ShowSeat showSeat = this.showSeatRepository.findById(showSeatIds.get(0)).orElseThrow(() -> new InvalidBookTicketRequestException("Seat id is invalid"));

        if(showSeat.getShow().getId() != showId){
            throw new InvalidBookTicketRequestException("Given seats dont belong to the same show");
        }

        List<ShowSeat> showSeats = null;

        if(showSeats.size() != showSeatIds.size()){
            throw new SeatsUnavailableException("Some of the seats you are trying to book are unavailable");
        }

        for(ShowSeat ss: showSeats){
            ss.setBookedBy(user);
            ss.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats);

        Ticket ticket = new Ticket();
        ticket.setMovie(show.getMovie());
        ticket.setShow(show);
        ticket.setShowSeats(showSeats);
        ticket.setUser(user);

        return ticketRepository.save(ticket);
    }
}
