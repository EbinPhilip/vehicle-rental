package com.ebin.vehiclerental.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandExecutor {

    private static final Map<String, Command> commandMap = new HashMap<>();

    public void addCommand(String name, Command command){
        commandMap.put(name,command);
    }

    public void executeCommand(String commandName, List<String> tokens) {
        Command command = commandMap.get(commandName);
        if(command == null){
           System.out.println("Invalid API");
        } else {
            command.run(tokens);
        }
    }
}
