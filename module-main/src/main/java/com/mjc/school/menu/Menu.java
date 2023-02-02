package com.mjc.school.menu;

import com.mjc.school.controller.Command;
import com.mjc.school.controller.factory.CommandFactory;
import com.mjc.school.controller.impl.command.ExitCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final CommandFactory commandFactory;

    @Autowired
    public Menu(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void run() {
        boolean isRun = true;
        Scanner scanner = new Scanner(System.in);
        while (isRun) {
            commandFactory.getOperationList().forEach(System.out::println);
            Command command = commandFactory.getCommand(scanner);
            command.execute();
            if(command.getClass() == ExitCommand.class) {
                isRun = false;
            }
        }
    }
}
