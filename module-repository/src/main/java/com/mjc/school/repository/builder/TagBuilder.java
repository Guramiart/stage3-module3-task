package com.mjc.school.repository.builder;

import com.mjc.school.repository.Builder;
import com.mjc.school.repository.impl.TagModel;

public class TagBuilder implements Builder<TagModel> {

    private final TagModel tagModel;

    public TagBuilder() {
        tagModel = new TagModel();
    }

    public TagBuilder setId(Long id) {
        tagModel.setId(id);
        return this;
    }

    public TagBuilder setName(String name) {
        tagModel.setName(name);
        return this;
    }

    @Override
    public TagModel build() {
        return tagModel;
    }

}
