package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.Author;

import java.util.List;

public interface AuthorCommands extends BaseRepository<Author, Long> {
    List<Author> readAuthorByNewsId(Long id);

}
