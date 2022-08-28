package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.repositories.BranchRepository;

public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;

    @Override
    public boolean createBranch(String branchName, List<String> vehicleTypes) {

        Branch branch = new Branch(branchName, vehicleTypes);
        return (branchRepository.save(branch) != null);
    }

    @Override
    public Branch getBranch(String branchName) {

        return branchRepository.findByBranchName(branchName);
    }
}
