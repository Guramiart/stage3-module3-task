package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class CreateTagCommand implements Command {

    private static final String OPERATION = "Operation: Create tag.";
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> controller;
    private final Scanner sc;

    public CreateTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }
    @Override
    public void execute() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.TAG_NAME_RESP);
                String name = sc.nextLine();
                TagDtoRequest tagDtoRequest = new TagDtoRequest
                        .TagDtoRequestBuilder(name)
                        .build();
                controller.create(tagDtoRequest);
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
