package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.ScannerUtils;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;

public class DeleteNewsCommand implements Command {

    private static final String OPERATION = "Operation: Delete news.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner sc;

    public DeleteNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while(!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.NEWS_ID_RESP);
                Long newsId = ScannerUtils.getNumberFromScanner("News", sc);
                System.out.println(controller.deleteById(newsId));
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
