package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public ParkingBoy(ParkingLot... parkingLots){
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public ParkingTicket park(Car car){
        ParkingLot nextAvailableParkingLot = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
        return nextAvailableParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.get(0).fetch(parkingTicket);
    }
}
