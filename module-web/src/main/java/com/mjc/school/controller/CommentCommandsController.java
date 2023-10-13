package com.mjc.school.controller;


import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;

import java.util.List;

public interface CommentCommandsController extends BaseController<CommentDTORequest, CommentDTOResponse, Long> {
    List<CommentDTOResponse> readCommentsByNewsId(Long id);
}
