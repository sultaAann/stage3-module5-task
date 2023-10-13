package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.Tag;

import java.util.List;

public interface TagCommands extends BaseRepository<Tag, Long> {
    List<Tag> readTagsByNewsId(Long id);
}
