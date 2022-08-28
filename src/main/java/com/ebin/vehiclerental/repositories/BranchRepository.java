package com.ebin.vehiclerental.repositories;

import java.util.Optional;

import com.ebin.vehiclerental.entities.Branch;

public interface BranchRepository {

    public Optional<Branch> findByBranchName(String branchName);

    public Branch save(Branch branch);
}
