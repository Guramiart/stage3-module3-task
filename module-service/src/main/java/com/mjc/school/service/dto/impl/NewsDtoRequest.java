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
    private Set<Long> tagId;
    private Long authorId;

    public NewsDtoRequest() {}

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

    public Set<Long> getTagId() {
        return tagId;
    }

    public void setTagId(Set<Long> tagId) {
        this.tagId = tagId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
