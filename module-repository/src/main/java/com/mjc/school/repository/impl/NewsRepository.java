package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel model = entityManager.getReference(NewsModel.class, entity.getId());
        model.setTitle(entity.getTitle());
        model.setContent(entity.getContent());
        model.setAuthorModel(entity.getAuthorModel());
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> authorModel = readById(id);
        authorModel.ifPresent(model -> entityManager.remove(model));
        return !existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }

    public Set<TagModel> getTagsByNewsId(Long id) {
        TypedQuery<NewsModel> query = entityManager.createQuery(
                "SELECT n FROM NewsModel n INNER JOIN FETCH n.tagModelSet t WHERE n.id = :id", NewsModel.class);
        query.setParameter("id", id);
        NewsModel model = query.getResultList().get(0);
        return model.getTagModelSet();
    }

    public List<NewsModel> readNewsByParam(Set<String> names, Set<Long> ids,
                                           String author, String title, String content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);
        Join<NewsModel, AuthorModel> authorModelJoin = root.join("authorModel");
        root.fetch("authorModel");

        List<NewsModel> results = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();

        if(names.size() != 0) {
            for(String name : names) {
                Join<NewsModel, TagModel> tagModelJoin = root.join("tagModelSet");
                root.fetch("tagModelSet");
                predicates.add(criteriaBuilder.equal(tagModelJoin.get("name"), name));
            }
        }
        if(ids.size() != 0) {
            for(Long id : ids) {
                Join<NewsModel, TagModel> tagModelJoin = root.join("tagModelSet");
                root.fetch("tagModelSet");
                predicates.add(criteriaBuilder.equal(tagModelJoin.get("id"), id));
            }
        }
        if(!("".equals(author))) {
            predicates.add(criteriaBuilder.equal(authorModelJoin.get("name"), author));
        }
        if(!("".equals(title))) {
            predicates.add(criteriaBuilder.equal(root.get("title"), title));
        }
        if(!("".equals(content))) {
            predicates.add(criteriaBuilder.equal(root.get("content"), content));
        }
        if(predicates.size() != 0) {
            Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            query.select(root).distinct(true).where(and);
            results = entityManager.createQuery(query).getResultList();
        }
        return results;
    }
}
