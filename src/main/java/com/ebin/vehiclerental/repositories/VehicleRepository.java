package com.ebin.vehiclerental.repositories;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface VehicleRepository {

    public List<Vehicle> findByBranchId(String branchId);

    public List<Vehicle> findByBranchIdAndVehicleType(String branchId, String vehicleType);

    public int countByBranchIdAndVehicleType(String branchId, String vehicleType);

    public Vehicle save(Vehicle vehicle);
}
