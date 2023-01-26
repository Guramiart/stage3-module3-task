package com.mjc.school.service.dto;

import com.mjc.school.repository.impl.NewsModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class AuthorDtoRequest implements BaseDto<Long> {

    private Long id;
    private String name;

    private List<NewsModel> newsModelList;

    public AuthorDtoRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsModel> getNewsModelList() {
        return newsModelList;
    }

    public void setNewsModelList(List<NewsModel> newsModelList) {
        this.newsModelList = newsModelList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDtoRequest that = (AuthorDtoRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, name=%s]", getClass().getSimpleName(), id, name);
    }
}
