package com.mjc.school.service.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class AuthorDtoResponse implements BaseDto<Long> {

    private Long id;
    private Long name;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;

    public AuthorDtoResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDtoResponse that = (AuthorDtoResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(createTime, that.createTime)
                && Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createTime, lastUpdateTime);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, name=%s, createDate=%s, updateDate=%s]",
                getClass().getSimpleName(), id, name, createTime, lastUpdateTime);
    }
}
