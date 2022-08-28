package com.ebin.vehiclerental.repositories;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ebin.vehiclerental.entities.Booking;

public class BookingRepositoryImplTests {

    private BookingRepositoryImpl bookingRepositoryImpl;

    @BeforeEach
    void setup() {
        this.bookingRepositoryImpl = new BookingRepositoryImpl();
    }

    @Test
    @DisplayName("Test saving a booking")
    public void saveBranch() {

        Booking booking = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 1, 2, (double) 100);
        Assertions.assertNotNull(bookingRepositoryImpl.save(booking));
    }

    @Test
    @DisplayName("Test findByBranchIdAndWithinTime")
    public void findByBranchIdAndWithinTimeTest() {

        Booking booking = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 1, 2, (double) 100);
        bookingRepositoryImpl.save(booking);

        Booking booking2 = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 4, 7, (double) 100);
        bookingRepositoryImpl.save(booking2);

        Booking booking3 = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 8, 10, (double) 100);
        bookingRepositoryImpl.save(booking3);

        List<Booking> bookings = bookingRepositoryImpl.findByBranchIdAndWithinTime("B1", 1, 5);
        Assertions.assertTrue(bookings.contains(booking));
        Assertions.assertTrue(bookings.contains(booking2));
        Assertions.assertTrue(!bookings.contains(booking3));
    }

    @Test
    @DisplayName("Test findByBranchIdAndTypeAndWithinTime")
    public void findByBranchIdAndTypeAndWithinTimeTest() {

        Booking booking = new Booking(UUID.randomUUID(), "v1", "CAR", "B1", 1, 2, (double) 100);
        bookingRepositoryImpl.save(booking);

        Booking booking2 = new Booking(UUID.randomUUID(), "v1", "BIKE", "B1", 4, 7, (double) 100);
        bookingRepositoryImpl.save(booking2);

        Booking booking3 = new Booking(UUID.randomUUID(), "v1", "TRUCK", "B1", 8, 10, (double) 100);
        bookingRepositoryImpl.save(booking3);

        List<Booking> bookings = bookingRepositoryImpl.findByBranchIdAndTypeAndWithinTime("B1", "CAR", 1, 5);
        Assertions.assertTrue(bookings.contains(booking));
        Assertions.assertTrue(!bookings.contains(booking2));
        Assertions.assertTrue(!bookings.contains(booking3));
    }
}
