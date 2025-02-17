package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.ScannerUtils;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class DeleteTagCommand implements Command {

    private static final String OPERATION = "Operation: Delete tag.";
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> controller;
    private final Scanner sc;

    public DeleteTagCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while(!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.TAG_ID_RESP);
                Long id = ScannerUtils.getNumberFromScanner("Tag", sc);
                System.out.println(controller.deleteById(id));
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
