package com.mjc.school.service.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record CommentDTORequest(
        @Size(min = 5, max = 255, message = "Comment can not be less than 5 and more than 255 symbols.")
        String content,
        @Positive
        Long newsId
) {
}