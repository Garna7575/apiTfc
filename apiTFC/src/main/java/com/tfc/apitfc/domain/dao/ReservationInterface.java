package com.tfc.apitfc.domain.dao;

import com.tfc.apitfc.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationInterface extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByCommonAreaId(Integer commonAreaId);

    List<Reservation> findByNeighborId(Integer neighborId);

    List<Reservation> findByStartTimeBetweenAndNeighborId(LocalDateTime start, LocalDateTime end, int neighborId);

    @Query("SELECT r FROM Reservation r WHERE r.commonArea.id = :commonAreaId " +
            "AND (r.startTime < :endTime AND r.endTime > :startTime)")
    List<Reservation> findOverlappingReservations(
            @Param("commonAreaId") Integer commonAreaId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
}
