package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;


import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {

    private CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;

    }


    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {
        Category category = null;
        try
        {
            category = categoryDao.getById(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

        if(category == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return category;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {

       try{
           return categoryDao.create(category);
       }
       catch(Exception ex) {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
       }
    }


    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            categoryDao.update(id, category);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops.");
        }
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        try
        {
            var category = categoryDao.getById(id);
            if(category== null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            categoryDao.delete(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}

