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
    public void should_not_return_car_when_providing_invalid_ticket(){
        ParkingTicket ticket = parkingLot.park(car);
        ParkingTicket fakeTicket = new ParkingTicket();
        Assert.assertFalse(car.equals(parkingLot.fetch(fakeTicket)));
    }
}
