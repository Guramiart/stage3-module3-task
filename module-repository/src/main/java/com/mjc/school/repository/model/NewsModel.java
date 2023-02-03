package com.mjc.school.repository.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Component
@Scope("prototype")
@Table(name = "news")
public class NewsModel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "content", unique = true, nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagModel> tagModelSet;

    private static class NewsBuilder {

        private final Long id;
        private final String title;
        private final String content;
        private LocalDateTime createDate;
        private LocalDateTime lastUpdateDate;
        private AuthorModel authorModel;
        private Set<TagModel> tagModelSet;

        public NewsBuilder(Long id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public NewsBuilder createDate(LocalDateTime value) {
            createDate = value;
            return this;
        }

        public NewsBuilder lastUpdateDate(LocalDateTime value) {
            lastUpdateDate = value;
            return this;
        }

        public NewsBuilder authorModel(AuthorModel value) {
            authorModel = value;
            return this;
        }

        public NewsBuilder tagModelSet(Set<TagModel> set) {
            tagModelSet = set;
            return this;
        }

        public NewsModel build() {
            return new NewsModel(this);
        }

    }

    public NewsModel() {}

    public NewsModel(NewsBuilder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
        createDate = builder.createDate;
        lastUpdateDate = builder.lastUpdateDate;
        authorModel = builder.authorModel;
        tagModelSet = builder.tagModelSet;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public Set<TagModel> getTagModelSet() {
        return tagModelSet;
    }

    public void setTagModelSet(Set<TagModel> tagModelSet) {
        this.tagModelSet = tagModelSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return Objects.equals(id, newsModel.id)
                && Objects.equals(title, newsModel.title)
                && Objects.equals(content, newsModel.content)
                && Objects.equals(createDate, newsModel.createDate)
                && Objects.equals(lastUpdateDate, newsModel.lastUpdateDate)
                && Objects.equals(authorModel, newsModel.authorModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, authorModel);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, title=%s, content=%s, createDate=%s, updateDate=%s, authorId=%d]",
                getClass().getSimpleName(), id, title, content, createDate, lastUpdateDate, authorModel.getId());
    }
}
