package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;

import java.util.Scanner;

public class ReadAllAuthorsCommand implements Command {

    private static final String OPERATION = "Operation: Get all authors.";
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;

    public ReadAllAuthorsCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println(OPERATION);
        controller.readAll().forEach(System.out::println);
    }
}
