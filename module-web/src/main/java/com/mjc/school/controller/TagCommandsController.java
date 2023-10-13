package com.mjc.school.controller;

import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;

import java.util.List;

public interface TagCommandsController extends BaseController<TagDTORequest, TagDTOResponse, Long> {
    List<TagDTOResponse> readTagsByNewsId(Long id);
}
