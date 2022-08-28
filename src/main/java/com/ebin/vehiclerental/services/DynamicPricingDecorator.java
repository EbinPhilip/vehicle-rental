package com.ebin.vehiclerental.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.repositories.VehicleRepository;

import lombok.NonNull;

public class DynamicPricingDecorator extends VehicleAvailabilityDecorator {

    private VehicleRepository vehicleRepository;

    private BranchService branchService;

    public DynamicPricingDecorator(VehicleAvailabilityService vehicleAvailabilityService,
            @NonNull VehicleRepository vehicleRepository, @NonNull BranchService branchService) {
        super(vehicleAvailabilityService);
        this.vehicleRepository = vehicleRepository;
        this.branchService = branchService;
    }

    @Override
    public List<Vehicle> getAvailableByBranch(String branchId, int startTime, int endTime) {

        List<Vehicle> vehiclesList = super.getAvailableByBranch(branchId, startTime, endTime);
        return applyDynamicPricing(branchId, vehiclesList);
    }

    @Override
    public List<Vehicle> getAvailableByBranchAndVehicleType(String branchId, String vehicleType, int startTime,
            int endTime) {

        List<Vehicle> vehiclesList = super.getAvailableByBranchAndVehicleType(branchId, vehicleType, startTime, endTime);
        return applyDynamicPricing(branchId, vehiclesList);
    }

    private List<Vehicle> applyDynamicPricing(String branchId, List<Vehicle> vehiclesList) {

        Branch branch = branchService.getBranch(branchId);
        List<String> vehicleTypes = branch.getVehicleTypes();

        HashMap<String, Integer> totalTypeCount = new HashMap<>();
        for (String type : vehicleTypes) {
            int count = vehicleRepository.countByBranchIdAndVehicleType(branchId, type);
            totalTypeCount.put(type, count);
        }

        HashMap<String, Integer> availableTypeCount = new HashMap<>();
        for (Vehicle vehicle : vehiclesList) {
            String type = vehicle.getVehicleType();
            if (availableTypeCount.containsKey(type)) {
                availableTypeCount.put(type, availableTypeCount.get(type) + 1);
            } else {
                availableTypeCount.put(type, 1);
            }
        }

        List<Vehicle> vehicles = vehiclesList.stream()
                .map((vehicle) -> {
                    int total = totalTypeCount.get(vehicle.getVehicleType());
                    int available = availableTypeCount.get(vehicle.getVehicleType());

                    if (((double) available) / total <= 0.2) {
                        return new Vehicle(vehicle.getVehicleId(), vehicle.getBranchName(),
                                vehicle.getVehicleType(), vehicle.getPrice() * 1.1);
                    } else {
                        return vehicle;
                    }
                }).collect(Collectors.toList());
        
        return vehicles;
    }

}
