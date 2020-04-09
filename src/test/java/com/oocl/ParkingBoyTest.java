package com.oocl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class ParkingBoyTest {
    ParkingLot parkingLot;
    Car car;

    @Before
    public void setup() {
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    public void should_park_by_parking_boy() {
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_providing_ticket_to_parking_boy() {
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(car);
        assertEquals(car, tom.fetch(parkingTicket));
    }

    @Test
    public void should_not_double_park_for_a_single_car() {
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(car);
        ParkingTicket anotherTicket = tom.park(car);
        assertNull(anotherTicket);
    }

    @Test
    public void should_not_park_null(){
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(null);
        assertNull(parkingTicket);
    }
}
