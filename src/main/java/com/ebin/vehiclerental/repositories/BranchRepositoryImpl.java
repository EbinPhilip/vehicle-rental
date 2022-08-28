package com.ebin.vehiclerental.repositories;

import java.util.HashMap;
import java.util.Map;

import com.ebin.vehiclerental.entities.Branch;

public class BranchRepositoryImpl implements BranchRepository {

    private Map<String, Branch> branches = new HashMap<>();

    @Override
    public Branch findByBranchName(String branchName) {

        return branches.values()
                .stream()
                .filter((i) -> {
                    return (i.getName().equals(branchName));
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Branch save(Branch branch) {

        return branches.put(branch.getName(), branch);
    }
}
