package com.oocl.model.parking_boy;

import com.oocl.exception.NotEnoughPositionException;
import com.oocl.model.ParkingLot;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot selectSuitableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .sorted(Comparator.comparing(ParkingLot::getUsage))
                .findFirst()
                .orElseThrow(NotEnoughPositionException::new);
    }
}
