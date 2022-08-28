package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.exceptions.BranchNotFoundException;
import com.ebin.vehiclerental.exceptions.InvalidVehicleTypeException;
import com.ebin.vehiclerental.repositories.BranchRepository;
import com.ebin.vehiclerental.repositories.VehicleRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    @NonNull
    private BranchRepository branchRepository;

    @NonNull
    private VehicleRepository vehicleRepository;

    @Override
    public void createBranch(String branchName, List<String> vehicleTypes) {

        Branch branch = new Branch(branchName, vehicleTypes);
        branchRepository.save(branch);
    }

    @Override
    public void addVehicleToBranch(String branchName, String vehicleType,
            String vehicleId, double price) {

        Branch branch = getBranch(branchName);
        if (branch.getVehicleTypes().contains(vehicleType)) {
            Vehicle vehicle = new Vehicle(vehicleId, branchName, vehicleType, price);
            vehicleRepository.save(vehicle);
        } else {
            throw new InvalidVehicleTypeException();
        }
    }

    @Override
    public Branch getBranch(String branchName) {

        return branchRepository.findByBranchName(branchName)
                .orElseThrow(BranchNotFoundException::new);
    }
}
