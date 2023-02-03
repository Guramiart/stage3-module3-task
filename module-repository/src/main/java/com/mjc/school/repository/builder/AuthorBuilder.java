package com.mjc.school.repository.builder;

import com.mjc.school.repository.Builder;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.List;

public class AuthorBuilder implements Builder<AuthorModel> {

    private final AuthorModel authorModel;

    public AuthorBuilder() {
        authorModel = new AuthorModel();
    }

    public AuthorBuilder setId(Long id) {
        authorModel.setId(id);
        return this;
    }

    public AuthorBuilder setName(String name) {
        authorModel.setName(name);
        return this;
    }

    public AuthorBuilder setCreateDate(LocalDateTime createDate) {
        authorModel.setCreateDate(createDate);
        return this;
    }

    public AuthorBuilder setLastUpdateDate(LocalDateTime lastUpdateDate) {
        authorModel.setLastUpdateDate(lastUpdateDate);
        return this;
    }

    public AuthorBuilder setNewsList(List<NewsModel> newsModelList) {
        authorModel.setNewsModelList(newsModelList);
        return this;
    }

    @Override
    public AuthorModel build() {
        return authorModel;
    }
}
