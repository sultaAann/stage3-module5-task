package com.mjc.school.controller;

import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.exceptions.*;

import java.util.List;

public interface BaseController<T, R, K> {

    List readAll(int limit, int offset);

    R readById(K id);

    R create(T createRequest);

    R update(K id, T updateRequest);

    void deleteById(K id) throws ResourceNotFoundException;
}
