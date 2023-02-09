package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;

import com.mjc.school.repository.model.NewsModel;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<NewsModel> readAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT n FROM NewsModel n", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(em.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        NewsModel model = em.getReference(NewsModel.class, entity.getId());
        model.setTitle(entity.getTitle());
        model.setContent(entity.getContent());
        model.setAuthorModel(entity.getAuthorModel());
        em.getTransaction().commit();
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        NewsModel newsModel = em.find(NewsModel.class, id);
        em.remove(em.merge(newsModel));
        em.getTransaction().commit();
        return !existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }

}
