package com.mjc.school.service.dto.impl;

import com.mjc.school.service.dto.BaseDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@Scope("prototype")
public class NewsDtoRequest implements BaseDto<Long> {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Set<Long> tagId;

    public static class NewsDtoRequestBuilder {
        private Long id;
        private final String title;
        private final String content;
        private Long authorId;
        private Set<Long> tagId;

        public NewsDtoRequestBuilder(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public NewsDtoRequestBuilder id(Long value) {
            id = value;
            return this;
        }

        public NewsDtoRequestBuilder authorId(Long value) {
            authorId = value;
            return this;
        }

        public NewsDtoRequestBuilder tagId(Set<Long> set) {
            tagId = set;
            return this;
        }

        public NewsDtoRequest build() {
            return new NewsDtoRequest(this);
        }
    }

    public NewsDtoRequest() {}

    public NewsDtoRequest(NewsDtoRequestBuilder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
        authorId = builder.authorId;
        tagId = builder.tagId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<Long> getTagId() {
        return tagId;
    }

    public void setTagId(Set<Long> tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDtoRequest that = (NewsDtoRequest) o;
        return Objects.equals(id, that.id)
                && Objects.equals(title, that.title)
                && Objects.equals(content, that.content)
                && Objects.equals(tagId, that.tagId)
                && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, tagId, authorId);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, title=%s, content=%s, authorId=%d]",
                getClass().getSimpleName(), id, title, content, authorId);
    }
}
