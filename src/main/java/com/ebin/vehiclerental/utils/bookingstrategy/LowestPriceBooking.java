package com.ebin.vehiclerental.utils.bookingstrategy;

import java.util.Comparator;
import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public class LowestPriceBooking implements BookingStrategy{

    public Vehicle getVehicleToBook(List<Vehicle> vehicles) {

        return vehicles.stream()
                .sorted(new Comparator<Vehicle>() {
                    public int compare(Vehicle v1, Vehicle v2) {
                        return ((Double) v1.getPrice()).compareTo(v2.getPrice());
                    }
                }).findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
