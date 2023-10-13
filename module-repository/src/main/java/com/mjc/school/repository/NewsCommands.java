package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.News;
import com.mjc.school.repository.query.NewsSearchQueryParams;

import java.util.List;

public interface NewsCommands extends BaseRepository<News, Long> {

    List<News> readBySearchParams(NewsSearchQueryParams searchQueryParams);
}

