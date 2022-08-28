package com.ebin.vehiclerental.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ebin.vehiclerental.entities.Branch;

public class BranchRepositoryImpl implements BranchRepository {

    private Map<String, Branch> branches = new HashMap<>();

    @Override
    public Optional<Branch> findByBranchName(String branchName) {

        return Optional.ofNullable(branches.get(branchName));
    }

    @Override
    public Branch save(Branch branch) {

        return branches.put(branch.getName(), branch);
    }
}
