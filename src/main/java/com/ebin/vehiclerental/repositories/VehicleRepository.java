package com.ebin.vehiclerental.repositories;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface VehicleRepository {

    public Vehicle findByVehicleId(String vehicleId);

    public List<Vehicle> findByBranchId(String branchId);

    public List<Vehicle> findByBranchIdAndVehicleType(String branchId, String vehicleType);

    public Vehicle save(Vehicle vehicle);
}
