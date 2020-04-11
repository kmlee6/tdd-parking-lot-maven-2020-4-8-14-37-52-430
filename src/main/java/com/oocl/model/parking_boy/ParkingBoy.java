package com.oocl.model.parking_boy;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.ParkingTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public boolean isNotACar(Car car) {
        return car == null;
    }

    public boolean isDoublePark(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containsCar(car))
                .count() > 0;
    }

    public ParkingLot selectSuitableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NotEnoughPositionException::new);
    }

    public ParkingTicket park(Car car) {
        if (isNotACar(car) || isDoublePark(car)) {
            return null;
        }
        ParkingLot selectedParkingLot = selectSuitableParkingLot();
        return selectedParkingLot.park(car);
    }

    public ParkingLot searchTargetParkingLot(ParkingTicket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isRecognizedParkingTicket(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null) {
            throw new NoTicketException();
        }
        ParkingLot targetParkingLot = searchTargetParkingLot(ticket);
        return targetParkingLot.fetch(ticket);
    }
}
