package com.oocl;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNot.not;

public class ParkingLotTest {
    ParkingLot parkingLot;
    Car car;
    @Before
    public void setup(){
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    public void should_return_ticket_when_part_car(){
        ParkingTicket ticket = parkingLot.park(car);
        Assert.assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_provide_corresponding_ticket(){
        ParkingTicket ticket = parkingLot.park(car);
        Assert.assertEquals(car, parkingLot.fetch(ticket));
    }

    @Test
    public void should_return_corresponding_car_when_providing_ticket_given_parking_multiple_car(){
        ParkingTicket ticket = parkingLot.park(car);
        Car benz = new Car();
        ParkingTicket benzTicket = parkingLot.park(benz);
        Assert.assertEquals(benz, parkingLot.fetch(benzTicket));
    }

    @Test
    public void should_not_return_car_when_providing_invalid_ticket(){
        ParkingTicket ticket = parkingLot.park(car);
        ParkingTicket fakeTicket = new ParkingTicket();
        Assert.assertFalse(car.equals(parkingLot.fetch(fakeTicket)));
    }

    @Test
    public void should_not_return_car_when_not_provide_ticket(){
        ParkingTicket ticket = parkingLot.park(null);
        Assert.assertNull(parkingLot.fetch(ticket));
    }

    @Test
    public void should_not_return_car_when_ticket_has_used(){
        ParkingTicket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        Assert.assertNull(parkingLot.fetch(ticket));
    }

    @Test
    public void should_not_park_when_parking_lot_full(){
        ParkingLot smallParkingLot = new ParkingLot(1);
        smallParkingLot.park(car);
        Car benz = new Car();
        ParkingTicket benzTicket = smallParkingLot.park(benz);
        Assert.assertNull(parkingLot.fetch(benzTicket));
    }

    @Test
    public void should_park_by_parking_boy(){
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(car);
        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_providing_ticket_to_parking_boy(){
        ParkingBoy tom = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = tom.park(car);
        Assert.assertEquals(car, tom.fetch(parkingTicket));
    }
}
