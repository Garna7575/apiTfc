package com.tfc.apitfc.service;

import com.tfc.apitfc.domain.dao.ReservationInterface;
import com.tfc.apitfc.domain.dto.ReservationDTO;
import com.tfc.apitfc.domain.entity.CommonArea;
import com.tfc.apitfc.domain.entity.Neighbor;
import com.tfc.apitfc.domain.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService{

    @Autowired
    private ReservationInterface reservationInterface;

    @Autowired
    private CommonAreaService commonAreaService;

    @Autowired
    private NeighborService neighborService;


    @Transactional
    public Reservation createReservation(ReservationDTO reservationDTO) {
        System.out.println(reservationDTO.toString());
        CommonArea commonArea = commonAreaService.getCommonAreaById(reservationDTO.getCommonAreaId());
        Neighbor neighbor = neighborService.findById(reservationDTO.getNeighborId());

        System.out.println(commonArea);

        if (!isCommonAreaAvailable(commonArea.getId(), reservationDTO.getStartTime(), reservationDTO.getEndTime())) {
            throw new IllegalArgumentException("El área común no está disponible en el horario solicitado");
        }

        Reservation reservation = new Reservation();

        reservation.setCommonArea(commonArea);
        reservation.setNeighbor(neighbor);
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setCommonArea(commonArea);
        reservation.setNeighbor(neighbor);

        return reservationInterface.save(reservation);
    }


    public Reservation getReservationById(Integer id) {
        return reservationInterface.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    public List<Reservation> getAllReservations() {
        return reservationInterface.findAll();
    }

    public List<Reservation> getReservationsByCommonArea(Integer commonAreaId) {
        return reservationInterface.findByCommonAreaId(commonAreaId);
    }

    public List<Reservation> getReservationsByDay(LocalDateTime day, int id) {
        LocalDateTime startOfDay = day.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = day.toLocalDate().atTime(23, 59, 59);

        return reservationInterface.findByStartTimeBetweenAndNeighborId(startOfDay, endOfDay, id);
    }


    public List<Reservation> getReservationsByNeighbor(Integer neighborId) {
        return reservationInterface.findByNeighborId(neighborId);
    }

    public boolean isCommonAreaAvailable(Integer commonAreaId, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("La hora de inicio no puede ser posterior a la hora de fin");
        }

        List<Reservation> overlapping = reservationInterface.findOverlappingReservations(
                commonAreaId, startTime, endTime);

        return overlapping.isEmpty();
    }

    @Transactional
    public void cancelReservation(Integer reservationId) {
        Reservation reservation = getReservationById(reservationId);
        reservationInterface.delete(reservation);
    }

    @Transactional
    public Reservation updateReservation(Integer reservationId, Reservation updatedReservation) {
        Reservation existing = getReservationById(reservationId);

        if (!updatedReservation.getStartTime().equals(existing.getStartTime()) ||
                !updatedReservation.getEndTime().equals(existing.getEndTime())) {

            if (!isCommonAreaAvailable(
                    existing.getCommonArea().getId(),
                    updatedReservation.getStartTime(),
                    updatedReservation.getEndTime())) {
                throw new IllegalArgumentException("El área común no está disponible en el nuevo horario solicitado");
            }
        }

        existing.setStartTime(updatedReservation.getStartTime());
        existing.setEndTime(updatedReservation.getEndTime());

        return reservationInterface.save(existing);
    }
}
