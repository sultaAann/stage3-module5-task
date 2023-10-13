package com.mjc.school.service.impl;

import com.mjc.school.repository.TagCommands;
import com.mjc.school.repository.model.impl.Tag;
import com.mjc.school.service.TagCommandsService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements TagCommandsService {
    @Autowired
    private TagCommands repository;

    @Override
    public List<TagDTOResponse> readAll(int limit, int offset) {
        return repository.readAll(limit, offset).stream().map(TagMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public TagDTOResponse readById(Long id) {
        if (repository.readById(id).isPresent()) {
            return TagMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public TagDTOResponse create(TagDTORequest createRequest) {
        Tag model = TagMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return TagMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public TagDTOResponse update(Long id, TagDTORequest updateRequest) {
        Tag model = TagMapper.INSTANCE.dtoToModel(updateRequest);
        model.setId(id);
        repository.update(model);
        return TagMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<TagDTOResponse> readTagsByNewsId(Long id) {
        return repository.readTagsByNewsId(id).stream().map(TagMapper.INSTANCE::modelToDto).toList();
    }
}
