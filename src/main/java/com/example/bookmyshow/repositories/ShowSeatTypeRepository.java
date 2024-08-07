package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.SeatTypeShow;
import com.example.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<SeatTypeShow,Long> {
    public List<SeatTypeShow> findAllByShow(Show show);
}
