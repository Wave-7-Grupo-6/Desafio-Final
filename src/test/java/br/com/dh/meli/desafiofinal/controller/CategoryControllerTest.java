package br.com.dh.meli.desafiofinal.controller;

import br.com.dh.meli.desafiofinal.model.Category;
import br.com.dh.meli.desafiofinal.service.ICategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.dh.meli.desafiofinal.utils.TestUtils.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.hamcrest.CoreMatchers;

import java.util.List;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ICategory categoryService;

    @Test
    void getAll_returnAllCategories_whenValid() throws Exception {
        List<Category> categories = List.of(getCategory());
        when(categoryService.findAll()).thenReturn(categories);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is("Category 1")))
                .andExpect(jsonPath("$[0].temperature", CoreMatchers.is(10.0)));
    }

    @Test
    void findById_returnCategory_whenValid() throws Exception {
        Category category = getCategory();
        when(categoryService.findById(anyLong())).thenReturn(category);

        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/category/1")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.name", CoreMatchers.is("Category 1")))
                .andExpect(jsonPath("$.temperature", CoreMatchers.is(10.0)));
    }
}
