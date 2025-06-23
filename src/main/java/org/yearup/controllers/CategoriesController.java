package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;


import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {

    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }


    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public List<Category> getById(@PathVariable int id) {
        {
            try
            {
                var category = categoryDao.getById(id);

                if(category == null)
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);

                return category;
            }
            catch(Exception ex)
            {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
            }
        }
    }



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category addCategory(@RequestBody Category category) {

       try{
           return categoryDao.create(category);
       }
       catch(Exception ex) {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
       }
    }


    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            categoryDao.update(id, category);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
        return category;
    }


    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable int id) {
        try
        {
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            productDao.delete(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
