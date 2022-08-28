package com.ebin.vehiclerental.utils.bookingstrategy;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface BookingStrategy {

    public Vehicle getVehicleToBook(List<Vehicle> vehicles);
}
