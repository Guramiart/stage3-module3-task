package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    @Transactional
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public TagModel update(TagModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        TagModel tagModel = entityManager.find(TagModel.class, id);
        Set<NewsModel> set = new HashSet<>(tagModel.getNewsModelList());
        for (NewsModel model : set) {
            model.removeTag(tagModel);
            entityManager.merge(model);
        }
        entityManager.remove(tagModel);
        return !existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
