package com.oocl;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.ParkingTicket;
import com.oocl.model.parking_boy.ParkingBoy;
import com.oocl.model.parking_boy.SmartParkingBoy;
import com.oocl.model.parking_boy.SuperSmartParkingBoy;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class ParkingBoyTest {
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
    public void should_not_park_null() {
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(null);
        assertNull(parkingTicket);
    }

    @Test
    public void should_throw_exception_when_providing_unrecognized_ticket() {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        ParkingBoy tom = new ParkingBoy(parkingLot);
        tom.park(car);
        ParkingTicket fakeTicket = new ParkingTicket();
        tom.fetch(fakeTicket);
    }

    @Test
    public void should_throw_exception_when_not_providing_ticket() {
        expectedException.expect(NoTicketException.class);
        ParkingBoy tom = new ParkingBoy(parkingLot);
        tom.park(car);
        tom.fetch(null);
    }

    @Test
    public void should_throw_exception_when_parking_lot_is_full() {
        expectedException.expect(NotEnoughPositionException.class);
        ParkingLot smallParkingLot = new ParkingLot(1);
        ParkingBoy tom = new ParkingBoy(smallParkingLot);
        tom.park(car);
        Car benz = new Car();
        tom.park(benz);
    }

    @Test
    public void should_return_ticket_when_first_parking_lot_has_full_given_two_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy tom = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car lamborghini = new Car();
        Car benz = new Car();
        tom.park(lamborghini);
        ParkingTicket ticket = tom.park(benz);
        assertNotNull(ticket);
    }

    @Test
    public void should_park_at_second_parking_lot_when_parked_by_normal_parking_boy() {
        ParkingLot smallParkingLot = new ParkingLot(1);
        ParkingLot bigParkingLot = new ParkingLot(100);
        ParkingBoy tom = new ParkingBoy(smallParkingLot, bigParkingLot);
        Car car = new Car();
        tom.park(car);
        assertEquals(0, smallParkingLot.getFreeParkingSpace());
        assertEquals(100, bigParkingLot.getFreeParkingSpace());
    }

    @Test
    public void should_park_at_second_parking_lot_given_parked_by_smart_parking_boy() {
        ParkingLot smallParkingLot = new ParkingLot(1);
        ParkingLot bigParkingLot = new ParkingLot(100);
        SmartParkingBoy tom = new SmartParkingBoy(smallParkingLot, bigParkingLot);
        Car car = new Car();
        tom.park(car);
        assertEquals(1, smallParkingLot.getFreeParkingSpace());
        assertEquals(99, bigParkingLot.getFreeParkingSpace());
    }
}
