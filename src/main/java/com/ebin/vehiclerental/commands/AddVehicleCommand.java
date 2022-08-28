package com.ebin.vehiclerental.commands;

import java.util.List;

import com.ebin.vehiclerental.services.BranchService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddVehicleCommand implements Command {
  
    @NonNull
    BranchService branchService;

    @Override
    public void run(List<String> tokens) {

        try {
            String branchName = tokens.get(1).trim();
            String vehicleType = tokens.get(2).trim();
            String vehicleId = tokens.get(3).trim();
            double price = Double.valueOf(tokens.get(4).trim());

            branchService.addVehicleToBranch(branchName, vehicleType, vehicleId, price);
            System.out.println("TRUE");
        } catch (RuntimeException e) {
            System.out.println("FALSE");
        }
    }
}
