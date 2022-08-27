package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Vehicle;

public interface BranchService {

    public boolean createBranch(String branchName, List<String> vehicleTypes);

    public boolean addVehicleToBranch(String branchName, String vehicleType,
            int startTime, int endTime, double price);
    
    List<Vehicle> getVehiclesByBranch(String branchName);
}
