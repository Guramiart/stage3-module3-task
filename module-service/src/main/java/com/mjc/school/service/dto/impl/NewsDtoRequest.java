package com.mjc.school.service.dto.impl;

import com.mjc.school.repository.impl.AuthorModel;
import com.mjc.school.service.dto.BaseDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class NewsDtoRequest implements BaseDto<Long> {

    private Long id;
    private String title;
    private String content;
    private AuthorModel author;

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

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDtoRequest that = (NewsDtoRequest) o;
        return Objects.equals(id, that.id)
                && Objects.equals(title, that.title)
                && Objects.equals(content, that.content)
                && Objects.equals(author.getId(), that.author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author.getId());
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, title=%s, content=%s, authorId=%d]",
                getClass().getSimpleName(), id, title, content, author.getId());
    }
}
