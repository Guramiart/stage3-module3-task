package com.mjc.school.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Entity
@Component
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
    private Set<NewsModel> newsModelSet;

    public static class TagBuilder {
        private final Long id;
        private final String name;
        private Set<NewsModel> newsModelSet;

        public TagBuilder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public TagBuilder newsModelSet(Set<NewsModel> set) {
            newsModelSet = set;
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
        newsModelSet = builder.newsModelSet;
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

    public Set<NewsModel> getNewsModelSet() {
        return newsModelSet;
    }

    public void setNewsModelSet(Set<NewsModel> newsModelSet) {
        this.newsModelSet = newsModelSet;
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
