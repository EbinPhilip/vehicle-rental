package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public abstract class VehicleAvailabilityDecorator implements VehicleAvailabilityService {

    @NonNull
    private VehicleAvailabilityService vehicleAvailabilityService;

    @Override
    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime) {
        return vehicleAvailabilityService.getAvailableByBranch(branchId, startTime, endTime);  
    }

    @Override
    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime, int endTime) {
        return vehicleAvailabilityService.getAvailableByBranchAndVehicleType(branchId, vehicleType, startTime, endTime);
    }
}
