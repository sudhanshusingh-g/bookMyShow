package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.InvalidShowException;
import com.example.bookmyshow.exceptions.InvalidUserException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.BookingRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculationService priceCalculationService;
    private BookingRepository bookingRepository;
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculationService priceCalculationService,
                          BookingRepository bookingRepository) {
        this.showSeatRepository=showSeatRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.priceCalculationService = priceCalculationService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws InvalidUserException, InvalidShowException, ShowSeatNotAvailableException {



        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new InvalidUserException("User with id : "+userId+"doesn't exist.");
        }

        User user=userOptional.get();

        Optional<Show> showOptional=showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new InvalidShowException("Show with id : "+showId+"doesn't exist.");
        }

        Show show=showOptional.get();
        List<ShowSeat> showSeats=showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat:showSeats) {
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotAvailableException("Show seats with id : "+showSeat.getId()+"isn't available.");
            }
        }

        List<ShowSeat> savedShowSeats=new ArrayList<>();
        for(ShowSeat showSeat:showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        Booking booking=new Booking();
        booking.setBookedBy(user);
        booking.setBookingDate(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculationService.calculatePrice(savedShowSeats,show));

        return bookingRepository.save(booking);
    }


}
