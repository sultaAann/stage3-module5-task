package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;

import java.util.List;

public interface AuthorCommandsService extends BaseService<AuthorDTORequest, AuthorDTOResponse, Long> {
    List<AuthorDTOResponse> readAuthorByNewsId(Long id);
}
