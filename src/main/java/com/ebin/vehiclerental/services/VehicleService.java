package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface VehicleService {

    public boolean addVehicleToBranch(String branchName, String vehicleType,
            String vehicleId, double price);

    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime);

    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime, int endTime);
}
