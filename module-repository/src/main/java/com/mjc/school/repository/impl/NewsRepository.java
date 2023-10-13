package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsCommands;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.repository.model.impl.Tag;
import com.mjc.school.repository.query.NewsSearchQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements NewsCommands {


    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Override
    public List<News> readAll(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        CriteriaQuery<News> all = criteriaQuery.select(root);

        TypedQuery<News> allQuery = entityManager.createQuery(all)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    @Override
    public News create(News model) {
        entityManager.getTransaction().begin();
        model.setCreatedDate(LocalDateTime.now());
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        return model;
    }

    @Override
    public News update(News model) {
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            News news = entityManager.find(News.class, id);
            entityManager.remove(news);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        News model = entityManager.find(News.class, id);
        return entityManager.contains(model);
    }

    @Override
    public List<News> readBySearchParams(NewsSearchQueryParams searchQueryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> query = criteriaBuilder.createQuery(News.class);
        Root<News> root = query.from(News.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchQueryParams.tagNames() != null || searchQueryParams.tagIds() != null) {
            Join<Tag, News> newsTags = root.join("tags");
            if (searchQueryParams.tagNames() != null) {
                predicates.add(newsTags.get("name").in(searchQueryParams.tagNames()));
            }
            if (searchQueryParams.tagIds() != null) {
                predicates.add(newsTags.get("id").in(searchQueryParams.tagIds()));
            }
        }

        if (searchQueryParams.authorName() != null) {
            Join<Author, News> newsAuthor = root.join("author");
            predicates.add(criteriaBuilder.equal(newsAuthor.get("name"), searchQueryParams.authorName()));
        }

        if (searchQueryParams.content() != null) {
            predicates.add(criteriaBuilder.like(root.get("content"), "%" + searchQueryParams.content() + "%"));
        }

        if (searchQueryParams.title() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQueryParams.title() + "%"));
        }

        query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}
