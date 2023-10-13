package com.mjc.school.controller;



import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.query.NewsQueryParams;

import java.util.List;

public interface NewsCommandsController extends BaseController<NewsDTORequest, NewsDTOResponse, Long> {
    List<NewsDTOResponse> readBySearchParams(NewsQueryParams queryParams);

}
