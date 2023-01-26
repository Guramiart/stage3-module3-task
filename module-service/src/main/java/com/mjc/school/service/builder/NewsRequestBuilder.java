package com.mjc.school.service.builder;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.Builder;
import com.mjc.school.service.dto.NewsDtoRequest;

public class NewsRequestBuilder implements Builder<NewsDtoRequest> {

    private final NewsDtoRequest newsDtoRequest;

    public NewsRequestBuilder() {
        newsDtoRequest = new NewsDtoRequest();
    }

    public NewsRequestBuilder setId(Long id) {
        newsDtoRequest.setId(id);
        return this;
    }

    public NewsRequestBuilder setTitle(String title) {
        newsDtoRequest.setTitle(title);
        return this;
    }

    public NewsRequestBuilder setContent(String content) {
        newsDtoRequest.setContent(content);
        return this;
    }

    public NewsRequestBuilder setAuthor(AuthorModel author) {
        newsDtoRequest.setAuthor(author);
        return this;
    }

    @Override
    public NewsDtoRequest build() {
        return newsDtoRequest;
    }
}
