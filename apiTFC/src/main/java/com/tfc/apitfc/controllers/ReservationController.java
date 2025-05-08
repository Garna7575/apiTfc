package com.tfc.apitfc.controllers;

import com.tfc.apitfc.domain.dto.ReservationDTO;
import com.tfc.apitfc.domain.entity.Reservation;
import com.tfc.apitfc.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pocket/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO) {
        System.out.println(reservationDTO);
        Reservation created = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok().body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Reservation>> getReservationsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date, @RequestParam("id") int id) {
        System.out.println(date);
        List<Reservation> reservations = reservationService.getReservationsByDay(date, id);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/common-area/{commonAreaId}")
    public ResponseEntity<List<Reservation>> getReservationsByCommonArea(@PathVariable Integer commonAreaId) {
        List<Reservation> reservations = reservationService.getReservationsByCommonArea(commonAreaId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/neighbor/{neighborId}")
    public ResponseEntity<List<Reservation>> getReservationsByNeighbor(@PathVariable Integer neighborId) {
        List<Reservation> reservations = reservationService.getReservationsByNeighbor(neighborId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkAvailability(
            @RequestParam Integer commonAreaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        boolean isAvailable = reservationService.isCommonAreaAvailable(commonAreaId, startTime, endTime);
        return ResponseEntity.ok(isAvailable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Integer id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Integer id,
            @RequestBody Reservation updatedReservation) {
        Reservation reservation = reservationService.updateReservation(id, updatedReservation);
        return ResponseEntity.ok(reservation);
    }
}