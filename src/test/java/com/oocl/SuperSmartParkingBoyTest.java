package com.oocl;

import com.oocl.model.Car;
import com.oocl.model.ParkingLot;
import com.oocl.model.parking_boy.SuperSmartParkingBoy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_park_at_first_parking_lot_when_parking_last_car_given_parked_by_super_smart_parking_boy() {
        ParkingLot smallParkingLot = new ParkingLot(2);
        ParkingLot bigParkingLot = new ParkingLot(5);

        SuperSmartParkingBoy tom = new SuperSmartParkingBoy(smallParkingLot, bigParkingLot);
        Car carA = new Car();
        Car carB = new Car();
        Car carC = new Car();
        Car carD = new Car();
        Car carE = new Car();
        tom.park(carA);
        tom.park(carB);
        tom.park(carC);
        tom.park(carD);
        assertEquals(2, bigParkingLot.getFreeParkingSpace());
        tom.park(carE);
        assertEquals(0, smallParkingLot.getFreeParkingSpace());
    }
}
