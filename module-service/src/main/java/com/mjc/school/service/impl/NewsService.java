package com.mjc.school.service.impl;

import com.mjc.school.repository.NewsCommands;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.repository.query.NewsSearchQueryParams;
import com.mjc.school.service.NewsCommandsService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService implements NewsCommandsService {
    @Autowired
    private NewsCommands repository;

    @Override
    public List<NewsDTOResponse> readAll(int limit, int offset) {
        return repository.readAll(limit, offset).stream().map(NewsMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public NewsDTOResponse readById(Long id) {
        if (repository.readById(id).isPresent()) {
            return NewsMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest createRequest) {
        News model = NewsMapper.INSTANCE.dtoToModel(createRequest);

        Author author = new Author();
        author.setId(createRequest.authorId());
        model.setAuthorId(author);
        model.setCreatedDate(LocalDateTime.now());

        repository.create(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public NewsDTOResponse update(Long id, NewsDTORequest updateRequest) {
        News model = NewsMapper.INSTANCE.dtoToModel(updateRequest);
        model.setId(id);

        Author author = new Author();
        author.setId(updateRequest.authorId());
        model.setAuthorId(author);
        model.setLastUpdatedDate(LocalDateTime.now());

        repository.update(model);
        return NewsMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }


    @Override
    public List<NewsDTOResponse> readBySearchParams(NewsQueryParams queryParams) {
        NewsSearchQueryParams searchQueryParams = new NewsSearchQueryParams(
                queryParams.tagNames(),
                queryParams.tagIds(),
                queryParams.authorName(),
                queryParams.title(),
                queryParams.content()
        );
        return repository.readBySearchParams(searchQueryParams).stream().map(NewsMapper.INSTANCE::modelToDto).toList();
    }
}
