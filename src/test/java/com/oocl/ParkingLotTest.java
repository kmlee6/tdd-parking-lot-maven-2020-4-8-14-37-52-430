package com.oocl;

import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.ParkingTicket;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    ParkingLot parkingLot;
    Car car;

    @Before
    public void setup() {
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    public void should_return_ticket_when_part_car() {
        ParkingTicket ticket = parkingLot.park(car);
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_provide_corresponding_ticket() {
        ParkingTicket ticket = parkingLot.park(car);
        assertEquals(car, parkingLot.fetch(ticket));
    }

    @Test
    public void should_return_corresponding_car_when_providing_ticket_given_parking_multiple_car() {
        ParkingTicket ticket = parkingLot.park(car);
        Car benz = new Car();
        ParkingTicket benzTicket = parkingLot.park(benz);
        assertEquals(benz, parkingLot.fetch(benzTicket));
    }

    @Test
    public void should_not_return_car_when_providing_invalid_ticket() {
        ParkingTicket ticket = parkingLot.park(car);
        ParkingTicket fakeTicket = new ParkingTicket();
        assertNull(parkingLot.fetch(fakeTicket));
    }

    @Test
    public void should_not_return_car_when_not_provide_ticket() {
        ParkingTicket ticket = parkingLot.park(null);
        assertNull(parkingLot.fetch(ticket));
    }

    @Test
    public void should_not_return_car_when_ticket_has_used() {
        ParkingTicket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        assertNull(parkingLot.fetch(ticket));
    }

    @Test
    public void should_not_park_when_parking_lot_full() {
        ParkingLot smallParkingLot = new ParkingLot(1);
        smallParkingLot.park(car);
        Car benz = new Car();
        assertNull(smallParkingLot.park(benz));
    }
}
