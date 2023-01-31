package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.Command;

public class ExitCommand implements Command {

    private static final String OPERATION = "Exit command";
    @Override
    public void execute() {
        System.out.println(OPERATION);
    }
}
