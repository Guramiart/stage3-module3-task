package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.impl.AuthorDtoResponse;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoResponse;
import com.mjc.school.service.dto.impl.TagDtoResponse;
import com.mjc.school.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> service;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> service) {
        this.service = service;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return service.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return service.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }

    public AuthorDtoResponse readAuthorByNewsId(Long id) {
        return ((NewsService) service).readAuthorByNewsId(id);
    }

    public Set<TagDtoResponse> readTagsByNewsId(Long id) {
        return ((NewsService) service).readTagsByNewsId(id);
    }

    public List<NewsDtoResponse> readNewsByParams(Object ... params) {
        return ((NewsService) service).readNewsByParam(params);
    }
}
