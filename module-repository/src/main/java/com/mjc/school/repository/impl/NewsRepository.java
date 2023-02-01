package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
}
