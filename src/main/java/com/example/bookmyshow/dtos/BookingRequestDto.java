package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.Show;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDto {
    private Long userId;
    private Show showId;
    private List<Long> showSeatIds;
}
