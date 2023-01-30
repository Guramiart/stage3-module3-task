package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoResponse;

import java.util.Scanner;

public class ReadAllTagsCommand implements Command {

    private static final String OPERATION = "Operation: Get all tags.";
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> controller;

    public ReadAllTagsCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println(OPERATION);
        controller.readAll().forEach(System.out::println);
    }
}
