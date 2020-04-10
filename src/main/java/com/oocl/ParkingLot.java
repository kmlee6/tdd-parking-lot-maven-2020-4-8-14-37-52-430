package com.oocl;

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

    public boolean isFull() {
        return capacity == ticketCarHashMap.size();
    }

    public boolean isDoublePark(Car car) {
        return ticketCarHashMap.containsValue(car);
    }

    public ParkingTicket park(Car car) {
        if (isFull() || isNotACar(car) || isDoublePark(car)) {
            return null;
        }
        ParkingTicket returnTicket = new ParkingTicket();
        ticketCarHashMap.put(returnTicket, car);
        return returnTicket;
    }

    public void isValidTicket(ParkingTicket ticket) throws UnrecognizedParkingTicketException{
        if(!ticketCarHashMap.containsKey(ticket)){
            throw new UnrecognizedParkingTicketException();
        }
    }

    public Car fetch(ParkingTicket ticket){
        isValidTicket(ticket);
        return ticketCarHashMap.remove(ticket);
    }
}
