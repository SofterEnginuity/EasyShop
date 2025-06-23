package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin


public class CategoriesController
{

    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
public CategoriesController(CategoryDao categoryDao, ProductDao productDao){
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }



@GetMapping
    public List<Category> getAll()
    {
return categoryDao.getAllCategories();
    }

@GetMapping()
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    // https://localhost:8080/categories/1/products
    //help
    @GetMapping("{categoryId}/products")
        public List<Product> getProductsById(@PathVariable int categoryId) {
        List<Product> products = productDao.getById(categoryId);
        return products;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> addCategory(@RequestBody Category category) {
    categoryDao.create(category);
        return categoryDao.getAllCategories();
    }


  @PutMapping
  @PreAuthorize("hasRole('ADMIN')")
    public List<Category> updateCategory(@PathVariable int id, @RequestBody Category category)
    {
       categoryDao.update(id,category);
       return categoryDao.getAllCategories();
    }


    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
    }
}
