package com.mjc.school.service.builder;

import com.mjc.school.repository.impl.NewsModel;
import com.mjc.school.service.Builder;
import com.mjc.school.service.dto.AuthorDtoRequest;

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

    public AuthorRequestBuilder setNewsList(List<NewsModel> newsModelList) {
        authorDtoRequest.setNewsModelList(newsModelList);
        return this;
    }

    @Override
    public AuthorDtoRequest build() {
        return authorDtoRequest;
    }
}
