package com.mjc.school.service.dto.impl;

import com.mjc.school.service.dto.BaseDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class AuthorDtoRequest implements BaseDto<Long> {

    private Long id;
    private String name;

    private List<NewsDtoRequest> newsList;

    public static class AuthorDtoRequestBuilder {
        private Long id;
        private final String name;
        private List<NewsDtoRequest> newsList;

        public AuthorDtoRequestBuilder(String name) {
            this.name = name;
        }

        public AuthorDtoRequestBuilder id(Long value){
            id = value;
            return this;
        }

        public AuthorDtoRequestBuilder newsList(List<NewsDtoRequest> list) {
            newsList = list;
            return this;
        }

        public AuthorDtoRequest build() {
            return new AuthorDtoRequest(this);
        }
    }

    public AuthorDtoRequest() {}

    public AuthorDtoRequest(AuthorDtoRequestBuilder builder) {
        id = builder.id;
        name = builder.name;
        newsList = builder.newsList;
    }

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

    public List<NewsDtoRequest> getNewsList() {
        return newsList;
    }

    public void setNewsModelList(List<NewsDtoRequest> newsList) {
        this.newsList = newsList;
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
