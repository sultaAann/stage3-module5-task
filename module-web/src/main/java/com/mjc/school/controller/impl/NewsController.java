package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.NewsCommandsService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.query.NewsQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/news", consumes = {"text/plain", "application/*"})
public class NewsController implements NewsCommandsController {
    private final NewsCommandsService service;
    @Autowired
    public NewsController(NewsCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List<NewsDTOResponse> readAll( @RequestParam(defaultValue = "10", required = false) int limit,
                                          @RequestParam(defaultValue = "0", required = false) int offset) {
        return service.readAll(limit, offset);
    }

    @Override
    @GetMapping("/{id}")
    public NewsDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewsDTOResponse create(@RequestBody @Valid NewsDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    public NewsDTOResponse update(
            @PathVariable Long id,
            @RequestBody @Valid NewsDTORequest updateRequest
    ) {
        return service.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        if (!service.deleteById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    @Override
    @GetMapping()
    public List<NewsDTOResponse> readBySearchParams(@RequestBody NewsQueryParams queryParams) {
        return service.readBySearchParams(queryParams);
    }

}
