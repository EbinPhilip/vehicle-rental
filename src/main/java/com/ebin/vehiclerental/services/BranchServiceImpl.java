package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.exceptions.BranchNotFoundException;
import com.ebin.vehiclerental.repositories.BranchRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    @NonNull
    private BranchRepository branchRepository;

    @Override
    public void createBranch(String branchName, List<String> vehicleTypes) {

        Branch branch = new Branch(branchName, vehicleTypes);
        branchRepository.save(branch);
    }

    @Override
    public Branch getBranch(String branchName) {

        return branchRepository.findByBranchName(branchName)
                .orElseThrow(BranchNotFoundException::new);
    }
}
