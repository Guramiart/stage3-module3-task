package com.mjc.school.repository.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

//import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Scope("prototype")
@Table(name = "author")
public class AuthorModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorModel", cascade = CascadeType.REMOVE)
    private List<NewsModel> newsModelList;

    public static class AuthorBuilder {
        private final Long id;
        private final String name;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdateDate;
        private List<NewsModel> newsModelList;

        public AuthorBuilder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public AuthorBuilder createDate(LocalDateTime value) {
            createDate = value;
            return this;
        }

        public AuthorBuilder lastUpdateDate(LocalDateTime value) {
            lastUpdateDate = value;
            return this;
        }

        public AuthorBuilder newsModelList(List<NewsModel> value) {
            newsModelList = value;
            return this;
        }

        public AuthorModel build() {
            return new AuthorModel(this);
        }
    }

    private AuthorModel(AuthorBuilder builder) {
        id = builder.id;
        name = builder.name;
        createDate = builder.createDate;
        lastUpdateDate = builder.lastUpdateDate;
        newsModelList = builder.newsModelList;
    }

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
