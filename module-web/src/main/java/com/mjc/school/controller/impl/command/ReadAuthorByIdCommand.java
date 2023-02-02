package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.ScannerUtils;
import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class ReadAuthorByIdCommand implements Command {

    private static final String OPERATION = "Operation: Read author by id.";
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;
    private final Scanner sc;

    public ReadAuthorByIdCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while(!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.AUTHOR_ID_RESP);
                Long id = ScannerUtils.getNumberFromScanner("Author", sc);
                System.out.println(controller.readById(id));
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
