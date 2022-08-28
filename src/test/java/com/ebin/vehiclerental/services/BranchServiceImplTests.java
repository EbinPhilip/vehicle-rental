package com.ebin.vehiclerental.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ebin.vehiclerental.entities.Branch;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.exceptions.BranchNotFoundException;
import com.ebin.vehiclerental.exceptions.InvalidVehicleTypeException;
import com.ebin.vehiclerental.repositories.BranchRepository;
import com.ebin.vehiclerental.repositories.VehicleRepository;

@ExtendWith(MockitoExtension.class)
public class BranchServiceImplTests {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private BranchServiceImpl branchServiceImpl;

    @Test
    @DisplayName("Test creating a branch")
    public void createBranchTest() {

        branchServiceImpl.createBranch("B1", Arrays.asList("CAR", "JEEP"));
        verify(branchRepository, times(1)).save(any(Branch.class));
    }

    @Test
    @DisplayName("Test addVehicleToBranch")
    public void addVehicleToBranchTest() {

        Branch branch = new Branch("B1", Arrays.asList("CAR", "JEEP"));
        when(branchRepository.findByBranchName(anyString())).thenReturn(Optional.of(branch));

        branchServiceImpl.addVehicleToBranch("B1", "CAR", "v1", 200.0);

        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    @DisplayName("Test addVehicleToBranch with wrong type")
    public void addVehicleToBranchTestWrongType() {

        Branch branch = new Branch("B1", Arrays.asList("CAR", "JEEP"));
        when(branchRepository.findByBranchName("B1")).thenReturn(Optional.of(branch));

        Assertions.assertThrows(InvalidVehicleTypeException.class,
                () -> {
                    branchServiceImpl.addVehicleToBranch(
                        "B1", "PLANE", "v1", 200.0);
                });

    }

    @Test
    @DisplayName("Test getBranch")
    public void getBranchTest() {

        Branch branch = new Branch("B1", Arrays.asList("CAR", "JEEP"));
        when(branchRepository.findByBranchName("B1")).thenReturn(Optional.of(branch));

        Branch actual = branchServiceImpl.getBranch("B1");
        Assertions.assertEquals(branch, actual);
    }

    @Test
    @DisplayName("Test getBranch not existing")
    public void getBranchTestNotExisting() {

        when(branchRepository.findByBranchName("B1")).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(BranchNotFoundException.class,
                () -> {
                    branchServiceImpl.getBranch ("B1");
                });
    }
}
