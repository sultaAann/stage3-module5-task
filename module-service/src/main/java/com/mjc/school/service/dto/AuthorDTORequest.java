package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
@Scope("prototype")
public record AuthorDTORequest(
        @Size(min = 3, max = 15, message = "Author name can not be less than 3 and more than 15 symbols.")
        String name
) {
}