package com.ebin.vehiclerental.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ebin.vehiclerental.entities.Vehicle;

public class VehicleRepositoryImplTests {


    private VehicleRepositoryImpl vehicleRepositoryImpl;

    @BeforeEach
    void setup() {
        this.vehicleRepositoryImpl = new VehicleRepositoryImpl();
    }

    @Test
    @DisplayName("Test saving a vehicle")
    public void saveBranch() {

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 100.0);
        Assertions.assertNotNull(vehicleRepositoryImpl.save(vehicle));
    }

    @Test
    @DisplayName("Test findByBranchId")
    public void findByBranchIdTest() {

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle);

        Vehicle vehicle2 = new Vehicle("v2", "B1", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle2);

        Vehicle vehicle3 = new Vehicle("v3", "B2", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle3);

        List<Vehicle> vehicles = vehicleRepositoryImpl.findByBranchId("B1");
        Assertions.assertTrue(vehicles.contains(vehicle));
        Assertions.assertTrue(vehicles.contains(vehicle2));
        Assertions.assertTrue(!vehicles.contains(vehicle3));
    }

    @Test
    @DisplayName("Test findByBranchIdAndVehicleType")
    public void findByBranchIdAndVehicleTypeTest() {

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle);

        Vehicle vehicle2 = new Vehicle("v2", "B1", "BIKE", 100.0);
        vehicleRepositoryImpl.save(vehicle2);

        Vehicle vehicle3 = new Vehicle("v3", "B2", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle3);

        List<Vehicle> vehicles = vehicleRepositoryImpl.findByBranchIdAndVehicleType("B1", "CAR");
        Assertions.assertTrue(vehicles.contains(vehicle));
        Assertions.assertTrue(!vehicles.contains(vehicle2));
        Assertions.assertTrue(!vehicles.contains(vehicle3));
    }

    @Test
    @DisplayName("Test countByBranchIdAndVehicleType")
    public void countByBranchIdAndVehicleTypeTest() {

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle);

        Vehicle vehicle2 = new Vehicle("v2", "B1", "BIKE", 100.0);
        vehicleRepositoryImpl.save(vehicle2);

        Vehicle vehicle3 = new Vehicle("v3", "B1", "CAR", 100.0);
        vehicleRepositoryImpl.save(vehicle3);

        int count = vehicleRepositoryImpl.countByBranchIdAndVehicleType("B1", "CAR");
        Assertions.assertEquals(count, 2);
    }
}
