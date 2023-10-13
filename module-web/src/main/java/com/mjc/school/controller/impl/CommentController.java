package com.mjc.school.controller.impl;

import com.mjc.school.controller.CommentCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController implements CommentCommandsController {

    private final CommentCommandsService service;
    @Autowired
    public CommentController(CommentCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List<CommentDTOResponse> readAll( @RequestParam(defaultValue = "10", required = false) int limit,
                                             @RequestParam(defaultValue = "0", required = false) int offset) {
        return service.readAll(limit, offset);
    }

    @Override
    @GetMapping("/{id}")
    public CommentDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentDTOResponse create(
            @RequestBody CommentDTORequest createRequest
    ) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    public CommentDTOResponse update(
            @PathVariable Long id,
            @RequestBody CommentDTORequest updateRequest
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
    @GetMapping("/newsId/{id}")
    public List<CommentDTOResponse> readCommentsByNewsId(@PathVariable Long id) {
        return service.readCommentsByNewsId(id);
    }
}
