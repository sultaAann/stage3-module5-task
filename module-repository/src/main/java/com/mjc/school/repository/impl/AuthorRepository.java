package com.mjc.school.repository.impl;


import com.mjc.school.repository.AuthorCommands;
import com.mjc.school.repository.model.impl.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorCommands {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Author> readAll(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);
        CriteriaQuery<Author> all = criteriaQuery.select(root);

        TypedQuery<Author> allQuery = entityManager.createQuery(all)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public Optional<Author> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Author create(Author model) {
        model.setCreatedDate(LocalDateTime.now());
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        return model;
    }

    @Override
    public Author update(Author model) {
        model.setLastUpdatedDate(LocalDateTime.now());
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Author author = entityManager.find(Author.class, id);
            entityManager.remove(author);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        Author author = entityManager.find(Author.class, id);
        return entityManager.contains(author);
    }

    // Additional command
    @Override
    public List<Author> readAuthorByNewsId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> tags = criteriaQuery.from(Author.class);
        Join<Author, Author> newsJoin = tags.join("news", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("author_id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
