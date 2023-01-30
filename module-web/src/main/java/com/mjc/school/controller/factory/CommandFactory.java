package com.mjc.school.controller.factory;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.Command;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.impl.TagController;
import com.mjc.school.controller.impl.command.*;
import com.mjc.school.service.dto.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandFactory {

    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;

    @Autowired
    public CommandFactory(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                          BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController,
                          BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.tagController = tagController;
    }

    private enum Operation {

        CREATE_NEWS(1, "Create news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new CreateNewsCommand((NewsController) controller, sc);
            }
        },
        CREATE_AUTHOR(2, "Create author") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new CreateAuthorCommand((AuthorController) controller, sc);
            }
        },
        CREATE_TAG(3, "Create tag") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new CreateTagCommand((TagController) controller, sc);
            }
        },
        GET_NEWS(4, "Get all news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAllNewsCommand((NewsController) controller, sc);
            }
        },
        GET_AUTHORS(5, "Get all authors") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAllAuthorsCommand((AuthorController) controller, sc);
            }
        },
        GET_TAGS(6, "Get all tags") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAllTagsCommand((TagController) controller, sc);
            }
        },
        GET_NEWS_BY_ID(7, "Get news by id") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadNewsByIdCommand((NewsController) controller, sc);
            }
        },
        GET_AUTHOR_BY_ID(8, "Get author by id") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadAuthorByIdCommand((AuthorController) controller, sc);
            }
        },
        GET_TAG_BY_ID(9, "Get tag by id") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new ReadTagByIdCommand((TagController) controller, sc);
            }
        },
        UPDATE_NEWS(10, "Update news") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new UpdateNewsCommand((NewsController) controller, sc);
            }
        },
        UPDATE_AUTHOR(11, "Update author") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return new UpdateAuthorCommand((AuthorController) controller, sc);
            }
        },
        UPDATE_TAG(12, "Update tag") {
            @Override
            <T> Command getCommand(Scanner sc, T controller) {
                return null;
            }
        };

        private final int id;
        private final String name;

        Operation(int id, String name) {
            this.id = id;
            this.name = name;
        }

        abstract <T> Command getCommand(Scanner sc, T controller);

        @Override
        public String toString() {
            return id + " - " + name;
        }
    }

    public Command getCommand(Scanner sc) {
        Command command = new ErrorCommand();
        int id = Integer.parseInt(sc.nextLine());
        if(id >= 0 && id <= 10) {
            Operation operation = Arrays.stream(Operation.values())
                    .filter(e -> id == e.id)
                    .findFirst()
                    .get();
            if(id % 2 != 0 && id % 3 != 0) {
                command = operation.getCommand(sc, newsController);
            } else if (id % 3 != 0){
                command = operation.getCommand(sc, authorController);
            } else {
                command = operation.getCommand(sc, tagController);
            }
        }
        return command;
    }

    public List<String> getOperationList() {
        return Arrays.stream(Operation.values()).map(Operation::toString).toList();
    }
}
