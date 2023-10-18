package com.mjc.school.service.dto;

import javax.validation.constraints.Size;


public record TagDTORequest(
        @Size(min = 3, max = 15, message = "Tag name can not be less than 3 and more than 15 symbols.")
        String name
) {
}