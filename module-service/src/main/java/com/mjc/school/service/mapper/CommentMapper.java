package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(target = "newsId", source = "newsId.id")
    CommentDTOResponse modelToDto(Comment model);

    @Mapping(target = "newsId.id", source = "newsId")
    Comment dtoToModel(CommentDTORequest modelDTO);
}
