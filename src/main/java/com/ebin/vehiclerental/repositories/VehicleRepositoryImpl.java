package com.ebin.vehiclerental.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.entities.Vehicle;

public class VehicleRepositoryImpl implements VehicleRepository {

    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    @Override
    public List<Vehicle> findByBranchId(String branchId) {

        return vehicleMap.values()
                .stream()
                .filter((i) -> {
                    return i.getBranchName().equals(branchId);
                }).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByBranchIdAndVehicleType(String branchId, String vehicleType) {

        return vehicleMap.values()
                .stream()
                .filter((i) -> {
                    return (i.getBranchName().equals(branchId)
                            && i.getVehicleType().equals(vehicleType));
                }).collect(Collectors.toList());
    }

    @Override
    public int countByBranchIdAndVehicleType(String branchId, String vehicleType) {

        return findByBranchIdAndVehicleType(branchId, vehicleType).size();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {

        return vehicleMap.put(vehicle.getVehicleId(), vehicle);
    }
}
