package com.ebin.vehiclerental.commands;

import java.util.List;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.services.VehicleService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DisplayVehicles implements Command {

    @NonNull
    private final VehicleService vehicleService;

    @Override
    public void run(List<String> tokens) {

        try {
            String branchId = tokens.get(1).trim();
            int startTime = Integer.valueOf(tokens.get(2).trim());
            int endTime = Integer.valueOf(tokens.get(3).trim());

            List<String> vehicleIDs = vehicleService.getAvailableByBranch(branchId, startTime, endTime)
                    .stream()
                    .map((vehicle) -> {
                        return vehicle.getVehicleId();
                    })
                    .collect(Collectors.toList());
            String vehicleIdsString = String.join(",", vehicleIDs);
            System.out.println(vehicleIdsString);
        } catch (RuntimeException e) {
            // no vehicles to print
            System.out.println();
        }
    }
}
