package com.ebin.vehiclerental.services;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ebin.vehiclerental.entities.Vehicle;
import com.ebin.vehiclerental.exceptions.InvalidTimeSlotException;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.utils.bookingstrategy.BookingStrategy;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private VehicleAvailabilityService vehicleService;

    @Mock
    private BookingStrategy bookingStrategy;

    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    @Test
    @DisplayName("Test bookVehicle")
    public void bookVehicleTest() {

        Vehicle vehicle = new Vehicle("v1", "B1", "CAR", 50.0);
        Vehicle vehicle2 = new Vehicle("v2", "B1", "CAR", 100.0);
        Vehicle vehicle3 = new Vehicle("v3", "B2", "CAR", 150.0);

        when(vehicleService.getAvailableByBranchAndVehicleType(
                anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(vehicle, vehicle2, vehicle3));
        when(bookingStrategy.getVehicleToBook(
                anyList()))
                .thenReturn(vehicle);

        double actual = bookingServiceImpl.bookVehicle("B1", "CAR", 2, 4);
        Assertions.assertEquals(100.0, actual);
    }

    @Test
    @DisplayName("Test bookVehicle with invalid time range")
    public void bookVehicleTestInvalidRange() {

        Assertions.assertThrows(InvalidTimeSlotException.class,
                () -> {
                    bookingServiceImpl.bookVehicle("B1", "CAR", 4, 2);
                });
    }

    @Test
    @DisplayName("Test bookVehicle with invalid time range")
    public void bookVehicleTestInvalidRange2() {

        Assertions.assertThrows(InvalidTimeSlotException.class,
                () -> {
                    bookingServiceImpl.bookVehicle("B1", "CAR", 0, 2);
                });
    }

    @Test
    @DisplayName("Test bookVehicle with invalid time range")
    public void bookVehicleTestInvalidRange3() {

        Assertions.assertThrows(InvalidTimeSlotException.class,
                () -> {
                    bookingServiceImpl.bookVehicle("B1", "CAR", 23, 25);
                });
    }

    @Test
    @DisplayName("Test bookVehicle with invalid time range")
    public void bookVehicleTestInvalidRange4() {

        Assertions.assertThrows(InvalidTimeSlotException.class,
                () -> {
                    bookingServiceImpl.bookVehicle("B1", "CAR", 24, 24);
                });
    }

    @Test
    @DisplayName("Test bookVehicle with invalid time range")
    public void bookVehicleTestInvalidRange5() {

        Assertions.assertThrows(InvalidTimeSlotException.class,
                () -> {
                    bookingServiceImpl.bookVehicle("B1", "CAR", 1, 1);
                });
    }
}
