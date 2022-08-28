package com.ebin.vehiclerental;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ebin.vehiclerental.commands.CommandExecutor;
import com.ebin.vehiclerental.config.ApplicationConfig;

public class VehicleRentalApplication {
    public static void main(String[] args)  {

        try {
            ApplicationConfig config = new ApplicationConfig();
            CommandExecutor executor = config.getExecutor();

            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line != null && !line.isEmpty()) {
                    // get each command with arguments, send it to executor
                    List<String> tokens = Arrays.asList(line.split(" "));
                    executor.executeCommand(tokens.get(0),tokens);
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
	}
}
