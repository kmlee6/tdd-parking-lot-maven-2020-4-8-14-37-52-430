package com.oocl;

import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.parking_boy.SmartParkingBoy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmartParkingBoyTest {
    @Test
    public void should_park_at_first_parking_lot_given_parked_by_smart_parking_boy() {
        ParkingLot smallParkingLot = new ParkingLot(1);
        ParkingLot bigParkingLot = new ParkingLot(100);

        SmartParkingBoy tom = new SmartParkingBoy(smallParkingLot, bigParkingLot);
        Car car = new Car();
        tom.park(car);
        assertEquals(1, smallParkingLot.getFreeParkingSpace());
        assertEquals(99, bigParkingLot.getFreeParkingSpace());
    }
}
