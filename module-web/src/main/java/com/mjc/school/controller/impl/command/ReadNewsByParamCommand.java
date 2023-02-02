package com.mjc.school.controller.impl.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.utils.ScannerUtils;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.exceptions.ServiceException;

import java.util.Scanner;
import java.util.Set;

public class ReadNewsByParamCommand implements Command {

    private static final String OPERATION = "Operation: Get news by provided params.";
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;
    private final Scanner sc;

    public ReadNewsByParamCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller, Scanner sc) {
        this.controller = controller;
        this.sc = sc;
    }

    @Override
    public void execute() {
        boolean isValid = false;
        while(!isValid) {
            try {
                System.out.println(OPERATION);
                System.out.println(Constants.TAG_NAMES_RESP);
                Set<String> names = ScannerUtils.getSetStringFromScanner("Tag", sc);
                System.out.println(Constants.TAG_IDS_RESP);
                Set<Long> tagIds = ScannerUtils.getSetNumberFromScanner("Tag", sc);
                System.out.println(Constants.AUTHOR_NAME_RESP);
                String author = sc.nextLine();
                System.out.println(Constants.NEWS_TITLE_RESP);
                String title = sc.nextLine();
                System.out.println(Constants.NEWS_CONTENT_RESP);
                String content = sc.nextLine();
                ((NewsController) controller).readNewsByParams(names, tagIds,  author, title, content)
                        .forEach(System.out::println);
                isValid = true;
            } catch (ServiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
