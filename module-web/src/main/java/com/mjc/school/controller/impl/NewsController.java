package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;
    private final BaseService<TagDtoRequest, TagDtoResponse, Long> tagService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService,
                          BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService,
                          BaseService<TagDtoRequest, TagDtoResponse, Long> tagService) {
        this.newsService = newsService;
        this.authorService = authorService;
        this.tagService = tagService;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }

    public AuthorDtoResponse readAuthorByNewsId(Long id) {
        NewsDtoResponse newsDtoResponse = newsService.readById(id);
        return authorService.readById(newsDtoResponse.getAuthorId());
    }

    public Set<TagDtoResponse> readTagsByNewsId(Long id) {
        NewsDtoResponse newsDtoResponse = newsService.readById(id);
        return newsDtoResponse.getTagId()
                .stream()
                .map(tagService::readById)
                .collect(Collectors.toSet());
    }

    public List<NewsDtoResponse> readNewsByParams(Set<String> names, Set<Long> ids,
                                                  String author, String title, String content) {
        return readAll()
                .stream()
                .filter(getPredicate(names, ids, author, title, content))
                .toList();
    }

    private Predicate<NewsDtoResponse> getPredicate(Set<String> names, Set<Long> ids,
                                                    String author, String title, String content) {
        Predicate<NewsDtoResponse> predicate = newsDtoResponse -> true;
        if(names.size() != 0) {
            predicate = predicate.and(newsDtoResponse -> new HashSet<>(newsDtoResponse.getTagId()
                    .stream()
                    .map(tagService::readById)
                    .map(TagDtoResponse::getName).toList())
                    .containsAll(names));
        }
        if(ids.size() != 0) {
            predicate = predicate.and(newsDtoResponse -> new HashSet<>(newsDtoResponse.getTagId()
                    .stream()
                    .toList())
                    .containsAll(ids));
        }
        if(!author.isEmpty()) {
            predicate = predicate.and(newsDtoResponse -> authorService
                    .readById(newsDtoResponse.getAuthorId()).getName().equalsIgnoreCase(author));
        }
        if(!title.isEmpty()) {
            predicate = predicate.and(newsDtoResponse -> newsDtoResponse.getTitle().equalsIgnoreCase(title));
        }
        if(!content.isEmpty()) {
            predicate = predicate.and(newsDtoResponse -> newsDtoResponse.getContent().equalsIgnoreCase(content));
        }
        return predicate;
    }
}
