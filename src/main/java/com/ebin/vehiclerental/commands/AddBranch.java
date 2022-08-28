package com.ebin.vehiclerental.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ebin.vehiclerental.services.BranchService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AddBranch implements Command {

    @NonNull
    BranchService branchService;

    @Override
    public void run(List<String> tokens) {

        try {
            String branchName = tokens.get(1).trim(); 
            String vehicleTypesString = tokens.get(2).trim();
            List<String> vehicleTypes = Arrays.asList(vehicleTypesString.split(","))
                    .stream()
                    .map((type)->{
                        return type.trim();
                    }).collect(Collectors.toList());

            branchService.createBranch(branchName, vehicleTypes);
            System.out.println("TRUE");
        } catch (RuntimeException e) {

            System.out.println("FALSE");
        }
    }
}
