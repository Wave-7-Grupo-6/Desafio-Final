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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Api(tags = "Category Controller", value = "CategoryController", description = "Controller for Category")
public class CategoryController {
    @Autowired
    private ICategory service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category created successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    public void save(@RequestBody @Valid CategoryDTO categoryDTO){
        service.save(categoryDTO);
    }

    @GetMapping
    @ApiOperation(value = "Get all Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categories found"),
    })
    public ResponseEntity<List<Category>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

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
