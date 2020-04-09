package com.oocl;

import sun.security.krb5.internal.Ticket;

import java.util.HashMap;

public class ParkingLot {
    static final int DEFAULT_CAPACITY = 10;
    int capacity;
    HashMap<ParkingTicket, Car> ticketCarHashMap = new HashMap<ParkingTicket, Car>();

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == ticketCarHashMap.size();
    }

    public boolean isDoublePark(Car car){
        return ticketCarHashMap.containsValue(car);
    }

    public ParkingTicket park(Car car) {
        if (isFull() || isDoublePark(car)) {
            return null;
        }
        ParkingTicket returnTicket = new ParkingTicket();
        ticketCarHashMap.put(returnTicket, car);
        return returnTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        return ticketCarHashMap.remove(ticket);
    }
}
