package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;

import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class CreateAuthorCommand implements Command {

    private static final String OPERATION = "Operation: Create author.";
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;
    private final Scanner sc;

    public CreateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.AUTHOR_NAME_RESP);
                String name = sc.nextLine();
                AuthorDtoRequest authorDtoRequest = new AuthorDtoRequest
                        .AuthorDtoRequestBuilder(name)
                        .build();
                controller.create(authorDtoRequest);
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
