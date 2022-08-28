package com.ebin.vehiclerental.services;

import java.util.List;

import com.ebin.vehiclerental.entities.Branch;

public interface BranchService {

    public boolean createBranch(String branchName, List<String> vehicleTypes);

    public Branch getBranch(String branchName);
}
