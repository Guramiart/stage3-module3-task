package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.utils.ScannerUtils;

import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;
import java.util.Set;

public class CreateNewsCommand implements Command {

    private static final String OPERATION = "Operation: Create news.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner sc;

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.NEWS_TITLE_RESP);
                String title = sc.nextLine();
                System.out.println(Constants.NEWS_CONTENT_RESP);
                String content = sc.nextLine();
                System.out.println(Constants.AUTHOR_ID_RESP);
                Long authorId = ScannerUtils.getNumberFromScanner("News", sc);
                System.out.println(Constants.TAG_IDS_RESP);
                Set<Long> tagIdSet = ScannerUtils.getSetNumberFromScanner("News", sc);
                NewsDtoRequest newsDtoRequest = new NewsDtoRequest
                        .NewsDtoRequestBuilder(title, content)
                        .tagId(tagIdSet)
                        .authorId(authorId)
                        .build();
                controller.create(newsDtoRequest);
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
