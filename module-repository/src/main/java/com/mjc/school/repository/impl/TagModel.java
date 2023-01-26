package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.BaseEntity;
import jakarta.persistence.*;
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

    public TagModel() {}

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
