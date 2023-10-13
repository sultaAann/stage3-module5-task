package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<News> news;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
