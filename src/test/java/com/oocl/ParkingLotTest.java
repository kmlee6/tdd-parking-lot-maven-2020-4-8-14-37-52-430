package com.oocl;

import junit.framework.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_part_car(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);
        Assert.assertNotNull(ticket);
    }
}
