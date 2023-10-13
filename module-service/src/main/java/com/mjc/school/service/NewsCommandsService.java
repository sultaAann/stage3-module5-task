package com.mjc.school.service;


import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.query.NewsQueryParams;

import java.util.List;

public interface NewsCommandsService extends BaseService<NewsDTORequest, NewsDTOResponse, Long>{
    List<NewsDTOResponse> readBySearchParams(NewsQueryParams QueryParams);
}

