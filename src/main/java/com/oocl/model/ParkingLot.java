package com.oocl.model;

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

    public boolean isFull() {
        return capacity == ticketCarHashMap.size();
    }

    public boolean containsCar(Car car) {
        return ticketCarHashMap.containsValue(car);
    }

    public ParkingTicket park(Car car) {
        if(isFull()){
            return null;
        }
        ParkingTicket returnTicket = new ParkingTicket();
        ticketCarHashMap.put(returnTicket, car);
        return returnTicket;
    }

    public boolean isRecognizedParkingTicket(ParkingTicket ticket){
        return ticketCarHashMap.containsKey(ticket);
    }

    public Car fetch(ParkingTicket ticket){
        return ticketCarHashMap.remove(ticket);
    }
}
