package com.oocl;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException(){
        super("unrecognized parking ticket.");
    }
}
