package com.mjc.school.repository;


import com.mjc.school.repository.model.impl.Comment;

import java.util.List;

public interface CommentCommands extends BaseRepository<Comment, Long> {
    List<Comment> readCommentsByNewsId(Long id);
}
