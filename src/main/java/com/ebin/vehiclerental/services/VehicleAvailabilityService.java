package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface VehicleAvailabilityService {

    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime);

    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime, int endTime);
}
