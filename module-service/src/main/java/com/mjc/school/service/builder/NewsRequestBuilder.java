package com.mjc.school.service.builder;

import com.mjc.school.service.Builder;
import com.mjc.school.service.dto.impl.NewsDtoRequest;

import java.util.Set;

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

    public NewsRequestBuilder setTagId(Set<Long> tagId) {
        newsDtoRequest.setTagId(tagId);
        return this;
    }

    public NewsRequestBuilder setAuthorId(Long authorId) {
        newsDtoRequest.setAuthorId(authorId);
        return this;
    }

    @Override
    public NewsDtoRequest build() {
        return newsDtoRequest;
    }
}
