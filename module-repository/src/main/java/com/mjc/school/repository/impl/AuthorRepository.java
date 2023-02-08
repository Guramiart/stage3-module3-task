package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<AuthorModel> readAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT a FROM AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(em.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        AuthorModel model = em.getReference(AuthorModel.class, entity.getId());
        model.setName(entity.getName());
        em.getTransaction().commit();
        return model;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        AuthorModel authorModel = em.find(AuthorModel.class, id);
        em.remove(em.merge(authorModel));
        em.getTransaction().commit();
        return !existById(id);
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
