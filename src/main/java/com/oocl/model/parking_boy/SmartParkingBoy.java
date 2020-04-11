package com.oocl.model.parking_boy;

import com.oocl.exception.NotEnoughPositionException;
import com.oocl.model.ParkingLot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLots){
        super(parkingLots);
    }

    @Override
    public ParkingLot selectSuitableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .sorted(Comparator.comparing(ParkingLot::getFreeParkingSpace).reversed())
                .findFirst()
                .orElseThrow(NotEnoughPositionException::new);
    }
}
