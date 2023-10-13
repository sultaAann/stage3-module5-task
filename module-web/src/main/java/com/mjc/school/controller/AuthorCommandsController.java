package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;

import java.util.List;

public interface AuthorCommandsController extends BaseController<AuthorDTORequest, AuthorDTOResponse, Long> {
    List<AuthorDTOResponse> readAuthorByNewsId(Long id);
}
