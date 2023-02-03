package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
@Transactional
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

    public List<NewsModel> readNewsByParam(Object ... params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> query = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = query.from(NewsModel.class);
        root.join("authorModel", JoinType.INNER);
        root.fetch("authorModel", JoinType.INNER);
        Join<NewsModel, TagModel> newsModelTagModelJoin = root.join("tagModelSet", JoinType.INNER);
        root.fetch("tagModelSet", JoinType.INNER);

        List<NewsModel> results = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        Set<?> tagNames = (Set<?>) params[0];
        Set<?> tagIds = (Set<?>) params[1];
        if(tagNames.size() != 0) {
            predicates.add(criteriaBuilder.and(newsModelTagModelJoin.get("name").in(List.of(tagNames))));
        }
        if(tagIds.size() != 0) {
            predicates.add(criteriaBuilder.and(newsModelTagModelJoin.get("id").in(List.of(tagIds))));
        }
        if(!("".equals(params[2]))) {
            predicates.add(criteriaBuilder.equal(root.get("authorModel").get("name"), params[2]));
        }
        if(!("".equals(params[3]))) {
            predicates.add(criteriaBuilder.equal(root.get("title"), params[3]));
        }
        if(!("".equals(params[4]))) {
            predicates.add(criteriaBuilder.equal(root.get("content"), params[4]));
        }
        if(predicates.size() != 0) {
            query.select(root).where(predicates.toArray(new Predicate[]{}));
            results = entityManager.createQuery(query).getResultList();
        }
        return results;
    }
}
