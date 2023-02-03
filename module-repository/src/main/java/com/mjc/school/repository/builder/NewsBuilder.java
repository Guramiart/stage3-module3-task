package com.mjc.school.repository.builder;

import com.mjc.school.repository.Builder;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Scope("prototype")
public class NewsBuilder implements Builder<NewsModel> {

    private final NewsModel newsModel;

    public NewsBuilder() {
        this.newsModel = new NewsModel();
    }

    public NewsBuilder setId(Long id) {
        newsModel.setId(id);
        return this;
    }

    public NewsBuilder setTitle(String title) {
        newsModel.setTitle(title);
        return this;
    }

    public NewsBuilder setContent(String content) {
        newsModel.setContent(content);
        return this;
    }

    public NewsBuilder setCreateDate(LocalDateTime createDate) {
        newsModel.setCreateDate(createDate);
        return this;
    }

    public NewsBuilder setLastUpdateDate(LocalDateTime lastUpdateDate) {
        newsModel.setLastUpdateDate(lastUpdateDate);
        return this;
    }

    public NewsBuilder setAuthorModel(AuthorModel authorModel) {
        newsModel.setAuthorModel(authorModel);
        return this;
    }

    @Override
    public NewsModel build() {
        return newsModel;
    }
}
