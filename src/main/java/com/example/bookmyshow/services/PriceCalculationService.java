package com.example.bookmyshow.services;

import com.example.bookmyshow.models.SeatTypeShow;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculationService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public double calculatePrice(List<ShowSeat> showSeats, Show show) {
        List<SeatTypeShow> seatTypeShows=showSeatTypeRepository.findAllByShow(show);
        double price=0;
        for (ShowSeat showSeat : showSeats) {
            for (SeatTypeShow seatTypeShow : seatTypeShows) {
                if(showSeat.getSeat().getSeatType().equals(seatTypeShow.getSeatType())){
                    price+=seatTypeShow.getPrice();
                    break;
                }
            }
        }
        return price;
    }
}
