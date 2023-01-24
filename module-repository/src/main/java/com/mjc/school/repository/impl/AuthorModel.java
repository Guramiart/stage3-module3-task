package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity()
@Component("author")
@Scope("prototype")
@Table(name = "author")
public class AuthorModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create")
    private LocalDateTime createDate;

    @Column(name = "update")
    private LocalDateTime lastUpdateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorModel")
    private List<NewsModel> newsModelList;

    public AuthorModel() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
        AuthorModel that = (AuthorModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(newsModelList, that.newsModelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, newsModelList);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, name=%s, createDate=%s, updateDate=%s]",
                getClass().getSimpleName(), id, name, createDate, lastUpdateDate);
    }
}
