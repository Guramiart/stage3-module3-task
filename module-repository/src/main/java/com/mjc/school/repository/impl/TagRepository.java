package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.of(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<TagModel> tagModel = readById(id);
        tagModel.ifPresent(model -> entityManager.remove(model));
        return existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
