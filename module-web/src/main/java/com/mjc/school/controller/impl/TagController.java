package com.mjc.school.controller.impl;

import com.mjc.school.controller.TagCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.TagCommandsService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
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
@RequestMapping("/tag")
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting tag in the application")
public class TagController implements TagCommandsController {

    private final TagCommandsService service;

    @Autowired
    public TagController(TagCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    @ApiOperation(value = "View all tags", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all tags"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<TagDTOResponse> readAll( @RequestParam(defaultValue = "10", required = false) int limit,
                                         @RequestParam(defaultValue = "0", required = false) int offset) {
        return service.readAll(limit, offset);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve specific tag with the supplied id", response = TagDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the tag with the supplied id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public TagDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create tag", response = TagDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a tag"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public TagDTOResponse create(
            @RequestBody @Valid TagDTORequest createRequest
    ) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a tag", response = TagDTOResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated tag"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public TagDTOResponse update(
            @PathVariable Long id,
            @RequestBody @Valid TagDTORequest updateRequest
    ) {
        return service.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes specific tag with the supplied id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deletes the specific tag"),
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
    @ApiOperation(value = "Get tags by news id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get the specific tag"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<TagDTOResponse> readTagsByNewsId(@PathVariable Long id) {
        return service.readTagsByNewsId(id);
    }
}
