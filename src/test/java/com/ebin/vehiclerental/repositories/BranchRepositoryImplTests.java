package com.ebin.vehiclerental.repositories;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ebin.vehiclerental.entities.Branch;

public class BranchRepositoryImplTests {

    private BranchRepositoryImpl branchRepository;

    @BeforeEach
    void setup() {
        this.branchRepository = new BranchRepositoryImpl();
    }

    @Test
    @DisplayName("Test saving a branch")
    public void saveBranch() {

        Branch branch = new Branch("B1", Arrays.asList("CAR", "JEEP"));
        Assertions.assertNotNull(branchRepository.save(branch));
    }

    @Test
    @DisplayName("Test getting a branch")
    public void getBranch() {

        Branch branch = new Branch("B1", Arrays.asList("CAR", "JEEP"));
        branchRepository.save(branch);

        Branch branch2 = branchRepository.findByBranchName("B1").get();
        Assertions.assertEquals(branch, branch2);
    }

    @Test
    @DisplayName("Test getting a branch does not exist")
    public void getBranchNotExisting() {

        Assertions.assertThrows(NoSuchElementException.class,
                () -> {
                    branchRepository.findByBranchName("B1").get();
                });
    }
}
