package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public record CommentDTOResponse(
        Long id,
        String content,
        Long newsId
) {
}