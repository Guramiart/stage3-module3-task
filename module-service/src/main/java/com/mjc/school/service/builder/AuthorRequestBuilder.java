package com.mjc.school.service.builder;

import com.mjc.school.service.Builder;
import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.impl.NewsDtoRequest;

import java.util.List;

public class AuthorRequestBuilder implements Builder<AuthorDtoRequest> {

    private final AuthorDtoRequest authorDtoRequest;

    public AuthorRequestBuilder() {
        authorDtoRequest = new AuthorDtoRequest();
    }

    public AuthorRequestBuilder setId(Long id) {
        authorDtoRequest.setId(id);
        return this;
    }

    public AuthorRequestBuilder setName(String name) {
        authorDtoRequest.setName(name);
        return this;
    }

    public AuthorRequestBuilder setNewsList(List<NewsDtoRequest> newsList) {
        authorDtoRequest.setNewsModelList(newsList);
        return this;
    }

    @Override
    public AuthorDtoRequest build() {
        return authorDtoRequest;
    }
}
