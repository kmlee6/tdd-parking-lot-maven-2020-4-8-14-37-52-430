package com.oocl;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;
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
        expectedException.expect(UnrecognizedParkingTicketException.class);
        ParkingTicket ticket = parkingLot.park(car);
        ParkingTicket fakeTicket = new ParkingTicket();
        parkingLot.fetch(fakeTicket);
    }

    @Test
    public void should_not_return_car_when_not_provide_ticket() {
        expectedException.expect(NoTicketException.class);
        ParkingTicket ticket = parkingLot.park(null);
        parkingLot.fetch(ticket);
    }

    @Test
    public void should_not_return_car_when_ticket_has_used() {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        ParkingTicket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        parkingLot.fetch(ticket);
    }

    @Test
    public void should_not_park_when_parking_lot_full() {
        expectedException.expect(NotEnoughPositionException.class);

        ParkingLot smallParkingLot = new ParkingLot(1);
        smallParkingLot.park(car);
        Car benz = new Car();
        smallParkingLot.park(benz);
    }
}
