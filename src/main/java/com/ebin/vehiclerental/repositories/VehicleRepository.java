package com.ebin.vehiclerental.repositories;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface VehicleRepository {

    public List<Vehicle> findByBranchId();

    public List<Vehicle> findByBranchIdAndVehicleType();

    public Vehicle save(Vehicle vehicle);
}
