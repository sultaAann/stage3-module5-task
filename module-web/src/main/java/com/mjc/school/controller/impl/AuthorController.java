package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.AuthorCommandsService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting author in the application")
public class AuthorController implements AuthorCommandsController {
    private final AuthorCommandsService service;
    @Autowired
    public AuthorController(AuthorCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    @ApiOperation(value = "View all authors", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all authors"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<AuthorDTOResponse> readAll( @RequestParam(defaultValue = "10", required = false) int limit,
                                            @RequestParam(defaultValue = "0", required = false) int offset) {
        return service.readAll(limit, offset);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific authors with the supplied id", response = AuthorDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the authors with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public AuthorDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create a piece of authors", response = AuthorDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTOResponse create(
            @RequestBody @Valid AuthorDTORequest createRequest
    ) {
        return service.create(createRequest);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Update a piece of authors information", response = AuthorDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated authors information"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public AuthorDTOResponse update(
            @PathVariable Long id,
            @RequestBody @Valid AuthorDTORequest updateRequest
    ) {
        return service.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific author with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deletes the specific author"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        if (!service.deleteById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    @Override
    @GetMapping("/newsId/{id}")
    @ApiOperation(value = "Get author by news id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the authors with the news id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<AuthorDTOResponse> readAuthorByNewsId(@PathVariable Long id) {
        return service.readAuthorByNewsId(id);
    }
}
