package com.mjc.school.repository.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;

//import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.util.Objects;
import java.util.Set;

@Entity
@Scope("prototype")
@Table(name = "tag")
public class TagModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tagModelSet")
    private Set<NewsModel> newsModelList;

    public static class TagBuilder {
        private final Long id;
        private final String name;
        private Set<NewsModel> newsModelList;

        public TagBuilder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public TagBuilder newsModelList(Set<NewsModel> list) {
            newsModelList = list;
            return this;
        }

        public TagModel build() {
            return new TagModel(this);
        }
    }

    public TagModel() {}

    public TagModel(TagBuilder builder) {
        id = builder.id;
        name = builder.name;
        newsModelList = builder.newsModelList;
    }

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

    public Set<NewsModel> getNewsModelList() {
        return newsModelList;
    }

    public void setNewsModelList(Set<NewsModel> newsModelList) {
        this.newsModelList = newsModelList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagModel tagModel = (TagModel) o;
        return Objects.equals(id, tagModel.id) && Objects.equals(name, tagModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, name=%s]", getClass().getSimpleName(), id, name);
    }

}
