package com.mjc.school.controller.impl;

import com.mjc.school.controller.CommentCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting comments in the application")
public class CommentController implements CommentCommandsController {

    private final CommentCommandsService service;
    @Autowired
    public CommentController(CommentCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    @ApiOperation(value = "View all comments", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all comments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<CommentDTOResponse> readAll( @RequestParam(defaultValue = "10", required = false) int limit,
                                             @RequestParam(defaultValue = "0", required = false) int offset) {
        return service.readAll(limit, offset);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific comment with the supplied id", response = CommentDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the comment with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public CommentDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create comment", response = CommentDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created commment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentDTOResponse create(
            @RequestBody CommentDTORequest createRequest
    ) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a piece of comment information", response = CommentDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated comment"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public CommentDTOResponse update(
            @PathVariable Long id,
            @RequestBody CommentDTORequest updateRequest
    ) {
        return service.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific comments with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deletes the specific comments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        if (!service.deleteById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    @Override
    @GetMapping("/newsId/{id}")
    @ApiOperation(value = "Get comments by news id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get the comments"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    public List<CommentDTOResponse> readCommentsByNewsId(@PathVariable Long id) {
        return service.readCommentsByNewsId(id);
    }
}
