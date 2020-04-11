package com.oocl.model.parking_boy;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.ParkingTicket;

public class ParkingBoy {
//    private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLots){
//        this.parkingLots.addAll(Arrays.asList(parkingLots));
        this.parkingLot = parkingLots;
    }

    public boolean isNotACar(Car car) {
        return car == null;
    }

    public boolean isDoublePark(Car car) {
        return parkingLot.containsCar(car);
    }

    public void parkingLotCapacityChecking() throws NotEnoughPositionException {
        if(parkingLot.isFull()){
            throw new NotEnoughPositionException();
        }
    }

    public ParkingTicket park(Car car){
//        ParkingLot nextAvailableParkingLot = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
//        return nextAvailableParkingLot.park(car);
        if (isNotACar(car) || isDoublePark(car)) {
            return null;
        }
        parkingLotCapacityChecking();
        return parkingLot.park(car);
    }

    public void isValidTicket(ParkingTicket ticket) throws UnrecognizedParkingTicketException, NoTicketException {
        if(ticket==null){
            throw new NoTicketException();
        }
        if(!parkingLot.isRecognizedParkingTicket(ticket)){
            throw new UnrecognizedParkingTicketException();
        }
    }

    public Car fetch(ParkingTicket ticket) {
        isValidTicket(ticket);
        return parkingLot.fetch(ticket);
    }
}
