package com.mjc.school.repository.impl;

import com.mjc.school.repository.TagCommands;
import com.mjc.school.repository.model.impl.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements TagCommands {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Tag> readAll(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);
        CriteriaQuery<Tag> all = criteriaQuery.select(root);

        TypedQuery<Tag> allQuery = entityManager.createQuery(all)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public Optional<Tag> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Tag create(Tag model) {
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        return model;
    }

    @Override
    public Tag update(Tag model) {
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Tag tag = entityManager.find(Tag.class, id);
            entityManager.remove(tag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        Tag model = entityManager.find(Tag.class, id);
        return entityManager.contains(model);
    }


    //Additional Commands
    @Override
    public List<Tag> readTagsByNewsId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tags = criteriaQuery.from(Tag.class);
        Join<Tag, Tag> newsJoin = tags.join("news", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("tag_id"), id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
