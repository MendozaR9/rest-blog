package com.example.restblog.web;

import com.example.restblog.data.CategoriesRepository;
import com.example.restblog.data.Category;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {

    private final CategoriesRepository categoriesRepository;

    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoriesRepository.findAll();
    }


    @GetMapping("name")
    public Category getPostByCategory(@RequestParam String categoryName){
     return    categoriesRepository.findCategoryByName(categoryName);
    }

}
