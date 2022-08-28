package com.ebin.vehiclerental.config;

import com.ebin.vehiclerental.commands.AddBranchCommand;
import com.ebin.vehiclerental.commands.AddVehicleCommand;
import com.ebin.vehiclerental.commands.BookCommand;
import com.ebin.vehiclerental.commands.CommandExecutor;
import com.ebin.vehiclerental.commands.DisplayVehiclesCommand;
import com.ebin.vehiclerental.repositories.BookingRepository;
import com.ebin.vehiclerental.repositories.BookingRepositoryImpl;
import com.ebin.vehiclerental.repositories.BranchRepository;
import com.ebin.vehiclerental.repositories.BranchRepositoryImpl;
import com.ebin.vehiclerental.repositories.VehicleRepository;
import com.ebin.vehiclerental.repositories.VehicleRepositoryImpl;
import com.ebin.vehiclerental.services.BookingService;
import com.ebin.vehiclerental.services.BookingServiceImpl;
import com.ebin.vehiclerental.services.BranchService;
import com.ebin.vehiclerental.services.BranchServiceImpl;
import com.ebin.vehiclerental.services.VehicleService;
import com.ebin.vehiclerental.services.VehicleServiceImpl;
import com.ebin.vehiclerental.utils.bookingstrategy.BookingStrategy;
import com.ebin.vehiclerental.utils.bookingstrategy.LowestPriceBooking;

import lombok.Getter;

public class ApplicationConfig {

    private final BranchRepository branchRepository = new BranchRepositoryImpl();
    private final VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    private final BookingRepository bookingRepository = new BookingRepositoryImpl();

    private final BranchService branchService = new BranchServiceImpl(branchRepository);
    private final VehicleService vehicleService = new VehicleServiceImpl(branchService, bookingRepository, vehicleRepository);
    private final BookingStrategy bookingStrategy = new LowestPriceBooking();
    private final BookingService bookingService = new BookingServiceImpl(bookingRepository, vehicleService, bookingStrategy);

    private final AddBranchCommand addBranchCommand = new AddBranchCommand(branchService);
    private final AddVehicleCommand addVehicleCommand = new AddVehicleCommand(vehicleService);
    private final BookCommand bookCommand = new BookCommand(bookingService);
    private final DisplayVehiclesCommand displayVehiclesCommand = new DisplayVehiclesCommand(vehicleService);

    @Getter
    private final CommandExecutor executor = new CommandExecutor();

    public ApplicationConfig() {

        executor.addCommand("ADD_BRANCH", addBranchCommand);
        executor.addCommand("ADD_VEHICLE", addVehicleCommand);
        executor.addCommand("BOOK", bookCommand);
        executor.addCommand("DISPLAY_VEHICLES", displayVehiclesCommand);
    }
}

