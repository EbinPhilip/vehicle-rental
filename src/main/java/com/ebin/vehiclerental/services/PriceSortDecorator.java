package com.ebin.vehiclerental.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.entities.Vehicle;

public class PriceSortDecorator extends VehicleAvailabilityDecorator {

    public PriceSortDecorator(VehicleAvailabilityService vehicleAvailabilityService) {

        super(vehicleAvailabilityService);
    }

    @Override
    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime) {

        List<Vehicle> vehiclesList = super.getAvailableByBranch(branchId, startTime, endTime);
        return sortVehicles(vehiclesList);
    }

    @Override
    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime, int endTime) {

        List<Vehicle> vehiclesList = super.getAvailableByBranchAndVehicleType(branchId, vehicleType, startTime, endTime);
        return sortVehicles(vehiclesList);
    }

    private List<Vehicle> sortVehicles(List<Vehicle> vehiclesList) {

        return vehiclesList.stream()
                .sorted(new Comparator<Vehicle>() {
                    public int compare(Vehicle v1, Vehicle v2) {
                        return ((Double) v1.getPrice()).compareTo(v2.getPrice());
                    }
                }).collect(Collectors.toList());
    }
}
