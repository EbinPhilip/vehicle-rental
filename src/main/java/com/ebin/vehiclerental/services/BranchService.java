package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Branch;

public interface BranchService {

    public void createBranch(String branchName, List<String> vehicleTypes);

    public void addVehicleToBranch(String branchName, String vehicleType,
            String vehicleId, double price);

    public Branch getBranch(String branchName);
}
