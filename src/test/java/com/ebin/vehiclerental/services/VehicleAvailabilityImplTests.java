package com.ebin.vehiclerental.services;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ebin.vehiclerental.entities.Booking;
import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.repositories.VehicleRepository;

@ExtendWith(MockitoExtension.class)
public class VehicleAvailabilityImplTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleAvailabilityServiceImpl vehicleAvailabilityServiceImpl;

    @Test
    @DisplayName("Test getAvailableByBranch")
    public void getAvailableByBranchTest() {

        Booking booking = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 1, 2, (double) 100);
        Booking booking2 = new Booking(UUID.randomUUID(), "v2", "CAR", "B1", 4, 7, (double) 100);
        Booking booking3 = new Booking(UUID.randomUUID(), "v2", "CAR", "B1", 8, 10, (double) 100);

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 50.0);
        Vehicle vehicle2 = new Vehicle("v2", "B1", "CAR", 100.0);
        Vehicle vehicle3 = new Vehicle("v3", "B2", "CAR", 150.0);

        when(bookingRepository.findByBranchIdAndWithinTime(anyString(), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(booking, booking2, booking3));
        when(vehicleRepository.findByBranchId(anyString()))
                .thenReturn(Arrays.asList(vehicle, vehicle2, vehicle3));

        List<Vehicle> vehicles = vehicleAvailabilityServiceImpl.getAvailableByBranch("B1", 1, 24);
        Assertions.assertEquals(1, vehicles.size());
        Assertions.assertEquals(vehicle3, vehicles.get(0));
    }


    @Test
    @DisplayName("Test getAvailableByBranchAndVehicleType")
    public void getAvailableByBranchAndVehicleTypeTest() {

        Booking booking = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 1, 2, (double) 100);
        Booking booking2 = new Booking(UUID.randomUUID(), "v2", "CAR", "B1", 4, 7, (double) 100);
        Booking booking3 = new Booking(UUID.randomUUID(), "v2", "CAR", "B1", 8, 10, (double) 100);

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 50.0);
        Vehicle vehicle2 = new Vehicle("v2", "B1", "CAR", 100.0);
        Vehicle vehicle3 = new Vehicle("v3", "B2", "CAR", 150.0);

        when(bookingRepository.findByBranchIdAndTypeAndWithinTime(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(booking, booking2, booking3));
        when(vehicleRepository.findByBranchIdAndVehicleType(anyString(), anyString()))
                .thenReturn(Arrays.asList(vehicle, vehicle2, vehicle3));

        List<Vehicle> vehicles = vehicleAvailabilityServiceImpl.getAvailableByBranchAndVehicleType("B1", "CAR", 1, 24);
        Assertions.assertEquals(1, vehicles.size());
        Assertions.assertEquals(vehicle3, vehicles.get(0));
    }
}
