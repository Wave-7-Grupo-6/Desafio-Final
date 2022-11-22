package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.dto.CategoryDTO;
import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.service.ICategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Category controller.
 */
@RestController
@RequestMapping("/api/v1/category")
@Api(tags = "Category Controller", value = "CategoryController", description = "Controller for Category")
public class CategoryController {
    @Autowired
    private ICategory service;

    /**
     * Save the category.
     *
     * @param categoryDTO the category dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody @Valid CategoryDTO categoryDTO){
        service.save(categoryDTO);
    }

    /**
     * Get all response entity with categories.
     *
     * @return the response entity
     */
    @GetMapping
    @ApiOperation(value = "Get all Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categories found"),
    })
    public ResponseEntity<List<Category>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity with category
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category found"),
            @ApiResponse(code = 404, message = "Category not found"),
    })
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
