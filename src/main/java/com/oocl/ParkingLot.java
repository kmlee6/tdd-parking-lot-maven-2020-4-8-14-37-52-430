package com.oocl;

import sun.security.krb5.internal.Ticket;

import java.util.HashMap;

public class ParkingLot {
    HashMap<ParkingTicket, Car> ticketCarHashMap = new HashMap<ParkingTicket, Car>();

    public ParkingTicket park(Car car) {
        ParkingTicket returnTicket = new ParkingTicket();
        ticketCarHashMap.put(returnTicket, car);
        return returnTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        return ticketCarHashMap.remove(ticket);
    }
}
