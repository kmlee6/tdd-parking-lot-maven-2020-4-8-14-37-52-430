package com.oocl.model;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;

public class ParkingLot {
    static final int DEFAULT_CAPACITY = 10;
    int capacity;
    HashMap<ParkingTicket, Car> ticketCarHashMap = new HashMap<>();

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean isNotACar(Car car) {
        return car == null;
    }

    public boolean isDoublePark(Car car) {
        return ticketCarHashMap.containsValue(car);
    }

    public boolean isFull() {
        return capacity == ticketCarHashMap.size();
    }

    public void parkingLotAvailabilityChecking() throws NotEnoughPositionException{
        if(isFull()){
            throw new NotEnoughPositionException();
        }
    }

    public ParkingTicket park(Car car) {
        parkingLotAvailabilityChecking();
        if (isNotACar(car) || isDoublePark(car)) {
            return null;
        }
        ParkingTicket returnTicket = new ParkingTicket();
        ticketCarHashMap.put(returnTicket, car);
        return returnTicket;
    }

    public void isValidTicket(ParkingTicket ticket) throws UnrecognizedParkingTicketException, NoTicketException {
        if(ticket==null){
            throw new NoTicketException();
        }
        if(!ticketCarHashMap.containsKey(ticket)){
            throw new UnrecognizedParkingTicketException();
        }
    }

    public Car fetch(ParkingTicket ticket){
        isValidTicket(ticket);
        return ticketCarHashMap.remove(ticket);
    }
}
