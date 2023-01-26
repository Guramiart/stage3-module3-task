package com.mjc.school.service.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TagDtoResponse implements BaseDto<Long> {

    private Long id;
    private String name;

    public TagDtoResponse() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDtoResponse that = (TagDtoResponse) o;
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
