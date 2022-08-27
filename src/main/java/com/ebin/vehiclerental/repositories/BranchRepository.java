package com.ebin.vehiclerental.repositories;

import com.ebin.vehiclerental.entities.Branch;

public interface BranchRepository {

    public Branch findByBranchName(String branchName);

    public Branch save(Branch branch);
}
