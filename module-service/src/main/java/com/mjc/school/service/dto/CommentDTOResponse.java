package com.mjc.school.service.dto;


public record CommentDTOResponse(
        Long id,
        String content,
        Long newsId
) {
}