package com.mjc.school.service.impl;

import com.mjc.school.repository.CommentCommands;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import com.mjc.school.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements CommentCommandsService {
    @Autowired
    private CommentCommands repository;

    @Override
    public List<CommentDTOResponse> readAll(int limit, int offset) {
        return repository.readAll(limit, offset).stream().map(CommentMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public CommentDTOResponse readById(Long id) {
        if (repository.readById(id).isPresent()) {
            return CommentMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public CommentDTOResponse create(CommentDTORequest createRequest) {
        Comment model = CommentMapper.INSTANCE.dtoToModel(createRequest);
        News news = new News();
        news.setId(createRequest.newsId());

        model.setCreatedDate(LocalDateTime.now());
        model.setNewsId(news);

        repository.create(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public CommentDTOResponse update(Long id, CommentDTORequest updateRequest) {
        Comment model = CommentMapper.INSTANCE.dtoToModel(updateRequest);
        News news = new News();
        news.setId(updateRequest.newsId());

        model.setId(id);
        model.setLastUpdatedDate(LocalDateTime.now());
        model.setNewsId(news);

        repository.update(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<CommentDTOResponse> readCommentsByNewsId(Long id) {
        return repository.readCommentsByNewsId(id).stream().map(CommentMapper.INSTANCE::modelToDto).toList();
    }
}
