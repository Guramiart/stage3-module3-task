package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<TagModel> readAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT t FROM TagModel t", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(em.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TagModel tagModel = em.getReference(TagModel.class, entity.getId());
        tagModel.setName(entity.getName());
        em.getTransaction().commit();
        return tagModel;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TagModel tagModel = em.find(TagModel.class, id);
        Set<NewsModel> set = new HashSet<>(tagModel.getNewsModelList());
        for (NewsModel model : set) {
            model.removeTag(tagModel);
            em.merge(model);
        }
        em.remove(em.merge(tagModel));
        em.getTransaction().commit();
        return !existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
