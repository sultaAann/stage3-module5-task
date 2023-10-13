package com.mjc.school.service;


import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;

import java.util.List;

public interface CommentCommandsService extends BaseService<CommentDTORequest, CommentDTOResponse, Long>{
    List<CommentDTOResponse> readCommentsByNewsId(Long id);
}
