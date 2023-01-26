package com.mjc.school.service.builder;

import com.mjc.school.service.Builder;
import com.mjc.school.service.dto.TagDtoRequest;

public class TagRequestBuilder implements Builder<TagDtoRequest> {

    private final TagDtoRequest tagDtoRequest;

    public TagRequestBuilder() {
        tagDtoRequest = new TagDtoRequest();
    }

    public TagRequestBuilder setId(Long id) {
        tagDtoRequest.setId(id);
        return this;
    }

    public TagRequestBuilder setName(String name) {
        tagDtoRequest.setName(name);
        return this;
    }

    @Override
    public TagDtoRequest build() {
        return tagDtoRequest;
    }
}
