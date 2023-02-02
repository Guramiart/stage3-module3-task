package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;

public class ErrorCommand implements Command {

    @Override
    public void execute() {
        System.out.println(Constants.ERROR_COMMAND);
    }

}
